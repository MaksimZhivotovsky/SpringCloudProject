package com.exempl.max.sevice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exempl.max.model.Book;
import com.exempl.max.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

	private final BookRepository bookRepository;
	
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}
