package com.example.FirstProject.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.dto.CommentDto;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션 (Simple Logging Facade for Java) // facade(파사드): 정면
public class ArticleController {
    @Autowired
    private CommentService commentService;

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 연결!
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());
        log.info(form.toString());

        // 1, Dto를 변환, Entity!
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        log.info("id = " + id);

        // ++ 댓글 불러와 등록
        List<CommentDto> commentDtos = commentService.comments(id);
        model.addAttribute("commentDtos", commentDtos);

        // 1: id로 데이터를 가져옴
//        자바 8이 아니면 안되는 코드
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);


        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1: 모든 Article 가져옴
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지 설정
        return "articles/index"; // articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){ // @GetMapping의 {id}와 Long id -> id 이름 같아야 함

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

//    @PatchMapping
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        // 1. dto 엔티티로
        Article articleEntity = form.toEntity();

        // 2. 엔티티 db로 저장
        // 2-1. db에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터에 값을 갱신
        if(target!=null){
            articleRepository.save(articleEntity); // Entity DB로 갱신
        }

        // 3. 수정 결과 페이지로 리다이렉트
//        return "redirect:/articles/{articleEntity.getId()}"; // 안됨
        return "redirect:/articles/" + articleEntity.getId();
    }

//    @DeleteMapping
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        // 1: 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);

        // 2: 대상을 삭제 한다.
        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!"); // 휘발성 데이터
        }

        // 3: 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles";
    }

}
