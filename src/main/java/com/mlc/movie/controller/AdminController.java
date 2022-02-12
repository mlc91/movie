package com.mlc.movie.controller;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.mlc.movie.helper.ProgramHelper.isGuest;
import static com.mlc.movie.helper.ProgramHelper.makeMap;

/**
 * The AdminController class implements some methods that only an Admin has access.
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api")
public class AdminController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PersonRepository personRepository;

    /**
     * Get all the Movies from the DB.
     * @param @PathVariable page
     * @param authentication
     * @return
     */
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

    /**
     * Get all the Persons from the DB
     * @param @PathVariable page
     * @param authentication
     * @return
     */
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
