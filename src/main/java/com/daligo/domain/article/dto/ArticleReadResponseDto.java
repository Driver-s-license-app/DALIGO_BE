package com.daligo.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArticleReadResponseDto {
    private String title;
    private String body;
    private Long view;
    private Long upvote;
    private Long comment;
    private String images;
}
