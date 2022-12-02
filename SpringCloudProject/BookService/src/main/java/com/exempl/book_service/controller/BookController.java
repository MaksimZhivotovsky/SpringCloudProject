package com.exempl.book_service.controller;

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
@RequestMapping(value = "/V1/librarys/{libraryId}/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<Book>> getBooks() {
		try {
			List<Book> books = bookService.getBooks();
			log.info("BookController.getBooks : {}", books);
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			log.info("BookController.getBooks: {} ", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Book> saveBook(
			@PathVariable("libraryId") Long libraryId,
			@RequestBody Book book) {
		try {
			bookService.saveBook(libraryId, book);
			log.info("BookController.saveBook : {}", book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("BookController.saveBook: {} ", HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	
	@PutMapping(value = "/{bookId}")
	public ResponseEntity<Book> updateBook(
			@PathVariable("libraryId") Long libraryId,
			@PathVariable("bookId") Long bookId,
			@RequestBody Book book) {
		try {
			bookService.updateBook(libraryId, bookId, book);
			log.info("BookController.updateBook: {} ", HttpStatus.OK);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			log.info("BookController.updateBook: {} ", HttpStatus.NOT_MODIFIED);
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
//	@GetMapping(value = "/{bookId}")
//	public ResponseEntity<Book> getById(
//			@PathVariable("libraryId") Long libraryId,
//			@PathVariable("bookId") Long bookId) {
//		try {
//			Book book = bookService.findById(libraryId, bookId);
//			log.info("getById : {}", book);
//			return new ResponseEntity<>(book, HttpStatus.OK);
//		} catch (Exception e) {
//			log.info("BookController.getById: {} ", HttpStatus.NOT_FOUND);
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping(value = "/{bookId}")
	public Book getById(
			@PathVariable("libraryId") Long libraryId,
			@PathVariable("bookId") Long bookId) {
		return bookService.findById(libraryId, bookId);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookId") Long bookId) {
		try {
			bookService.deleteBook(bookId);
			log.info("BookController.saveBook: {} ", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			log.info("BookController.deleteBook: {} ", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
