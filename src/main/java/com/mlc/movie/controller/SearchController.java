package com.mlc.movie.controller;

import com.mlc.movie.constants.URLConstants;
import com.mlc.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.mlc.movie.helper.URLHelper.*;

/**
 * The SearchController class implement the queries to the TMDB API
 * (https://www.themoviedb.org/)
 * Methods to search information are found in this class.
 * There's no need to be logged in to access to these methods.
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("movies/query/{query}")
    public ResponseEntity<String> getMoviesAPI(@PathVariable String query) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("movies/page/{query}/{page}")
    public ResponseEntity<String> getMoviesPageAPI(@PathVariable String query, @PathVariable int page) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&page=" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("movies/language/{query}/{language}")
    public ResponseEntity<String> getMoviesLanguageAPI(@PathVariable String query, @PathVariable String language) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("movies/adult/{query}/{includeAdult}")
    public ResponseEntity<String> getMoviesIncludeAdultAPI(@PathVariable String query, @PathVariable boolean includeAdult) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("movies/region/{query}/{region}")
    public ResponseEntity<String> getMoviesRegionAPI(@PathVariable String query, @PathVariable String region) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("movies/year/{query}/{year}")
    public ResponseEntity<String> getMoviesYearAPI(@PathVariable String query, @PathVariable int year) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&year=" + year + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("movies/release/{query}/{releaseYear}")
    public ResponseEntity<String> getMoviesReleaseYearAPI(@PathVariable String query, @PathVariable int releaseYear) {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&primary_release_year=" + releaseYear + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("persons/query/{query}")
    public ResponseEntity<String> getPersonsAPI(@PathVariable String query) {
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("persons/page/{query}/{page}")
    public ResponseEntity<String> getPersonsPageAPI(@PathVariable String query, @PathVariable int page) {
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&page=" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("persons/language/{query}/{language}")
    public ResponseEntity<String> getPersonsLanguageAPI(@PathVariable String query, @PathVariable String language) {
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @RequestMapping("persons/adult/{query}/{includeAdult}")
    public ResponseEntity<String> getPersonsIncludeAdultAPI(@PathVariable String query, @PathVariable boolean includeAdult){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }
    @RequestMapping("persons/region/{query}/{region}")
    public ResponseEntity<String> getPersonsRegionAPI(@PathVariable String query, @PathVariable String region){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }
}
