package com.example.FirstProject.service;

import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

//    @Autowired ArticleService articleService;
//
//    @Test
//    void index() {
//        // 예상
//        Article a = new Article(1L, "aaa", "bbb");
//        Article b = new Article(2L, "aaaa", "bbbb");
//        Article c = new Article(3L, "aaaaa", "bbbbb");
//        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));
//
//        // 실제
//        List<Article> articles = articleService.index();
//
//        // 비교 (검증)
//        assertEquals(expected.toString(), articles.toString());
//    }
//
//    @Test
//    void show_존재하지_않는_id_입력() {
//        // 예상
//        Long id = -1L;
//        Article expected = null;
//
//        // 실제
//        Article article = articleService.show(id);
//
//        //비교
//        assertEquals(expected, article); // null은 toString() X
//    }
//
//    @Test
//    void create_성공___title과_content만_있는_dto_입력() {
//        //예상
//        String title = "abcd";
//        String content = "abcde";
//        ArticleForm dto = new ArticleForm(null, title, content);
//        Article expected = new Article(4L, title, content);
//
//        // 실제
//        Article article = articleService.create(dto);
//
//        // 비교
//        assertEquals(expected.toString(), article.toString());
//
//    }
}