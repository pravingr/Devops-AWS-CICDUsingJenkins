package com.pgr.bookapi.service;

import com.pgr.bookapi.model.Book;
import com.pgr.bookapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleBook = new Book(1L, "Test Book", "Author", 25.5);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook));

        List<Book> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        Book book = bookService.getBookById(1L);

        assertNotNull(book);
        assertEquals("Author", book.getAuthor());
    }

    @Test
    void testAddBook() {
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);

        Book saved = bookService.addBook(sampleBook);

        assertEquals("Test Book", saved.getTitle());
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);

        Book updated = bookService.updateBook(1L, sampleBook);

        assertEquals("Test Book", updated.getTitle());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
