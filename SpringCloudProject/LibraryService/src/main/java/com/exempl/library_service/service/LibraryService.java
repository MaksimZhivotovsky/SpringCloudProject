package com.exempl.library_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exempl.library_service.model.Library;
import com.exempl.library_service.repository.LibraryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryService {

	private final LibraryRepository libraryRepository;
	
	public List<Library> findLibraries() {
		return libraryRepository.findAll();
	}
	
	public Library saveLibrary(Library library) {
		return libraryRepository.save(library);
	}
	
	public Library findById(Long id) {
		return libraryRepository.findById(id).get();
	}
	
	public void deleteLibrary(Long id) {
		libraryRepository.deleteById(id);
	}
 }
