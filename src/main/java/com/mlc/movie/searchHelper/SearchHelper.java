package com.mlc.movie.searchHelper;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.PersonDTO;
import org.springframework.web.client.RestTemplate;

public class SearchHelper {
    public static PersonDTO getPersonFromAPI(String personId){
        RestTemplate restTemplate = new RestTemplate();
        String url =  URLConstants.URL_SEARCH_PERSON_1 + personId + URLConstants.URL_SEARCH_PERSON_2;
        return restTemplate.getForObject(url, PersonDTO.class);
    }

    public static MovieDTO getMovieFromAPI(String movieId){
        MovieDTO movieDTO = new MovieDTO();
        String url = URLConstants.URL_SEARCH_MOVIE_1 + movieId + URLConstants.URL_SEARCH_MOVIE_2;
        RestTemplate restTemplate = new RestTemplate();
        movieDTO = restTemplate.getForObject(url, MovieDTO.class);
        //movieDTO.setCredit(getCredit(movieId));
        return movieDTO;
    }

    private static CreditDTO getCredit(String movieId){
        String url = URLConstants.URL_SEARCH_CREDIT_1 + movieId + URLConstants.URL_SEARCH_CREDIT_2;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, CreditDTO .class);
    }
}
