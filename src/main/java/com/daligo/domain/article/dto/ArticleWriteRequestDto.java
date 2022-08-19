package com.daligo.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleWriteRequestDto {
    @NotNull
    private Long board;
    @NotNull
    private String title;
    @NotNull
    private String body;
}
