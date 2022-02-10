package com.mlc.movie.controller;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.MovieUser;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.repository.MovieUserRepository;
import com.mlc.movie.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mlc.movie.URLConstants.URL_SEARCH_CREDITS;
import static com.mlc.movie.controller.Util.isGuest;
import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.model.movie.MovieDTO.setMovieFromMovieDTO;
import static com.mlc.movie.model.person.PersonDTO.setPersonFromPersonDTO;
import static com.mlc.movie.util.URLHelper.urlBuilderTwoParamWithId;
import static com.mlc.movie.util.URLHelper.urlBuilderWithId;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MovieUserRepository movieUserRepository;



    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long movieId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        String urlMovie = urlBuilderWithId(movieId, URLConstants.URL_SEARCH_MOVIE);
        String urlCredit = urlBuilderTwoParamWithId(movieId, URLConstants.URL_SEARCH_MOVIE, URL_SEARCH_CREDITS);
        RestTemplate restTemplate1 = new RestTemplate();
        RestTemplate restTemplate2 = new RestTemplate();
        MovieDTO movieDTO = restTemplate1.getForEntity(urlMovie, MovieDTO.class).getBody();
        ResponseEntity<CreditDTO> response = restTemplate2.getForEntity(urlCredit, CreditDTO.class);
        movieDTO.setCreditDTO(response.getBody());
        Movie movie = setMovieFromMovieDTO(movieDTO);
        movie.setMovieUser(movieUserRepository.findByUsername(username));
        if(movieRepository.findByTmdbId(movieId) != null){
            return new ResponseEntity<>(makeMap("Error", "Movie already saved!"), HttpStatus.FORBIDDEN);
        }
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/person/{personId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable Long personId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        String url =  urlBuilderWithId(personId, URLConstants.URL_SEARCH_PERSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonDTO> personDTO = restTemplate.getForEntity(url, PersonDTO.class);
        Person person = setPersonFromPersonDTO(personDTO.getBody());
        person.setMovieUser(movieUserRepository.findByUsername(username));
        if(personRepository.findByTmdbId(personId) != null){
            return new ResponseEntity<>(makeMap("Error", "Person already saved!"), HttpStatus.FORBIDDEN);
        }
        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/persons")
    private ResponseEntity<Map<String, Object>> getPersons(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        // todo poner principal y username en un método aparte
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        MovieUser movieUser = movieUserRepository.findByUsername(username);
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("persons", personRepository.findByMovieUser(movieUser).stream().map(Person::personDTO).collect(Collectors.toList()));
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies")
    private Map<String, Object> getMovies(Authentication authentication){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("movies", movieRepository.findAll().stream().map(Movie::movieDTO).collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping(path = "movie/delete/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteMovie(@PathVariable Long movieId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        movieRepository.deleteById(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "person/delete/{personId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long personId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        }
        personRepository.deleteById(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
