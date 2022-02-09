package com.mlc.movie.controller;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.credit.Credit;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.repository.PersonRepository;
import com.mlc.movie.searchHelper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mlc.movie.URLConstants.URL_SEARCH_CREDITS;
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

    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long movieId){
        String url = urlBuilderWithId(movieId, URLConstants.URL_SEARCH_MOVIE);
        String url2 = urlBuilderTwoParamWithId(movieId, URLConstants.URL_SEARCH_MOVIE, URL_SEARCH_CREDITS);
        RestTemplate restTemplate1 = new RestTemplate();
        RestTemplate restTemplate2 = new RestTemplate();

        MovieDTO movieDTO = restTemplate1.getForObject(url, MovieDTO.class);
        ResponseEntity<CreditDTO> response = restTemplate2.getForEntity(url2, CreditDTO.class);
        movieDTO.setCreditDTO(response.getBody());
        Movie movie = setMovieFromMovieDTO(movieDTO);

        if(movieRepository.findByTmdbId(movieId) != null){
            return new ResponseEntity<>(makeMap("Error", "Movie already saved!"), HttpStatus.FORBIDDEN);
        } else if (!movie.isValid()){
            return new ResponseEntity<>(response.getStatusCode());
        }
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long movieId){
//        MovieDTO movieDTO = SearchHelper.getMovieFromAPI(movieId);
//        Movie movie = setMovieFromMovieDTO(movieDTO);
//
//        if(movieRepository.findByTmdbId(movieId) != null){
//            return new ResponseEntity<>(makeMap("Error", "Movie already saved!"), HttpStatus.FORBIDDEN);
//        }
//
//        movieRepository.save(movie);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @RequestMapping(path = "/person/{personId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable Long personId){
        PersonDTO personDTO = SearchHelper.getPersonFromAPI(personId);
        Person person = setPersonFromPersonDTO(personDTO);

        if(personRepository.findByTmdbId(personId) != null){
            return new ResponseEntity<>(makeMap("Error", "Person already saved!"), HttpStatus.FORBIDDEN);
        }

        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    private Map<String, Object> getPersons(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("persons", personRepository.findAll().stream().map(person -> person.personDTO()).collect(Collectors.toList()));
        return dto;
    }

    @GetMapping("/movies")
    private Map<String, Object> getMovies(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("movies", movieRepository.findAll().stream().map(movie -> movie.movieDTO()).collect(Collectors.toList()));
        //dto.put("credit", creditRepository.findAll().stream().map(credit -> credit.creditDTO()).collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping(path = "movie/delete/{movieId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteMovie(@PathVariable Long movieId) {
        movieRepository.deleteById(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "person/delete/{personId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long personId) {
        personRepository.deleteById(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
