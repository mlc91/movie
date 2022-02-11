package com.mlc.movie.helper;

import com.mlc.movie.URLConstants;
import com.mlc.movie.model.credit.CreditDTO;
import com.mlc.movie.model.movie.MovieDTO;
import com.mlc.movie.model.person.Person;
import com.mlc.movie.model.person.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.mlc.movie.model.person.PersonDTO.setPersonFromPersonDTO;
import static com.mlc.movie.util.URLHelper.urlBuilderWithId;

public class ProgramHelper {

    public static Person getPersonFromAPI(Long tmdbId){
        String url = urlBuilderWithId(tmdbId, URLConstants.URL_SEARCH_PERSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonDTO> personDTO = restTemplate.getForEntity(url, PersonDTO.class);
        return setPersonFromPersonDTO(personDTO.getBody());
    }








//    public static ResponseEntity<MovieDTO> getMovieFromAPI(String url, Long movieId){
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForEntity(url, MovieDTO.class);
//    }
//
//    public static CreditDTO getCreditFromAPI(String url, Long movieId){
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, CreditDTO.class);
//    }

}
