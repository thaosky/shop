package com.example.shop.test.book;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException("Book does not exist");
        }
        return optionalBook.get();
    }

}

