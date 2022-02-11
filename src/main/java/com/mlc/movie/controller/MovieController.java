package com.mlc.movie.controller;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.MovieUser;
import com.mlc.movie.model.PersonUser;
import com.mlc.movie.model.Fan;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.mlc.movie.URLConstants.URL_SEARCH_CREDITS;
import static com.mlc.movie.controller.Util.isGuest;
import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.model.movie.MovieDTO.setMovieFromMovieDTO;
import static com.mlc.movie.model.person.PersonDTO.setPersonFromPersonDTO;
import static com.mlc.movie.util.URLHelper.urlBuilderTwoParamWithId;
import static com.mlc.movie.util.URLHelper.urlBuilderWithId;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FanRepository fanRepository;

    @Autowired
    MovieUserRepository movieUserRepository;

    @Autowired
    PersonUserRepository personUserRepository;

    @PostMapping(path = "/movie/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            if(movieRepository.findByTmdbId(tmdbId) == null) {
                String urlMovie = urlBuilderWithId(tmdbId, URLConstants.URL_SEARCH_MOVIE);
                String urlCredit = urlBuilderTwoParamWithId(tmdbId, URLConstants.URL_SEARCH_MOVIE, URL_SEARCH_CREDITS);
                RestTemplate restTemplate1 = new RestTemplate();
                RestTemplate restTemplate2 = new RestTemplate();
                MovieDTO movieDTO = restTemplate1.getForEntity(urlMovie, MovieDTO.class).getBody();
                CreditDTO creditDTO = restTemplate2.getForEntity(urlCredit, CreditDTO.class).getBody();
                movieDTO.setCreditDTO(creditDTO);
                Movie movie = setMovieFromMovieDTO(movieDTO);
                movieRepository.save(movie);
            }
            Fan user = fanRepository.findByNickname(authentication.getName());
            MovieUser movieUser = movieUserRepository.save(new MovieUser(user, movieRepository.findByTmdbId(tmdbId)));
            return new ResponseEntity<>(makeMap("movieUserId", movieUser.getId()), HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "/person/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            if (personRepository.findByTmdbId(tmdbId) == null) {
                String url = urlBuilderWithId(tmdbId, URLConstants.URL_SEARCH_PERSON);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<PersonDTO> personDTO = restTemplate.getForEntity(url, PersonDTO.class);
                Person person = setPersonFromPersonDTO(personDTO.getBody());
                personRepository.save(person);
            }
            Fan user = fanRepository.findByNickname(authentication.getName());
            PersonUser personUser = personUserRepository.save(new PersonUser(user, personRepository.findByTmdbId(tmdbId)));
            return new ResponseEntity<>(makeMap("personUserId", personUser.getId()), HttpStatus.CREATED);
        }
    }

    @GetMapping("/persons")
    private ResponseEntity<Map<String, Object>> getPersons(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        Map<String, Object> dto = new LinkedHashMap<>();
        Fan fan = fanRepository.findByNickname(authentication.getName());
        List<PersonUser> personUsers = personUserRepository.findByFan(fan);
        List<Person> personList = personUsers.stream().map(PersonUser::getPerson).collect(Collectors.toList());
        dto.put("persons", personList.stream().map(Person::personDTO));
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies")
    private ResponseEntity<Map<String, Object>> getMovies(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        Pageable firstPage = (Pageable) PageRequest.of(0, 10);
        Map<String, Object> dto = new LinkedHashMap<>();
        Fan fan = fanRepository.findByNickname(authentication.getName());
        List<MovieUser> movieUsers = movieUserRepository.findByFan(fan);
        List<Movie> movieList = movieUsers.stream().map(MovieUser::getMovie).collect(Collectors.toList());
        dto.put("movies", movieList.stream().map(Movie::movieDTO));
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "movie/delete/{movieId}")
    public ResponseEntity<Map<String, Object>> deleteMovie(@PathVariable Long movieId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Fan fan = fanRepository.findByNickname(authentication.getName());
            List<MovieUser> movieUsers = movieUserRepository.findByFan(fan);
            Predicate<MovieUser> predicate = movieUser -> Objects.equals(movieUser.getMovie().getId(), movieId);
            List<MovieUser> movieUserToDelete = movieUsers.stream().filter(predicate).collect(Collectors.toList());
            if(!movieUserToDelete.isEmpty()) {
                movieUserToDelete.forEach(movieUser -> movieUserRepository.delete(movieUser));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "person/delete/{personId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long personId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Fan fan = fanRepository.findByNickname(authentication.getName());
            List<PersonUser> personUsers = personUserRepository.findByFan(fan);
            Predicate<PersonUser> predicate = personUser -> Objects.equals(personUser.getPerson().getId(), personId);
            List<PersonUser> personUserToDelete = personUsers.stream().filter(predicate).collect(Collectors.toList());
            if(!personUserToDelete.isEmpty()) {
                personUserToDelete.forEach(personUser -> personUserRepository.delete(personUser));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
// TODO TERMINAR:

//    @GetMapping(path = "movies/{releaseDate}")
//    public ResponseEntity<Map<String, Object>> getByReleaseDate(@PathVariable String releaseDate) {
//        Map<String, Object> dto = new LinkedHashMap<>();
//        List<Movie> allMovies = movieRepository.findAllByReleaseDate(releaseDate, firstPage);
//        if(!allMovies.isEmpty()){
//            dto.put("movies", allMovies.stream().map(Movie::movieDTO));
//            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
