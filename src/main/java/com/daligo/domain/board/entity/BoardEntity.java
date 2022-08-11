package com.daligo.domain.board.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long boardId;
    @Column
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardEntity that = (BoardEntity) o;
        return Objects.equals(boardId, that.boardId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, name);
    }

}
