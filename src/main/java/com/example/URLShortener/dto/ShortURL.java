package com.example.URLShortener.dto;

public class ShortURL {
    private String shortUrl;
    public  ShortURL(){}
    public ShortURL(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    public String getShortUrl(){
        return shortUrl;
    }
    public void setShortUrl(String shortUrl){
        this.shortUrl = shortUrl;
    }


}
