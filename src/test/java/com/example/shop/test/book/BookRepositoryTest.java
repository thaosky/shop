package com.example.shop.test.book;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @Order(1)
    @Rollback(value = false)
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

    @Test
    @Order(3)
    void getAllBook_shouldReturnSuccess(){
        List<Book> bookList = bookRepository.findAll();

        assertThat(bookList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void updateBook_shouldReturnSuccess(){
        Book book = bookRepository.findById(1L).get();
        book.setName("Book update name");

        Book bookUpdated = bookRepository.save(book);
        assertThat(bookUpdated.getName()).isEqualTo("Book update name");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    void deleteBook_shouldReturnSuccess() {
        Book book = bookRepository.findById(1L).get();
        bookRepository.delete(book);

        Optional<Book> optionalBook = bookRepository.findById(1L);
        Book book1 = null;
        if (optionalBook.isPresent()) {
            book1 = optionalBook.get();
        }
        assertThat(book1).isNull();
    }
}
