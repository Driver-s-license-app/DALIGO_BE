package com.daligo.domain.user.entity;

import com.daligo.global.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Long userId;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private int age;
    @Column
    private String location;
    @Column
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
