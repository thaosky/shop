package com.example.shop.test.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    void getAll_ShouldReturnSuccess() {
        // Bước 1: Tạo data mock
        List<Book> mockBook = List.of(
                new Book(1L, "Book 1"),
                new Book(2L, "Book 2")
        );

        // Bước 2: Định nghĩa hành vi của bookRepository
        when(bookRepository.findAll()).thenReturn(mockBook);

        // Bước 3: Call service method
        List<Book> actualBook = bookService.getAll();

        // Bước 4: Kiểm tra kết quả
        assertThat(mockBook.size()).isEqualTo(actualBook.size());
        verify(bookRepository).findAll();

    }

    @Test
    void getById_WhenIdNotExist_ThrowException() {
        // Bước 1:
        Long idNotExist = 3L;
        // Bước 2: Định nghĩa hành vi của repository
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getById(idNotExist)).isInstanceOf(BookNotFoundException.class);
        verify(bookRepository).findById(any(Long.class));
    }
}