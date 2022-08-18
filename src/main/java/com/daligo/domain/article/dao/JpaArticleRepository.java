package com.daligo.domain.article.dao;

import com.daligo.domain.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArticleRepository  extends JpaRepository<ArticleEntity, Long> {
}
