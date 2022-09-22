package com.example.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    // because we use a password encoded for user's password, encoded pass length = 64
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(length = 64)
    private String photos;

    private boolean enabled;

    public UserEntity(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany
    @JoinTable(
            name = "users_roles", // tên join table
            joinColumns = @JoinColumn(name = "user_id"), // khóa ngoại trỏ đến bảng hiện tại
            inverseJoinColumns = @JoinColumn(name = "role_id") // khóa ngoại trỏ đến thuộc tính dưới
    )
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
