package com.daligo.domain.comment.entity;

import com.daligo.domain.article.entity.ArticleEntity;
import com.daligo.domain.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;
    @Column
    private String writer;
    @Column
    private String comment;
    @Column
    private int upvote;
    @Column
    private int childCount;
    @Column
    private int parentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

}
