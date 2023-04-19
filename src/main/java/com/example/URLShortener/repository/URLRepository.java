package com.example.URLShortener.repository;

import com.example.URLShortener.model.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLRepository extends JpaRepository<URLEntity,Long> {

    List<URLEntity> findUrlByFullUrl(String fullUrl);
}
