package com.pgr.bookapi.service;

import com.pgr.bookapi.model.Book;
import com.pgr.bookapi.repository.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks() {
    	logger.info("Fetching all books");
        return repository.findAll();
    }

    public Book getBookById(Long id) {
    	logger.info("Fetching book with id {}", id);
        return repository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
    	logger.info("Adding new book: {}", book);
        return repository.save(book);
    }

    public Book updateBook(Long id, Book book) {
    	logger.info("Updating book with id {}: {}", id, book);
        book.setId(id);
        return repository.save(book);
    }

    public void deleteBook(Long id) {
    	 logger.info("Deleting book with id {}", id);
        repository.deleteById(id);
    }
}
