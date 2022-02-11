package com.mlc.movie.controller;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.MovieUser;
import com.mlc.movie.model.PersonUser;
import com.mlc.movie.model.Fan;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.repository.*;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.mlc.movie.URLConstants.URL_SEARCH_CREDITS;
import static com.mlc.movie.controller.Util.isGuest;
import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.helper.ProgramHelper.getPersonFromAPI;
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

    @Autowired
    CrewRepository crewRepository;

    @PostMapping(path = "/movie/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Movie movieToAdd = movieRepository.findByTmdbId(tmdbId);
            if(movieToAdd == null) {
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
            Movie movieToSave = movieRepository.findByTmdbId(tmdbId);
            // verifica que el usuario no haya guardado la película
            Set<MovieUser> movieIsSaved = movieUserRepository.findByFan(user).stream()
                    .filter(movieUser -> movieUser.getMovie().getId() == movieToSave.getId() && movieUser.getFan().equals(user))
                    .collect(Collectors.toSet());
            if (movieIsSaved.isEmpty()){
                MovieUser movieUser = movieUserRepository.save(new MovieUser(user, movieToSave));
                return new ResponseEntity<>(makeMap("movieUserId", movieUser.getId()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("Error", "Movie already saved"), HttpStatus.FORBIDDEN);
            }
        }
    }

    @PostMapping(path = "/person/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            if (personRepository.findByTmdbId(tmdbId) == null) {
                Person person = getPersonFromAPI(tmdbId);
                personRepository.save(person);
            }
            Fan user = fanRepository.findByNickname(authentication.getName());
            Person personToSave = personRepository.findByTmdbId(tmdbId);
            Set<PersonUser> personIsSaved = personUserRepository.findByFan(user).stream()
                    .filter(personUser -> Objects.equals(personUser.getPerson().getId(),
                            personToSave.getId()) && personUser.getFan().equals(user))
                    .collect(Collectors.toSet());
            if (personIsSaved.isEmpty()){
                PersonUser personUser = personUserRepository.save(new PersonUser(user, personToSave));
                return new ResponseEntity<>(makeMap("personUserId", personUser.getId()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("Error", "Person already saved"), HttpStatus.FORBIDDEN);
            }
        }
    }

    @GetMapping("/movies")
    private ResponseEntity<Map<String, Object>> getMovies(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            Fan fan = fanRepository.findByNickname(authentication.getName());
            dto.put("movies", fan.getMovieUsers()
                    .stream().map(movieUser -> movieUser.getMovie().movieDTO()));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/persons")
    private ResponseEntity<Map<String, Object>> getPersons(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            Fan fan = fanRepository.findByNickname(authentication.getName());
            dto.put("persons", fan.getPersonUsers()
                    .stream().map(personUser -> personUser.getPerson().personDTO()));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping(path = "movie/delete/{movieUserId}")
    public ResponseEntity<Map<String, Object>> deleteMovie(@PathVariable Long movieUserId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Optional<MovieUser> movieUsers = movieUserRepository.findById(movieUserId);
            if (!movieUsers.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            movieUserRepository.delete(movieUsers.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "person/delete/{personUserId}")
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long personUserId, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Optional<PersonUser> personUsers = personUserRepository.findById(personUserId);
            if (!personUsers.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            personUserRepository.delete(personUsers.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    // Find Movies

    @GetMapping(path = "movies/releaseYear/{releaseYear}")
    public ResponseEntity<Map<String, Object>> getByReleaseDate(@PathVariable String releaseYear, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            List<Movie> allMovies = movieRepository.findAll();
            if (!allMovies.isEmpty()) {
                String year = releaseYear.substring(0, 4);
                List<Movie> movies = allMovies.stream()
                        .filter(movie -> Objects.equals(movie.getReleaseDate().substring(0, 4), year)).collect(Collectors.toList());
                dto.put("movies", movies.stream().map(Movie::movieDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping(path = "movies/director/{director}")
    public ResponseEntity<Map<String, Object>> getByDirector(@PathVariable String director, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            List<Crew> allCrew = crewRepository.findAll();
            if(!allCrew.isEmpty()){
                List<Crew> allDirectors = allCrew.stream()
                        .filter(crew -> Objects.equals(crew.getJob(), "Director")).collect(Collectors.toList());
                Predicate<Crew> predicate = crew -> Objects.equals(crew.getName(), director);
                List<String> matchedDirectors = allDirectors.stream().filter(predicate)
                        .map(Crew::getName).collect(Collectors.toList());
                dto.put("movies", matchedDirectors);
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping(path = "movies/name/{name}")
    public ResponseEntity<Map<String, Object>> getByName(@PathVariable String name, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            List<Movie> allMovies = movieRepository.findAll();
            if(!allMovies.isEmpty()){
                List<Movie> matchedMovies = allMovies.stream().filter(movie -> Objects.equals(movie.getTitle(), name)).collect(Collectors.toList());
                dto.put("movies", matchedMovies.stream().map(Movie::movieDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping(path = "admin/movies/{page}")
    public ResponseEntity<Map<String, Object>> getAllMovies(@PathVariable int page, Authentication authentication) {
        if (isGuest(authentication) && authentication.getAuthorities()
                .stream().noneMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Movie> allMovies = movieRepository.findAll(pageable);
            if(allMovies.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Map<String, Object> dto = new LinkedHashMap<>();
                dto.put("movies", allMovies.stream().map(Movie::movieDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            }
        }
    }

    @GetMapping(path = "admin/persons/{page}")
    public ResponseEntity<Map<String, Object>> getAllPerson(@PathVariable int page, Authentication authentication) {
        if (isGuest(authentication) && authentication.getAuthorities()
                .stream().noneMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Person> allPersons = personRepository.findAll(pageable);
            if(allPersons.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Map<String, Object> dto = new LinkedHashMap<>();
                dto.put("persons", allPersons.stream().map(Person::personDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            }
        }
    }
}
