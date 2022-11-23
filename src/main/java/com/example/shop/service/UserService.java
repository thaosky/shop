package com.example.shop.service;

import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity updateUser(UserEntity user) throws Exception {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(user.getId());
        UserEntity savedUser = optionalUserEntity.orElseThrow(Exception::new);
        BeanUtils.copyProperties(user, savedUser);
        return this.userRepository.save(savedUser);
    }

    public UserEntity createUser(UserEntity user) {
        encodePassword(user);
        return this.userRepository.save(user);
    }

    private void encodePassword(UserEntity user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isDuplicateEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
