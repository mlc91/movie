package com.mlc.movie.controller;

import com.mlc.movie.helper.SearchAPIHelper;
import com.mlc.movie.model.search.SearchAPI;
import com.mlc.movie.model.credit.CreditAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @GetMapping("movie/{query}")
    public SearchAPI getMovieAPI(@PathVariable String query){
        SearchAPI result = SearchAPIHelper.searchMovieAPI(query);
        return result;
    }

    @GetMapping("person/{query}")
    public SearchAPI getPersonAPI(@PathVariable String query){
        SearchAPI result = SearchAPIHelper.searchPersonAPI(query);
        return result;
    }

    @GetMapping("credits/{movie_id}")
    public CreditAPI getCreditAPI(@PathVariable String movie_id){
        CreditAPI result = SearchAPIHelper.searchCreditsAPI(movie_id);
        return result;
    }

}
