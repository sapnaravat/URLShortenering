package com.example.URLShortener.exception;

import lombok.Getter;
import lombok.Setter;

public class InvalidURLException {
    @Getter
    @Setter
    private String field;
    @Getter
    @Setter
    private  String value;
    @Getter
    @Setter
    private  String message;
    public InvalidURLException(String field , String value , String message){
        this.field = field;
        this.value = value;
        this.message = message;
    }
}
