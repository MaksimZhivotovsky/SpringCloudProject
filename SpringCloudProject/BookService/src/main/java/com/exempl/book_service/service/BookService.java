package com.exempl.book_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exempl.book_service.model.Book;
import com.exempl.book_service.model.Librarys;
import com.exempl.book_service.repository.BookRepository;
import com.exempl.book_service.service.client.LibraryFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

	private final BookRepository bookRepository;
	private final LibraryFeignClient libraryFeignClient;
	
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	public Book saveBook(Long libraryId, Book book) {
		Librarys library = libraryFeignClient.findLibraryId(libraryId);
		if(library != null) {
			book.setLibraryId(libraryId);
			book.setLibraryName(library.getAddress());
			book.setAddress(library.getAddress());
			return bookRepository.save(book);
		}
		return book;
	}
	
	public Book findById(Long libraryId, Long bookId) {
		Librarys library = libraryFeignClient.findLibraryId(libraryId);
		Book book = bookRepository.findById(bookId).get();
		if(library != null && book.getLibraryId().equals(library.getLibraryId())) {
			book.setLibraryName(library.getNameLibrary());
			book.setAddress(library.getAddress());
			return book;
		} else {
			throw new RuntimeException();
		}
		
	}
	
	public Book updateBook(Long libraryId, 
			Long bookId, Book book) {
		Librarys library = libraryFeignClient.findLibraryId(libraryId);
		Book dataBook = bookRepository.findById(bookId).get();
		if(library != null && dataBook != null) {
			dataBook.setAuthor(book.getAuthor());
			dataBook.setTitle(book.getTitle());
			dataBook.setPages(book.getPages());
			dataBook.setLibraryId(libraryId);
			dataBook.setLibraryName(library.getNameLibrary());
			dataBook.setAddress(library.getAddress());
			return bookRepository.save(dataBook);
		}
		throw new RuntimeException();
	}
	
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
	}

}
