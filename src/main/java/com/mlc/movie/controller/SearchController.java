package com.mlc.movie.controller;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.model.search.SearchDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("movies/query/{query}")
    public SearchDTO getMoviesAPI(@PathVariable String query){
        String url = URLConstants.URL_SEARCH_MOVIES + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/language/{query}/{language}")
    public SearchDTO getMoviesLanguageAPI(@PathVariable String query, @PathVariable String language){
        String url = URLConstants.URL_SEARCH_MOVIES + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/page/{query}/{page}")
    public SearchDTO getMoviesPageAPI(@PathVariable String query, @PathVariable int page){
        String url = URLConstants.URL_SEARCH_MOVIES + "&page=" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/adult/{query}/{includeAdult}")
    public SearchDTO getMoviesIncludeAdultAPI(@PathVariable String query, @PathVariable boolean includeAdult){
        String url = URLConstants.URL_SEARCH_MOVIES + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/region/{query}/{region}")
    public SearchDTO getMoviesRegionAPI(@PathVariable String query, @PathVariable String region){
        String url = URLConstants.URL_SEARCH_MOVIES + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/year/{query}/{year}")
    public SearchDTO getMoviesYearAPI(@PathVariable String query, @PathVariable int year){
        String url = URLConstants.URL_SEARCH_MOVIES + "&year=" + year + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("movies/release/{query}/{releaseYear}")
    public SearchDTO getMoviesReleaseYearAPI(@PathVariable String query, @PathVariable int releaseYear){
        String url = URLConstants.URL_SEARCH_MOVIES + "&primary_release_year=" + releaseYear + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("persons/query/{query}")
    public SearchDTO getPersonsAPI(@PathVariable String query){
        String url = URLConstants.URL_SEARCH_PERSONS + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("persons/language/{query}/{language}")
    public SearchDTO getPersonsLanguageAPI(@PathVariable String query, @PathVariable String language){
        String url = URLConstants.URL_SEARCH_PERSONS + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("persons/page/{query}/{page}")
    public SearchDTO getPersonsAPI(@PathVariable String query, @PathVariable int page){
        String url = URLConstants.URL_SEARCH_PERSONS + "&page=" + page +"&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }
    @GetMapping("persons/adult/{query}/{includeAdult}")
    public SearchDTO getPersonsAPI(@PathVariable String query, @PathVariable boolean includeAdult){
        String url = URLConstants.URL_SEARCH_PERSONS + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }
    @GetMapping("persons/region/{query}/{region}")
    public SearchDTO getPersonsAPI(@PathVariable String query, @PathVariable String region){
        String url = URLConstants.URL_SEARCH_PERSONS + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("companies/query/{query}")
    public SearchDTO getCompaniesAPI(@PathVariable String query){
        String url = URLConstants.URL_SEARCH_COMPANIES + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    @GetMapping("companies/page/{query}/{region}")
    public SearchDTO getCompaniesPageAPI(@PathVariable String query, @PathVariable int page){
        String url = URLConstants.URL_SEARCH_COMPANIES + "&page" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, SearchDTO.class);
    }

    //TODO move the following methods to other class...
    @GetMapping("credits/{movieId}")
    public CreditDTO getCreditsAPI(@PathVariable String movieId){

    }

    // TODO tomar el método anterior y pegarlo en este para poder obtener los credits
    @GetMapping("movie/{movieId}")
    public MovieDTO getMovieAPI(@PathVariable String movieId){
        String url = URLConstants.URL_SEARCH_MOVIE_1 + movieId + URLConstants.URL_SEARCH_MOVIE_2;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, MovieDTO.class);
    }

    @GetMapping("person/{personId}")
    public PersonDTO getPersonAPI(@PathVariable String personId){
        RestTemplate restTemplate = new RestTemplate();
        String url =  URLConstants.URL_SEARCH_PERSON_1 + personId + URLConstants.URL_SEARCH_PERSON_2;
        return restTemplate.getForObject(url, PersonDTO.class);
    }
}
