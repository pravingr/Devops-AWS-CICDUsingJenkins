package com.pgr.bookapi.controller;

import com.pgr.bookapi.model.Book;
import com.pgr.bookapi.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getAllBooks() {
    	logger.debug("GET /books called");
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
    	logger.debug("GET /books/{} called", id);
        return service.getBookById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
    	logger.debug("POST /books called");
        return service.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
    	logger.debug("PUT /books/{} called", id);
    	return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
    	logger.debug("DELETE /books/{} called", id);
        service.deleteBook(id);
    }
}
