package com.daligo.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CommentReadResponseDto {
    private Long commentId;
    private String writer;
    private String comment;
    private int upvote;
    private int childCount;
    private Long parentId;
}
