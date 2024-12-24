package com.example.FirstProject.api;

import com.example.FirstProject.dto.CommentDto;
import com.example.FirstProject.entity.Comment;
import com.example.FirstProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    private ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){

        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답

        return (dtos!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(dtos):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    private ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                            @RequestBody CommentDto dto){

        // 서비스에게 위임
        CommentDto createdDto = commentService.create(articleId, dto);

        // 결과 응답
        return createdDto != null ?
                ResponseEntity.status(HttpStatus.OK).body(createdDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    private ResponseEntity<CommentDto> update(@PathVariable Long id,
                                              @RequestBody CommentDto dto){

        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);

        // 결과 응답
        return updatedDto != null ?
                ResponseEntity.status(HttpStatus.OK).body(updatedDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    private ResponseEntity<CommentDto> delete(@PathVariable Long id){

        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);

        // 결과 응답
        return deletedDto != null ?
                ResponseEntity.status(HttpStatus.OK).body(deletedDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
