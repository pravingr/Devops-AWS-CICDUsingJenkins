package com.pgr.bookapi.repository;

import com.pgr.bookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
