package com.example.shop.test.book;

public class BookNotFoundException extends Exception {
    private String errorMessage;

    public BookNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
