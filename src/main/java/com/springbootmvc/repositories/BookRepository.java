package com.springbootmvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootmvc.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContaining(String title);
}
