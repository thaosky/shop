package com.example.shop.service;

import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) repository.findAll();
    }

    public UserEntity saveUser(UserEntity user) {
      Optional<UserEntity> optionalUserEntity =  repository.findById(user.getId());
        UserEntity savedUser;
        if (optionalUserEntity.isPresent()) {
          BeanUtils.copyProperties(user, optionalUserEntity.get());
      }

        repository.save(savedUser);
    }
}
