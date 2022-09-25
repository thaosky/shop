package com.example.shop.test.book;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    @Order(1)
    void saveBook_shouldReturnSuccess() {
        Book book = new Book("Book 1");
        bookRepository.save(book);
        assertThat(book.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    void getBookById_shouldReturnSuccess() {
        Book book = bookRepository.findById(1L).get();
        assertThat(book.getId()).isEqualTo(1L);
    }

}