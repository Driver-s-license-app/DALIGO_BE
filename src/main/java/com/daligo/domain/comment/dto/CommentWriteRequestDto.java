package com.daligo.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentWriteRequestDto {
    @NotNull
    private String writer;
    @NotNull
    private String comment;
    @NotNull
    private int upvote;
    @NotNull
    private int childCount;
    @NotNull
    private Long parentId;
    //@NotNull
    //private Long user;
    @NotNull
    private Long article;
}
