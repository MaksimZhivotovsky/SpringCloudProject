package com.exempl.library_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "librarys")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "library_id")
	private Long libraryId;
	@Column(name = "nameLibrary")
	private String nameLibrary;
	@Column(name = "address")
	private String address;
}
