package com.exempl.book_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exempl.book_service.model.Book;
import com.exempl.book_service.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/API/V1/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@GetMapping
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	
	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		try {
			bookService.saveBook(book);
			log.info("BookController.saveBook : {}", book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/title/{title}")
	public ResponseEntity<List<Book>> findByTitle(@PathVariable("title") String title) {
		try {
			List<Book> books = new ArrayList<>();
			if(title != null) {
				bookService.findByTitle(title).forEach(books::add);
			}
			log.info("BookController.findByTitle : {}", books);
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,
			@RequestBody Book book) {
		Book dataBook = bookService.findById(id);
		if(dataBook != null) {
			dataBook.setTitle(book.getTitle());
			dataBook.setAuthor(book.getAuthor());
			dataBook.setPages(book.getPages());
			log.info("updateBook : {}" , dataBook);
			return new ResponseEntity<>(bookService.saveBook(dataBook), HttpStatus.OK);
		} else {
			log.info("BookController.updateBook: {} ", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
		try {
			Book book = bookService.findById(id);
			log.info("getById : {}", book);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			log.info("BookController.getById: {} ", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
		try {
			bookService.deleteBook(id);
			log.info("BookRestController.saveBook: {} ", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			log.info("BookRestController.deleteBook: {} ", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
