package com.example.shop.repository;

import com.example.shop.entity.RoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTest {
    @Autowired
   private RoleRepository repository;

    @Test
    public void testCreateFirstRole() {
        RoleEntity roleAdmin = new RoleEntity("Admin", "Manage everything");
        RoleEntity savedRole = repository.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

}