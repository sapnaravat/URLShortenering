package com.example.URLShortener.service;

import com.example.URLShortener.common.ShorteningUtil;
import com.example.URLShortener.dto.FullURL;
import com.example.URLShortener.dto.ShortURL;
import com.example.URLShortener.model.URLEntity;
import com.example.URLShortener.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.List;


@Service
public class URLService {
    private final URLRepository urlRepo;

    @Autowired
    public URLService(URLRepository urlRepo){
        this.urlRepo = urlRepo;
    }
    private URLEntity get(Long id){
        URLEntity urlEntity = urlRepo.findById(id).get();
        return urlEntity;
    }
    public FullURL getFullUrl(String shortUrl){
        Long id = ShorteningUtil.strToId(shortUrl);
        return new FullURL(this.get(id).getFullUrl());
    }
    private URLEntity save(FullURL fullURL){
        return urlRepo.save(new URLEntity(fullURL.getFullUrl()));
    }
    public ShortURL getShortUrl(FullURL fullURL){
        List<URLEntity> savedUrls = null;
        savedUrls = checkFullUrlAlreadyExists(fullURL);
        URLEntity savedUrl = null;
        if(savedUrls.isEmpty()){
            savedUrl = this.save(fullURL);
        }
        else{
            savedUrl = savedUrls.get(0);
        }
        String shortUrlText = ShorteningUtil.idToStr(savedUrl.getId());
        return new ShortURL(shortUrlText);
    }
    private List<URLEntity> checkFullUrlAlreadyExists(FullURL fullURL){
        return urlRepo.findUrlByFullUrl(fullURL.getFullUrl());
    }
}
