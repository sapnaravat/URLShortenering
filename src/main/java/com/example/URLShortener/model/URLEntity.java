package com.example.URLShortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "url")
@Table(name = "url")
public class URLEntity {
    private Long id;
    @Column(name = "full_url")
    @Getter
    @Setter
    private String fullUrl;
    @Column(name = "short_url")
    @Getter
    @Setter
    private  String shortUrl;

    public URLEntity(){}
    public  URLEntity(Long id , String fullUrl , String shortUrl){
        this.id = id;
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
    }
    public  URLEntity(String fullUrl){
        this.fullUrl = fullUrl;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){
        return id;
    }
    public  void setId(Long id){
        this.id = id;
    }
}

