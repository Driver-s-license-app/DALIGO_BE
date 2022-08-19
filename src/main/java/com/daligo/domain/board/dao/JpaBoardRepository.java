package com.daligo.domain.board.dao;

import com.daligo.domain.article.entity.ArticleEntity;
import com.daligo.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<BoardEntity, Long> {
}
