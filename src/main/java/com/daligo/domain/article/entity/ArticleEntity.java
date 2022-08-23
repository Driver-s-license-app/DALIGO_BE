package com.daligo.domain.article.entity;

import com.daligo.domain.board.entity.BoardEntity;
import com.daligo.domain.user.entity.UserEntity;
import com.daligo.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity  extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long articleId;
    @Column
    private String title;
    @Column
    private String body;
    @Column
    private Long view;
    @Column
    private Long upvote;
    @Column
    private Long comment;
    @Column
    private String images;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="board_id", nullable = false)
    private BoardEntity board;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(title, that.title) && Objects.equals(body, that.body) && Objects.equals(view, that.view) && Objects.equals(upvote, that.upvote) && Objects.equals(comment, that.comment) && Objects.equals(images, that.images) && Objects.equals(user, that.user) && Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, title, body, view, upvote, comment, images, user, board);
    }

}
