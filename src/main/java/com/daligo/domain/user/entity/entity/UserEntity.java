package com.daligo.domain.user.entity.entity;

import com.daligo.global.entity.BaseTimeEntity;
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
public class UserEntity extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Schema(description = "고유번호", example = "b333cf1257c5")
    private Long userId;
    @Column
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Column
    @Schema(description = "전화번호", example = "010-1111-1111")
    private String phone;
    @Column
    @Schema(description = "나이", example = "25")
    private int age;
    @Column
    @Schema(description = "지역", example = "부산")
    private String location;
    @Column
    @Schema(description = "이메일", example = "example@example.com")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return age == that.age && Objects.equals(userId, that.userId) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(location, that.location) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, phone, age, location, email);
    }

}
