package com.example.shop.service;

import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import org.apache.catalina.User;
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

    public UserEntity updateUser(UserEntity user) throws Exception {
        Optional<UserEntity> optionalUserEntity = repository.findById(user.getId());
        UserEntity savedUser = optionalUserEntity.orElseThrow(Exception::new);
        BeanUtils.copyProperties(user, savedUser);
        return repository.save(savedUser);
    }

    public UserEntity createUser(UserEntity user) {
        return repository.save(user);
    }
}
