package com.exempl.book_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class BookSeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSeviceApplication.class, args);
	}
}

