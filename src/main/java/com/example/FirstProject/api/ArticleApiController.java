package com.example.FirstProject.api;

import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI 용 컨트롤러! 데이터(JSON)를 반환
public class ArticleApiController {
    @Autowired // DI; Dependency Injection; 외부에서 가져옴
    private ArticleService articeService;

//    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articeService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articeService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ // ArticleForm 이름이 ArticleDto 등이 더 적절할 것
        Article created = articeService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //PATCH
    @PatchMapping("/api/articles/{id}")
    //ResponseEntity로 상태코드를 함께 담아 보낼 수 있음
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody ArticleForm dto){
        Article updated = articeService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build(): // 204
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백 !
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articeService.createArticles(dtos);
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
