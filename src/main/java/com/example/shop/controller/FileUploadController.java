package com.example.shop.controller;

import com.example.shop.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity create(@RequestParam MultipartFile image) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        // Nếu chưa có dir thì tạo
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }

        // Tạo file path
        Path file = CURRENT_FOLDER
                .resolve(staticPath)
                .resolve(imagePath)
                .resolve(image.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }

        // Link ảnh để lưu vào db
        String path = imagePath.resolve(image.getOriginalFilename()).toString();
        return new UserEntity();
    }
}
