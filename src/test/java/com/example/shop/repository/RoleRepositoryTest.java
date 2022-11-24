package com.example.shop.repository;

import com.example.shop.entity.ERole;
import com.example.shop.entity.RoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

    @Test
    public void testCreateFirstRole() {
        RoleEntity roleAdmin = new RoleEntity(ERole.ROLE_ADMIN, "Manage everything");
        RoleEntity savedRole = repository.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRole() {
        RoleEntity roleSalePerson = new RoleEntity(ERole.ROLE_SALES_PERSON,
                "Manage product price, customers, shipping, orders and sales report");
        RoleEntity roleEditor = new RoleEntity(ERole.ROLE_EDITOR,
                "Manage categories, brands, products, articles and menu");
        RoleEntity roleShipper = new RoleEntity(ERole.ROLE_SHIPPER,
                "View products, view orders and update oder status");
        RoleEntity roleAssistant = new RoleEntity(ERole.ROLE_ASSISTANT,
                "Manage questions and  reviews");
        repository.saveAll(List.of(roleSalePerson, roleEditor, roleShipper, roleAssistant));
    }


}