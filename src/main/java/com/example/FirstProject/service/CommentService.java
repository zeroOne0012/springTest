package com.example.FirstProject.service;

import com.example.FirstProject.dto.CommentDto;
import com.example.FirstProject.entity.Article;
import com.example.FirstProject.entity.Comment;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.repository.CommentRepository;
import jakarta.transaction.Transactional;
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

    @Transactional // DB를 건드리기에 트랜잭션 처리
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패! 대상 대시글이 존재하지 않습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패! 대상 대시글이 존재하지 않습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 (및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패! 대상 대시글이 존재하지 않습니다."));

        // 댓글 삭제
        commentRepository.delete(target);

        // 삭제된 댓글 DTO로 변경하여 반환
        return CommentDto.createCommentDto(target);
    }
}
