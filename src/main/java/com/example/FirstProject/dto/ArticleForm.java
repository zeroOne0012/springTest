package com.example.FirstProject.dto;

import com.example.FirstProject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor // 생성자(모두 사용)
@ToString // toString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
