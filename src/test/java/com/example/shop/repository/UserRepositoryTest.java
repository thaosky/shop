package com.example.shop.repository;

import com.example.shop.entity.RoleEntity;
import com.example.shop.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void createUser_withOneRole_shouldSuccess() {
        RoleEntity roleAdmin = testEntityManager.find(RoleEntity.class, 1);
        UserEntity thao = new UserEntity("thaohsk@gmail.com", "thao123", "Nguyen", "Thao");
        thao.addRole(roleAdmin);

        UserEntity savedUser = userRepository.save(thao);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void createUser_withTwoRole_shouldSuccess() {
        RoleEntity roleAssistant = new RoleEntity(5);
        RoleEntity roleEditor = new RoleEntity(3);
        UserEntity lamlam = new UserEntity("lam@gmail.com", "lam123", "Nguyen", "Lam Lam");
        lamlam.addRole(roleAssistant);
        lamlam.addRole(roleEditor);

        UserEntity savedUser = userRepository.save(lamlam);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void listAllUser_shouldSuccess() {
        Iterable<UserEntity> list = userRepository.findAll();
        list.forEach(userEntity -> System.out.println(userEntity));
    }

    @Test
    public void getUserById_shouldSuccess() {
        UserEntity userEntity = userRepository.findById(1).get();
        System.out.println(userEntity);
        assertThat(userEntity).isNotNull();
    }

    @Test
    public void updateUserDetail_shouldSuccess() {
        UserEntity userEntity = userRepository.findById(1).get();
        userEntity.setEnabled(true);
        userEntity.setEmail("update@gmail.com");
        userRepository.save(userEntity);
    }

    @Test
    public void updateUserRole_shouldSuccess() {
        UserEntity userEntity = userRepository.findById(2).get();

        RoleEntity roleEditor = new RoleEntity(3);
        RoleEntity roleSalesPerson = new RoleEntity(2);
        userEntity.getRoleEntities().remove(roleEditor);
        userEntity.getRoleEntities().add(roleSalesPerson);

        userRepository.save(userEntity);
    }

    @Test
    public void deleteUser_shouldSuccess() {
        userRepository.deleteById(2);
        userRepository.findById(2);
    }

    @Test
    public void findUserByEmail_shouldSuccess() {
        String email = "thaohsk@gmail.com";
        boolean isDuplicate = userRepository.findByEmail(email).isPresent();
        assertThat(isDuplicate).isTrue();
    }
}