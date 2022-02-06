package com.mlc.movie.controller;

import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.repository.MovieRepository;
import com.mlc.movie.searchHelper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mlc.movie.controller.Util.makeMap;
import static com.mlc.movie.model.movie.Movie.setMovieFromMovieDTO;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable String movieId){
        MovieDTO movieDTO = SearchHelper.getMovieFromAPI(movieId);
        Movie movie = setMovieFromMovieDTO(movieDTO);

        if(movieRepository.findByDtoId(movieId) != null){
            return new ResponseEntity<>(makeMap("Error", "Movie already saved!"), HttpStatus.FORBIDDEN);
        }

        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
