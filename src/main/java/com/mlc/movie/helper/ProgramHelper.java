package com.mlc.movie.helper;

import com.mlc.movie.constants.URLConstants;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.Movie;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.mlc.movie.constants.URLConstants.URL_SEARCH_CREDITS;
import static com.mlc.movie.model.movie.MovieDTO.setMovieFromMovieDTO;
import static com.mlc.movie.model.person.PersonDTO.setPersonFromPersonDTO;
import static com.mlc.movie.helper.URLHelper.urlBuilderTwoParamWithId;
import static com.mlc.movie.helper.URLHelper.urlBuilderWithId;

public class ProgramHelper {

    public static Person getPersonFromAPI(Long tmdbId){
        String url = urlBuilderWithId(tmdbId, URLConstants.URL_SEARCH_PERSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonDTO> personDTO = restTemplate.getForEntity(url, PersonDTO.class);
        return setPersonFromPersonDTO(personDTO.getBody());
    }

    public static Movie getMovieFromAPI(Long tmdbId){
        String urlMovie = urlBuilderWithId(tmdbId, URLConstants.URL_SEARCH_MOVIE);
        String urlCredit = urlBuilderTwoParamWithId(tmdbId, URLConstants.URL_SEARCH_MOVIE, URL_SEARCH_CREDITS);
        RestTemplate restTemplate1 = new RestTemplate();
        RestTemplate restTemplate2 = new RestTemplate();
        MovieDTO movieDTO = restTemplate1.getForEntity(urlMovie, MovieDTO.class).getBody();
        CreditDTO creditDTO = restTemplate2.getForEntity(urlCredit, CreditDTO.class).getBody();
        movieDTO.setCreditDTO(creditDTO);
        return setMovieFromMovieDTO(movieDTO);
    }

    public static boolean isGuest(Authentication authentication){
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public static Map<String, Object> makeMap(String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
