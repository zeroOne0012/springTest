package com.example.FirstProject.service;

import com.example.FirstProject.dto.CommentDto;
import com.example.FirstProject.entity.Comment;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // Article 데이터도 DB에서 가져와야 함


    public List<CommentDto> comments(Long articleId) {

//        // 1. stream() 문법 미사용
//
//        // 댓글 목록 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 엔티티 -> DTO 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i=0; i<comments.size(); i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//
//        // 반환
//        return dtos;

        // 2. stream() 문법
        // 조회, 변환, 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
}
