package com.mlc.movie.controller;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.genre.GenreListDTO;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.PersonDTO;
import com.mlc.movie.model.search.SearchDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.mlc.movie.searchHelper.SearchHelper.*;

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

    // TODO tomar el método anterior y pegarlo en este para poder obtener los credits
    @GetMapping("movie/{movieId}")
    public MovieDTO getMovieAPI(@PathVariable String movieId){
        return getMovieFromAPI(movieId);
    }

    @GetMapping("person/{personId}")
    public PersonDTO getPersonAPI(@PathVariable String personId){
        return getPersonFromAPI(personId);
    }

    //TODO: Quitar el following método

    @GetMapping("credit/{movieId}")
    public CreditDTO getCreditAPI(@PathVariable String movieId){
        return getCreditFromAPI(movieId);
    }

}
//TODO: CAMBIAR LOS ID STRING TO INT
