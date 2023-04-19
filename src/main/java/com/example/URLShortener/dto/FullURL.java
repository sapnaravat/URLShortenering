package com.example.URLShortener.dto;

public class FullURL {
    private String fullUrl;
    public FullURL(){}
    public FullURL(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }
    public void setFullUrl(String fullUrl){
        this.fullUrl = fullUrl;
    }
}
