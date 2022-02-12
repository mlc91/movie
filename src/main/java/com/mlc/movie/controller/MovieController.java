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

    @PostMapping(path = "/movie/{tmdbId}")
    public ResponseEntity<Map<String, Object>> addMovie(@PathVariable Long tmdbId, Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Movie movieToAdd = movieRepository.findByTmdbId(tmdbId);
            if(movieToAdd == null) {
                Movie movie = getMovieFromAPI(tmdbId);
                movieRepository.save(movie);
            }
            UserApp user = userAppRepository.findByNickname(authentication.getName());
            Movie movieToSave = movieRepository.findByTmdbId(tmdbId);
            // verifica que el usuario no haya guardado la película
            Set<MovieUser> movieIsSaved = movieUserRepository.findByUserApp(user).stream()
                    .filter(movieUser -> movieUser.getMovie().getId() == movieToSave.getId() && movieUser.getUserApp().equals(user))
                    .collect(Collectors.toSet());
            if (movieIsSaved.isEmpty()){
                MovieUser movieUser = movieUserRepository.save(new MovieUser(user, movieToSave));
                return new ResponseEntity<>(makeMap("movieUserId", movieUser.getId()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(makeMap("Error", "Movie already saved"), HttpStatus.FORBIDDEN);
            }
        }
    }

    @GetMapping("/movies")
    private ResponseEntity<Map<String, Object>> getMovies(Authentication authentication){
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, Object> dto = new LinkedHashMap<>();
            UserApp userApp = userAppRepository.findByNickname(authentication.getName());
            dto.put("movies", userApp.getMovieUsers()
                    .stream().map(movieUser -> movieUser.getMovie().movieDTO()));
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

}
