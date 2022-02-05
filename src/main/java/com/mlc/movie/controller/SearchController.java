package com.mlc.movie.controller;

import com.mlc.movie.helper.SearchAPIHelper;
import com.mlc.movie.model.movie.MovieAPI;
import com.mlc.movie.model.person.PersonAPI;
import com.mlc.movie.model.search.SearchAPI;
import com.mlc.movie.model.credit.CreditAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @GetMapping("movies/{query}")
    public SearchAPI getMoviesAPI(@PathVariable String query){
        SearchAPI result = SearchAPIHelper.searchMoviesAPI(query);
        return result;
    }

    @GetMapping("movies/{query}/{language}")
    public SearchAPI getMoviesLanguageAPI(@PathVariable String query, @PathVariable String language){
        SearchAPI result = SearchAPIHelper.searchMoviesLanguageAPI(query, language);
        return result;
    }

    @GetMapping("movies/{query}/{page}")
    public SearchAPI getMoviesPageAPI(@PathVariable String query, @PathVariable int page){
        SearchAPI result = SearchAPIHelper.searchMoviesPageAPI(query, page);
        return result;
    }

    @GetMapping("movies/{query}/{includeAdult}")
    public SearchAPI getMoviesIncludeAdultAPI(@PathVariable String query, @PathVariable boolean includeAdult){
        SearchAPI result = SearchAPIHelper.searchMoviesIncludeAdultAPI(query, includeAdult);
        return result;
    }

    @GetMapping("movies/{query}/{region}")
    public SearchAPI getMoviesRegionAPI(@PathVariable String query, @PathVariable String region){
        SearchAPI result = SearchAPIHelper.searchMoviesRegionAPI(query, region);
        return result;
    }

    @GetMapping("movies/{query}/{year}")
    public SearchAPI getMoviesYearAPI(@PathVariable String query, @PathVariable int year){
        SearchAPI result = SearchAPIHelper.searchMoviesYearAPI(query, year);
        return result;
    }

    @GetMapping("movies/{query}/{releaseYear}")
    public SearchAPI getMoviesReleaseYearAPI(@PathVariable String query, @PathVariable int releaseYear){
        SearchAPI result = SearchAPIHelper.searchMoviesReleaseYearAPI(query, releaseYear);
        return result;
    }

    @GetMapping("persons/{query}")
    public SearchAPI getPersonsAPI(@PathVariable String query){
        SearchAPI result = SearchAPIHelper.searchPersonsAPI(query);
        return result;
    }

    @GetMapping("credits/{movie_id}")
    public CreditAPI getCreditsAPI(@PathVariable String movie_id){
        CreditAPI result = SearchAPIHelper.searchCreditsAPI(movie_id);
        return result;
    }

    @GetMapping("movie/{movie_id}")
    public MovieAPI getMovieAPI(@PathVariable String movie_id){
        MovieAPI result = SearchAPIHelper.searchMovieAPI(movie_id);
        return result;
    }

    @GetMapping("person/{person_id}")
    public PersonAPI getPersonAPI(@PathVariable String person_id){
        PersonAPI result = SearchAPIHelper.searchPersonAPI(person_id);
        return result;
    }

}
