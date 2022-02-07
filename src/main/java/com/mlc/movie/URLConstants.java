package com.mlc.movie;


public class URLConstants {
    public final static String URL_API = "https://api.themoviedb.org/3/";
    public final static String API_KEY = "api_key=fcc99eaef0e81ed1ea81c258bd54257b";

    public static String urlBuilder(String url){
        return URLConstants.URL_API + url + URLConstants.API_KEY;
    }

    public static String urlBuilderWithId(Long id, String url){
        return URLConstants.URL_API + id + url + URLConstants.API_KEY;
    }

    public static String urlBuilderParameters(Long id, String url1, String url2){
        return URLConstants.URL_API + url1 + id + url2 + URLConstants.API_KEY;
    }

    public final static String URL_SEARCH_MOVIES = "search/movie?";
    public final static String URL_SEARCH_PERSONS = "search/person?";
    public final static String URL_SEARCH_GENRES = "genre/movie/list?";
    public final static String URL_SEARCH_COMPANIES = "search/company?";
    public final static String URL_SEARCH_MOVIE = "movie/"; //rename URL_SEARCH_MOVIE
    public final static String URL_SEARCH_CREDITS = "/credits?";
    public final static String URL_SEARCH_PERSON = "person/"; //rename URL_SEARCH_PERSON


//    public final static String URL_SEARCH_MOVIES = "search/movie?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
//    public final static String URL_SEARCH_PERSONS = "search/person?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
//    public final static String URL_SEARCH_GENRES = "https://api.themoviedb.org/3/genre/movie/list?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
//    public final static String URL_SEARCH_COMPANIES = "https://api.themoviedb.org/3/search/company?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
//    public final static String URL_SEARCH_CREDIT_1 = "https://api.themoviedb.org/3/movie/";
//    public final static String URL_SEARCH_CREDIT_2 = "/credits?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
//    public final static String URL_SEARCH_MOVIE_1 = "https://api.themoviedb.org/3/movie/";
//    public final static String URL_SEARCH_MOVIE_2 = "?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
//    public final static String URL_SEARCH_PERSON_1 = "https://api.themoviedb.org/3/person/";
//    public final static String URL_SEARCH_PERSON_2 = "?api_key=fcc99eaef0e81ed1ea81c258bd54257b";
}
