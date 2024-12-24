package com.example.FirstProject.dto;

import com.example.FirstProject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") // api 이용 시 json body에 articleId 가 아닌 article_id 사용
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment c) {
        return new CommentDto(
                c.getId(),
                c.getArticle().getId(),
                c.getNickname(),
                c.getBody()
        );
    }
}
