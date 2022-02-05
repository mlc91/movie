package com.mlc.movie.helper;

import com.mlc.movie.model.search.SearchAPI;
import com.mlc.movie.model.credit.CreditAPI;
import org.springframework.web.client.RestTemplate;

public class SearchAPIHelper {
    private final static String URL_SEARCH_MOVIE = "https://api.themoviedb.org/3/search/movie?api_key=fcc99eaef0e81ed1ea81c258bd54257b&query=";
    private final static String URL_SEARCH_PERSON = "https://api.themoviedb.org/3/search/person?api_key=fcc99eaef0e81ed1ea81c258bd54257b&query=";
    private final static String URL_SEARCH_CREDIT_1 = "https://api.themoviedb.org/3/movie/";
    private final static String URL_SEARCH_CREDIT_2 = "/credits?api_key=fcc99eaef0e81ed1ea81c258bd54257b";

    public static SearchAPI searchMovieAPI(String query){
        String url = URL_SEARCH_MOVIE+query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchPersonAPI(String query){
        String url = URL_SEARCH_PERSON+query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }
    public static CreditAPI searchCreditsAPI(String movie_id){
        String url = URL_SEARCH_CREDIT_1+movie_id+URL_SEARCH_CREDIT_2;
        RestTemplate restTemplate = new RestTemplate();
        CreditAPI result = restTemplate.getForObject(url, CreditAPI.class);
        return result;
    }


}
