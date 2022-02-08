package com.mlc.movie.controller;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.repository.CastRepository;
import com.mlc.movie.repository.CreditRepository;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.repository.PersonRepository;
import com.mlc.movie.searchHelper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.model.movie.MovieDTO.setMovieFromMovieDTO;
import static com.mlc.movie.model.person.PersonDTO.setPersonFromPersonDTO;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CreditRepository creditRepository;

    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long movieId){
        MovieDTO movieDTO = SearchHelper.getMovieFromAPI(movieId);
        Movie movie = setMovieFromMovieDTO(movieDTO);

        if(movieRepository.findByTmdbId(movieId) != null){
            return new ResponseEntity<>(makeMap("Error", "Movie already saved!"), HttpStatus.FORBIDDEN);
        }

        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
        dto.put("credit", creditRepository.findAll().stream().map(credit -> credit.creditDTO()).collect(Collectors.toList()));
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
