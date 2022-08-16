package com.daligo.domain.comment.service;

import com.daligo.domain.article.dao.JpaArticleRepository;
import com.daligo.domain.article.entity.ArticleEntity;
import com.daligo.domain.comment.dao.JpaCommentRepository;
import com.daligo.domain.comment.dto.CommentReadResponseDto;
import com.daligo.domain.comment.dto.CommentWriteRequestDto;
import com.daligo.domain.comment.dto.CommentWriteResponseDto;
import com.daligo.domain.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class CommentService {
    private final JpaCommentRepository commentRepository;
    private final JpaArticleRepository articleRepository;

    public CommentWriteResponseDto write(CommentWriteRequestDto dto) {
        Optional<ArticleEntity> article = articleRepository.findById(dto.getParentId());

        if (article.isEmpty()) {
            return CommentWriteResponseDto.builder()
                    .commentId(-1L)
                    .build();
        }

        CommentEntity comment = CommentEntity.builder()
                .writer(dto.getWriter())
                .comment(dto.getComment())
                .upvote(dto.getUpvote())
                .childCount(dto.getChildCount())
                .parentId(dto.getParentId())
                //.user(dto.getUser())
                .article(article.get())
                .build();

        commentRepository.save(comment);

        return CommentWriteResponseDto.builder()
                .commentId(comment.getCommentId())
                .build();
    }

    public CommentReadResponseDto read(Long id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);

        if (comment.isEmpty()) {
            return null;
        }

        CommentEntity commentEntity = comment.get();

        return CommentReadResponseDto.builder()
                .writer(commentEntity.getWriter())
                .comment(commentEntity.getComment())
                .upvote(commentEntity.getUpvote())
                .childCount(commentEntity.getChildCount())
                .commentId(commentEntity.getParentId())
                .build();
    }
}
