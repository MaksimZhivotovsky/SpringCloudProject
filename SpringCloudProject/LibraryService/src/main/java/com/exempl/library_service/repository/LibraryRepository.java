package com.exempl.library_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exempl.library_service.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
