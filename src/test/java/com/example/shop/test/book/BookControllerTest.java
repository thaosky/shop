package com.example.shop.test.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    BookServiceImpl bookService;

    @InjectMocks
    BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .build();
    }

    @Test
    void getAllBooks_shouldReturnSuccess() throws Exception {
        List<Book> mockBooks = List.of(
                new Book(1L, "Book 1"),
                new Book(2L, "Book 2")
        );

        when(bookService.getAll()).thenReturn(mockBooks);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockBooks.size()))
                .andDo(print());
    }

    @Test
    void getAllBooks_shouldReturnNoContent() throws Exception {
        when(bookService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void getBookById_shouldReturnSuccess() throws Exception {
        Book bookMock = new Book(1L, "Book mock");
        when(bookService.getById(anyLong())).thenReturn(bookMock);

        mockMvc.perform(get("/books/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookMock.getId()))
                .andExpect(jsonPath("$.name").value(bookMock.getName()))
                .andDo(print());
    }
}

