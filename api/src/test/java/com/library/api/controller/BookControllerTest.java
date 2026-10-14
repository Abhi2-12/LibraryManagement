package com.library.api.controller;

import com.library.api.model.Book;
import com.library.api.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    void getAllBooksReturnsSuccess() throws Exception {

        when(bookRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void missingBookReturnsNotFound() throws Exception {

        when(bookRepository.findById(9999))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void blankTitleReturnsBadRequest() throws Exception {

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "   ",
                        "author": "Test Author",
                        "year": 2024,
                        "isbn": "123456",
                        "borrowed": false
                    }
                    """))
                .andExpect(status().isBadRequest());
    }

    
    @Test
    void createBookReturnsSuccess() throws Exception {

        when(bookRepository.save(org.mockito.ArgumentMatchers.any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Test Book",
                        "author": "Test Author",
                        "year": 2024,
                        "isbn": "TEST123",
                        "borrowed": false
                    }
                    """))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void searchBooksByTitleReturnsSuccess() throws Exception {

        when(bookRepository.findByTitleContainingIgnoreCase("Java"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/books/search")
                .param("title", "Java"))
                .andExpect(status().isOk());
    }

}
