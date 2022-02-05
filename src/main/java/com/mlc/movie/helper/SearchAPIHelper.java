package com.mlc.movie.helper;

import com.mlc.movie.model.movie.MovieAPI;
import com.mlc.movie.model.person.PersonAPI;
import com.mlc.movie.model.search.SearchAPI;
import com.mlc.movie.model.credit.CreditAPI;
import org.springframework.web.client.RestTemplate;

public class SearchAPIHelper {
    private final static String URL_SEARCH_MOVIES = "https://api.themoviedb.org/3/search/movie?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
    private final static String URL_SEARCH_PERSONS = "https://api.themoviedb.org/3/search/person?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
    private final static String URL_SEARCH_COMPANIES = "https://api.themoviedb.org/3/search/company?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
    private final static String URL_SEARCH_CREDIT_1 = "https://api.themoviedb.org/3/movie/";
    private final static String URL_SEARCH_CREDIT_2 = "/credits?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
    private final static String URL_SEARCH_MOVIE_1 = "https://api.themoviedb.org/3/movie/";
    private final static String URL_SEARCH_MOVIE_2 = "?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
    private final static String URL_SEARCH_PERSON_1 = "https://api.themoviedb.org/3/person/";
    private final static String URL_SEARCH_PERSON_2 = "?api_key=fcc99eaef0e81ed1ea81c258bd54257b";

    public static SearchAPI searchMoviesAPI(String query){
        String url = URL_SEARCH_MOVIES + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }
    public static SearchAPI searchMoviesLanguageAPI(String query, String language){
        String url = URL_SEARCH_MOVIES + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }
    public static SearchAPI searchMoviesPageAPI(String query, int page){
        String url = URL_SEARCH_MOVIES + "&page=" + page + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchMoviesIncludeAdultAPI(String query, boolean includeAdult){
        String url = URL_SEARCH_MOVIES + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchMoviesRegionAPI(String query, String region){
        String url = URL_SEARCH_MOVIES + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchMoviesYearAPI(String query, int year){
        String url = URL_SEARCH_MOVIES + "&year=" + year + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchMoviesReleaseYearAPI(String query, int releaseYear){
        String url = URL_SEARCH_MOVIES + "&primary_release_year=" + releaseYear + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchPersonsAPI(String query){
        String url = URL_SEARCH_PERSONS + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchPersonsLanguageAPI(String query, String language){
        String url = URL_SEARCH_PERSONS + "&language=" + language + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchPersonsPageAPI(String query, int page){
        String url = URL_SEARCH_PERSONS + "&page=" + page +"&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchPersonsIncludeAdultAPI(String query, boolean includeAdult){
        String url = URL_SEARCH_PERSONS + "&include_adult=" + includeAdult + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchPersonsRegionAPI(String query, String region){
        String url = URL_SEARCH_PERSONS + "&region=" + region + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }
    public static SearchAPI searchCompaniesAPI(String query){
        String url = URL_SEARCH_COMPANIES + "&query=" + query;
        RestTemplate restTemplate = new RestTemplate();
        SearchAPI result = restTemplate.getForObject(url, SearchAPI.class);
        return result;
    }

    public static SearchAPI searchCompaniesPageAPI(String query, int page){
        String url = URL_SEARCH_COMPANIES + "&page" + page + "&query=" + query;
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

    public static MovieAPI searchMovieAPI(String movie_id){
        String url = URL_SEARCH_MOVIE_1+movie_id+URL_SEARCH_MOVIE_2;
        RestTemplate restTemplate = new RestTemplate();
        MovieAPI result = restTemplate.getForObject(url, MovieAPI.class);
        return result;
    }

    public static PersonAPI searchPersonAPI(String person_id){
        String url = URL_SEARCH_PERSON_1+person_id+URL_SEARCH_PERSON_2;
        RestTemplate restTemplate = new RestTemplate();
        PersonAPI result = restTemplate.getForObject(url, PersonAPI.class);
        return result;
    }
}
