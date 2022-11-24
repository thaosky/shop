package com.example.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ERole code;

    @Column(length = 40)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    public RoleEntity(ERole code, String description) {
        this.code = code;
        this.description = description;
    }

    public RoleEntity(ERole code) {
        this.code = code;
    }

    public RoleEntity(ERole code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public RoleEntity(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
