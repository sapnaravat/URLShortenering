package com.example.URLShortener.common;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtil {
    private  URLUtil(){}

    public static String getBaseUrl(String url) throws MalformedURLException {
        URL requiredUrl = new URL(url);
        String protocal = requiredUrl.getProtocol();
        String host = requiredUrl.getHost();
        int port = requiredUrl.getPort();
        if(port == -1){
            return String.format("%s://%s/",protocal,host);
        }
        else{
            return String.format("%s://%s:%d/",protocal,host,port);
        }
    }
}
