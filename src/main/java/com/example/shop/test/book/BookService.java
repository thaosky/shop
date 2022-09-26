package com.example.shop.test.book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(Long id) throws BookNotFoundException;
}

