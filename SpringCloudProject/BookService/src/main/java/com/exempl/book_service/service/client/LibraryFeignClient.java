package com.exempl.book_service.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.exempl.book_service.model.Librarys;


@FeignClient(value = "library-service", url = "${base-url}")
public interface LibraryFeignClient {
	
	@PostMapping(value  = "/V1/librarys", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Librarys saveLibrary(@RequestBody Librarys library);
	
	@PutMapping(value = "/V1/librarys/{libraryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Librarys updateLibrary(@PathVariable("libraryId") Long libraryId,
			@RequestBody Librarys library);
	
	@GetMapping(value = "/V1/librarys/{libraryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Librarys findLibraryId(@PathVariable("libraryId") Long libraryId);
	
	@DeleteMapping("/V1/librarys/{libraryId}")
	public void deleteBook(@PathVariable("libraryId") Long libraryId);
}
