package com.example.URLShortener.controller;

import com.example.URLShortener.common.URLUtil;
import com.example.URLShortener.dto.FullURL;
import com.example.URLShortener.dto.ShortURL;
import com.example.URLShortener.exception.InvalidURLException;
import com.example.URLShortener.service.URLService;
import io.micrometer.core.instrument.config.validate.Validated;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

@RestController
public class URLController {
protected  final URLService urlService;

@Autowired
public URLController(URLService urlService){
    this.urlService = urlService;
}
@PostMapping("/shorten")
    public ResponseEntity<Object> saveUrl(@RequestBody FullURL fullURL , HttpServletRequest request){
    UrlValidator urlValidator = new UrlValidator(new String[]{"http" , "https"});
     String url = fullURL.getFullUrl();
     if(!urlValidator.isValid(url)){
         InvalidURLException exception = new InvalidURLException("url" , fullURL.getFullUrl(),"Invalid Url");
     return  ResponseEntity.badRequest().body(exception);
     }
     String baseUrl = null;
     try {
         baseUrl = URLUtil.getBaseUrl(request.getRequestURL().toString());
     }
     catch(MalformedURLException e){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request url is invalid" , e);
     }
    ShortURL shortURL = urlService.getShortUrl(fullURL);
     shortURL.setShortUrl(baseUrl+shortURL.getShortUrl());
     return new ResponseEntity<>(shortURL , HttpStatus.OK);
}
@GetMapping("/{shortenString}")
    public void redirectToFullUrl(HttpServletResponse response , @PathVariable String shortenString){
    try
    {
        FullURL fullURL = urlService.getFullUrl(shortenString);
        response.sendRedirect(fullURL.getFullUrl());
    }
    catch (NoSuchElementException e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND , "url not found" , e);
    }
    catch (IOException e){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "could not redirect to fullurl" , e);
    }
}
}
