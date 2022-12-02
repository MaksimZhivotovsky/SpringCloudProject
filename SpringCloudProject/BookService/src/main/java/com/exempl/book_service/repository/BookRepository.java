package com.exempl.book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exempl.book_service.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

