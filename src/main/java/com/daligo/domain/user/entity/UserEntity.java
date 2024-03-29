package com.daligo.domain.user.entity;

import com.daligo.global.entity.AuthProvider;
import com.daligo.global.entity.BaseTimeEntity;
import com.daligo.global.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString(exclude = "socialAuth")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@SecondaryTables({
        @SecondaryTable(name = "social_auth", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
})
public class UserEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long id;

    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "tel", length = 20)
    private String tel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "providerId", column = @Column(table = "social_auth", name = "provider_id")),
            @AttributeOverride(name = "provider", column = @Column(table = "social_auth", name = "provider")),
            @AttributeOverride(name = "email", column = @Column(table = "social_auth", name = "email", length = 100, nullable = false)),
            @AttributeOverride(name = "name", column = @Column(table = "social_auth", name = "name", length = 100, nullable = false)),
            @AttributeOverride(name = "imageUrl", column = @Column(table = "social_auth", name = "image_url", columnDefinition = "TEXT")),
            @AttributeOverride(name = "attributes", column = @Column(table = "social_auth", name = "attributes", columnDefinition = "TEXT")),
            @AttributeOverride(name = "ip", column = @Column(table = "social_auth", name = "ip", length = 30, nullable = false)),
    })
    private SocialAuth socialAuth;

}