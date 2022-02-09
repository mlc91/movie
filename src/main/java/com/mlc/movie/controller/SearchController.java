package com.mlc.movie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mlc.movie.URLConstants;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.model.search.SearchDTO;
import com.mlc.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.mlc.movie.searchHelper.SearchHelper.*;
import static com.mlc.movie.util.URLHelper.urlBuilder;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("movies/query/{query}")
    public ResponseEntity<String> getMoviesAPI(@PathVariable String query) throws JsonProcessingException {
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

//    ObjectMapper mapper = new ObjectMapper();
//    JsonNode root = mapper.readTree(response.getBody());
//    JsonNode name = root.path("original_title");
//    Movie movie = new Movie();
//        movie.setTitle(String.valueOf(name));
//    Credit credit = new Credit();
//        movie.setCredit(credit);
//        movieRepository.save(movie);

//
//    @GetMapping("movies/query/{query}")
//    public SearchDTO getMoviesAPI(@PathVariable String query){
//        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&query=" + query;
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, SearchDTO.class);
//    }

    @GetMapping("movies/language/{query}/{language}")
    public SearchDTO getMoviesLanguageAPI(@PathVariable String query, @PathVariable String language){
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/page/{query}/{page}")
    public SearchDTO getMoviesPageAPI(@PathVariable String query, @PathVariable int page){
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&page=" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/adult/{query}/{includeAdult}")
    public SearchDTO getMoviesIncludeAdultAPI(@PathVariable String query, @PathVariable boolean includeAdult){
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/region/{query}/{region}")
    public SearchDTO getMoviesRegionAPI(@PathVariable String query, @PathVariable String region){
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/year/{query}/{year}")
    public SearchDTO getMoviesYearAPI(@PathVariable String query, @PathVariable int year){
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&year=" + year + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/release/{query}/{releaseYear}")
    public SearchDTO getMoviesReleaseYearAPI(@PathVariable String query, @PathVariable int releaseYear){
        String url = urlBuilder(URLConstants.URL_SEARCH_MOVIES) + "&primary_release_year=" + releaseYear + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("persons/query/{query}")
    public SearchDTO getPersonsAPI(@PathVariable String query){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("persons/language/{query}/{language}")
    public SearchDTO getPersonsLanguageAPI(@PathVariable String query, @PathVariable String language){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("persons/page/{query}/{page}")
    public SearchDTO getPersonsAPI(@PathVariable String query, @PathVariable int page){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&page=" + page +"&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }
    @GetMapping("persons/adult/{query}/{includeAdult}")
    public SearchDTO getPersonsAPI(@PathVariable String query, @PathVariable boolean includeAdult){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }
    @GetMapping("persons/region/{query}/{region}")
    public SearchDTO getPersonsAPI(@PathVariable String query, @PathVariable String region){
        String url = urlBuilder(URLConstants.URL_SEARCH_PERSONS) + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("companies/query/{query}")
    public SearchDTO getCompaniesAPI(@PathVariable String query){
        String url = urlBuilder(URLConstants.URL_SEARCH_COMPANIES) + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("companies/page/{query}/{region}")
    public SearchDTO getCompaniesPageAPI(@PathVariable String query, @PathVariable int page){
        String url = urlBuilder(URLConstants.URL_SEARCH_COMPANIES) + "&page" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    //TODO move the following methods to other class...

    @GetMapping("movie/{movieId}")
    public MovieDTO getMovieAPI(@PathVariable Long movieId){
        return getMovieFromAPI(movieId);
    }

    @GetMapping("person/{personId}")
    public PersonDTO getPersonAPI(@PathVariable Long personId){
        return getPersonFromAPI(personId);
    }

}
