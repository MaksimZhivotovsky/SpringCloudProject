package com.exempl.library_service.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exempl.library_service.model.Library;
import com.exempl.library_service.service.LibraryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/V1/librarys")
public class LibraryController {

	private final LibraryService libraryService;
	
	@GetMapping
	public List<Library> getLibraries() {
		return libraryService.findLibraries();
	}
	
	@PostMapping
	public Library saveLibrary(@RequestBody Library library) {
		return libraryService.saveLibrary(library);
	}
	
	@PutMapping(value = "/{libraryId}")
	public Library updateLibrary(
			@PathVariable("libraryId") Long libraryId,
			@RequestBody Library library) {
		Library dataLibrary = libraryService.findById(libraryId);
		if(dataLibrary != null) {
			dataLibrary.setAddress(library.getAddress());
			dataLibrary.setNameLibrary(library.getNameLibrary());
			libraryService.saveLibrary(dataLibrary);
			return dataLibrary;
		} else {
			throw new RuntimeException();
		}
	}
	
	@GetMapping(value = "/{libraryId}")
	public Library findLibraryId(@PathVariable("libraryId") Long id) {
		return libraryService.findById(id);
	}
	
	@DeleteMapping("/{libraryId}")
	public void deleteBook(@PathVariable("libraryId") Long libraryId) {
		libraryService.deleteLibrary(libraryId);
	}
}
