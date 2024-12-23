package com.example.FirstProject.repository;

import com.example.FirstProject.entity.Article;
import com.example.FirstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */

//            // 입력 데이터 준비
//            Long articleId = 4L;
//
//            // 실제 수행
//            List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//            // 예상하기
//            Article target = new Article(4L, "당신의 인생 영화는?", "댓글로 알려주세요");
//            Comment a = new Comment(1L, target, "Kim", "겨울왕국");
//            Comment b = new Comment(2L, target, "Lee", "즐겁다");
//            Comment c = new Comment(3L, target, "Park", "꺄르륵");
//            List<Comment> expected = Arrays.asList(a, b, c);
//
//            // 검증
//            assertEquals(expected.toString(), comments.toString());

            String expected = null;
            String comments = null;
            assertEquals(expected, comments);



    }

    @Test
    void findByNickname() {
    }
}