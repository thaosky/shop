package com.example.shop.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 128, nullable = false, unique = true)
    private String username;

    @Column(length = 64, nullable = false)  //sử dụng password encode length = 64
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(length = 64)
    private String photos;

    private boolean enabled;

    public UserEntity(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany
    @JoinTable(
            name = "user_role", // tên join table
            joinColumns = @JoinColumn(name = "user_id"), // khóa ngoại trỏ đến bảng hiện tại
            inverseJoinColumns = @JoinColumn(name = "role_id") // khóa ngoại trỏ đến thuộc tính dưới
    )
    @EqualsAndHashCode.Exclude
    private Set<RoleEntity> roleEntities = new HashSet<>();

    public void addRole(RoleEntity role) {
        roleEntities.add(role);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleEntities=" + roleEntities +
                '}';
    }
}
