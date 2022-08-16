package com.daligo.domain.comment.entity;

import com.daligo.domain.article.entity.ArticleEntity;
import com.daligo.domain.user.entity.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Schema(description = "고유번호", example = "b333cf1257c5")
    private Long commentId;
    @Column
    @Schema(description = "작성자", example = "홍길동")
    private String writer;
    @Column
    @Schema(description = "댓글", example = "댓글입니다.")
    private String comment;
    @Column
    @Schema(description = "따봉수", example = "3")
    private int upvote;
    @Column
    @Schema(description = "대댓글수", example = "5")
    private int childCount;
    @Column
    @Schema(description = "부모댓글아이디", example = "b333cf1257c5123")
    private Long parentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return upvote == that.upvote && childCount == that.childCount && parentId == that.parentId && Objects.equals(commentId, that.commentId) && Objects.equals(writer, that.writer) && Objects.equals(comment, that.comment) && Objects.equals(user, that.user) && Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, writer, comment, upvote, childCount, parentId, user, article);
    }
}
