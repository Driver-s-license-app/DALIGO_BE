package com.daligo.domain.article.service;

import com.daligo.domain.article.dao.JpaArticleRepository;
import com.daligo.domain.article.dto.ArticleReadResponseDto;
import com.daligo.domain.article.dto.ArticleWriteRequestDto;
import com.daligo.domain.article.dto.ArticleWriteResponseDto;
import com.daligo.domain.article.entity.ArticleEntity;
import com.daligo.domain.board.dao.JpaBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ArticleService {
    private final JpaArticleRepository articleRepository;
    private final JpaBoardRepository boardRepository;

    public ArticleWriteResponseDto write(ArticleWriteRequestDto dto) {
        var board = boardRepository.findById(dto.getBoard());

        if (board.isEmpty())
            return ArticleWriteResponseDto.builder()
                    .articleId(-1L)
                    .build();

        var article = ArticleEntity.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .board(board.get())
                .build();

        articleRepository.save(article);

        return ArticleWriteResponseDto.builder()
                .articleId(article.getArticleId())
                .build();
    }

    public ArticleReadResponseDto read(Long id) {
        var optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isEmpty())
            return null;

        var article = optionalArticle.get();

        return ArticleReadResponseDto.builder()
                .title(article.getTitle())
                .body(article.getBody())
                .comment(article.getComment())
                .images(article.getImages())
                .upvote(article.getUpvote())
                .view(article.getView())
                .build();
    }
}
