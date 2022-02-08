package com.mlc.movie.util;

import com.mlc.movie.URLConstants;

public class URLHelper {


    public static String urlBuilder(String url){
        return URLConstants.URL_API + url + URLConstants.API_KEY;
    }

    public static String urlBuilderWithId(Long id, String url){
        return URLConstants.URL_API + url + id + URLConstants.API_KEY;
    }

    public static String urlBuilderTwoParamWithId(Long id, String url1, String url2){
        return URLConstants.URL_API + url1 + id + url2 + URLConstants.API_KEY;
    }
}
