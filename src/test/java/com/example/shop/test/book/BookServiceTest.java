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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    void getAll_shouldReturnSuccess() {
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
    void getById_shouldReturnSuccess() throws BookNotFoundException {
        // Bước 1: Tạo data mock
        Book mockBook = new Book(1L, "Book 1");

        // Bước 2: Định nghĩa hành vi của bookRepository
        when(bookRepository.findById(anyLong()))
                .thenReturn(Optional.of(mockBook));

        // Bước 3: Call service method
        Book actual = bookService.getById(1L);

        // Bước 4: Kiểm tra kết quả
        assertThat(mockBook.getId()).isEqualTo(actual.getId());
        assertThat(mockBook.getName()).isEqualTo(actual.getName());
        verify(bookRepository).findById(anyLong());
    }

    @Test
    void getById_whenIdNotExist_ThrowException() {
        // Bước 1: Tạo input
        Long idNotExist = 3L;
        // Bước 2: Định nghĩa hành vi của repository
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Bước 3 + 4: Call service method đồng thời kiểm tra kết quả
        assertThatThrownBy(() -> bookService.getById(idNotExist))
                .isInstanceOf(BookNotFoundException.class);
        verify(bookRepository).findById(anyLong());
    }
}

