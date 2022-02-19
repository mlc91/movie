package com.mlc.movie.controller;

import com.mlc.movie.model.movieUser.MovieUser;
import com.mlc.movie.model.userApp.UserApp;
import com.mlc.movie.model.credit.crew.Crew;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.mlc.movie.helper.ProgramHelper.*;

/**
 * The MovieController class implements the Movie controller logic.
 * The user will use these methods to save, delete and list all their
 * favorite movies.
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserAppRepository userAppRepository;

    @Autowired
    MovieUserRepository movieUserRepository;

    @Autowired
    CrewRepository crewRepository;

    /**
     * Save a MovieUser in the DB. If the Movie doesn't exist in the DB, it save it too.
     * @param @PathVariable tmdbId
     * @param authentication
     * @return responseEntity
     */
    @PostMapping(path = "/movie/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            // If the Person isn't saved, retrieve information from the TMDB API
            if(movieRepository.findByTmdbId(tmdbId) == null) {
                Movie movie = getMovieFromAPI(tmdbId);
                movieRepository.save(movie);
            }
            // Get the current User and the Movie to save to create a new MovieUser
            UserApp user = userAppRepository.findByNickname(authentication.getName());
            Movie movieToSave = movieRepository.findByTmdbId(tmdbId);
            // Checks if the Movie is already saved by the current User. If not, it will be saved
            Set<MovieUser> movieIsSaved = movieUserRepository.findByUserApp(user).stream()
                    .filter(movieUser -> movieUser.getMovie().getId() == movieToSave.getId() &&
                            movieUser.getUserApp().equals(user)).collect(Collectors.toSet());
            if (movieIsSaved.isEmpty()){
                MovieUser movieUser = movieUserRepository.save(new MovieUser(user, movieToSave));
                return new ResponseEntity<>(makeMap("movieUserId", movieUser.getId()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("Error", "Movie already saved"), HttpStatus.FORBIDDEN);
            }
        }
    }

    /**
     * Retrieve all the Movies from the DB that belongs to the current User.
     * @param authentication
     * @return responseEntity
     */
    @GetMapping("/movies")
    private ResponseEntity<Map<String, Object>> getMovies(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            UserApp userApp = userAppRepository.findByNickname(authentication.getName());
            dto.put("movies", userApp.getMovieUsers().stream().map(movieUser -> movieUser.getMovie().movieDTO()));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    /**
     * Delete a MovieUser from the DB.
     * @param @PathVariable movieUserId
     * @param authentication
     * @return responseEntity
     */
    @DeleteMapping(path = "movie/{movieUserId}")
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

    /**
     * Get all the Movies form the DB filtered out by release Year.
     * @param @PathVariable releaseYear
     * @param authentication
     * @return responseEntity
     */
    @GetMapping(path = "movies/releaseYear/{releaseYear}")
    public ResponseEntity<Map<String, Object>> getMovieByYear(@PathVariable int page,
                                                              @PathVariable String releaseYear,
                                                              Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Movie> allMovies = movieRepository.findAll(pageable);
            if (!allMovies.isEmpty()) {
                Map<String, Object> dto = new LinkedHashMap<>();
                // To get the Year from releaseDate: movie.getReleaseDate().substring(0, 4)
                List<Movie> movies = allMovies.stream().filter(movie -> Objects.equals(movie
                        .getReleaseDate().substring(0, 4), releaseYear)).collect(Collectors.toList());
                dto.put("movies", movies.stream().map(Movie::movieDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    /**
     * Get a movie by director.
     * @param @PathVariable director
     * @param authentication
     * @return responseEntity
     */
    @GetMapping(path = "movies/director/{director}/{page}")
    public ResponseEntity<Map<String, Object>> getDirector(@PathVariable int page,
                                                           @PathVariable String director, Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Crew> allCrew = crewRepository.findAll(pageable);
            if(!allCrew.isEmpty()){
                Map<String, Object> dto = new LinkedHashMap<>();
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
    /**
     * Get a Movie by name.
     * @param name
     * @param authentication
     * @return responseEntity
     */
    @GetMapping(path = "movies/name/{name}/{page}")
    public ResponseEntity<Map<String, Object>> getMovieByName(@PathVariable int page,
                                                              @PathVariable String name,
                                                              Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Pageable pageable = PageRequest.of(page, 1);
            Page<Movie> allMovies = movieRepository.findAll(pageable);
            if(!allMovies.isEmpty()){
                Map<String, Object> dto = new LinkedHashMap<>();
                List<Movie> matchedMovies = allMovies.stream().filter(movie -> Objects.equals(movie.getTitle(), name))
                        .collect(Collectors.toList());
                dto.put("movies", matchedMovies.stream().map(Movie::movieDTO));
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}
