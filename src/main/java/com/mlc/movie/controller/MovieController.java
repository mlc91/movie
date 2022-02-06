package com.mlc.movie.controller;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.repository.PersonRepository;
import com.mlc.movie.searchHelper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.model.movie.Movie.setMovieFromMovieDTO;
import static com.mlc.movie.model.person.Person.setPersonFromPersonDTO;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable String movieId){
        MovieDTO movieDTO = SearchHelper.getMovieFromAPI(movieId);
        Movie movie = setMovieFromMovieDTO(movieDTO);

        if(movieRepository.findByTmdbId(movieId) != null){
            return new ResponseEntity<>(makeMap("Error", "Movie already saved!"), HttpStatus.FORBIDDEN);
        }

        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/person/{personId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addPerson(@PathVariable String personId){
        PersonDTO personDTO = SearchHelper.getPersonFromAPI(personId);
        Person person = setPersonFromPersonDTO(personDTO);

        if(personRepository.findByTmdbId(personId) != null){
            return new ResponseEntity<>(makeMap("Error", "Person already saved!"), HttpStatus.FORBIDDEN);
        }

        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    private List<Object> getPersons(){
        return personRepository.findAll().stream().map(person -> person.personDTO()).collect(Collectors.toList());
    }

    @GetMapping("/movies")
    private List<Object> getMovies(){
        return movieRepository.findAll().stream().map(movie -> movie.mo )
    }
}
