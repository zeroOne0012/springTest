package com.example.FirstProject.repository;

import com.example.FirstProject.entity.Article;
import jakarta.annotation.Nullable;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
