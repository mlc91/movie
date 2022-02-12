package com.mlc.movie.helper;

import com.mlc.movie.constants.URLConstants;

/**
 * The URLHelper is a Helper class that contains all the
 * necessary methods to build a URL.
 */
public class URLHelper {

    public static String urlBuilder(String url){
        return URLConstants.URL_API + url + URLConstants.URL_API_KEY;
    }

    public static String urlBuilderWithId(Long id, String url){
        return URLConstants.URL_API + url + id + URLConstants.URL_API_KEY;
    }

    public static String urlBuilderTwoParamWithId(Long id, String url1, String url2){
        return URLConstants.URL_API + url1 + id + url2 + URLConstants.URL_API_KEY;
    }
}
