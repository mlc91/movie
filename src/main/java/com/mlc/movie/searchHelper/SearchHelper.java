package com.mlc.movie.searchHelper;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.genre.GenreListDTO;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.PersonDTO;
import org.springframework.web.client.RestTemplate;

import static com.mlc.movie.URLConstants.*;
import static com.mlc.movie.util.URLHelper.*;

public class SearchHelper {

    public static MovieDTO getMovieFromAPI(Long movieId){
        String url = urlBuilderWithId(movieId, URLConstants.URL_SEARCH_MOVIE);
        RestTemplate restTemplate = new RestTemplate();
        MovieDTO movieDTO = restTemplate.getForObject(url, MovieDTO.class);
        CreditDTO creditDTO = getCreditFromAPI(movieId);
        movieDTO.setCreditDTO(creditDTO);
        return movieDTO;
    }

    public static CreditDTO getCreditFromAPI(Long movieId){
        String url = urlBuilderTwoParamWithId(movieId, URLConstants.URL_SEARCH_MOVIE, URL_SEARCH_CREDITS);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, CreditDTO.class);
    }

    public static GenreListDTO getGenresFromAPI(){
        String url = urlBuilder(URLConstants.URL_SEARCH_GENRES);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, GenreListDTO.class);
    }
}
