package com.pgr.bookapi.controller;

import com.pgr.bookapi.model.Book;
import com.pgr.bookapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
	@MockBean
    private BookService service;

    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book(1L, "Book", "Author", 20.0);
        Mockito.when(service.getAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Book")));
    }

    @Test
    void testAddBook() throws Exception {
        Book book = new Book(1L, "Book", "Author", 20.0);
        Mockito.when(service.addBook(any(Book.class))).thenReturn(book);

        String json = """
                {
                    "title": "Book",
                    "author": "Author",
                    "price": 20.0
                }
                """;

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Book")));
    }
}
