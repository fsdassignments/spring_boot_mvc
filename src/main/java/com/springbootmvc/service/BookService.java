
package com.springbootmvc.service;

import java.util.List;

import com.springbootmvc.error.BookNotFoundException;
import com.springbootmvc.model.Book;

public interface BookService {
	long save(Book book) throws Exception;

	List<Book> list();

	void delete(long id) throws BookNotFoundException;
	
	List<Book> searchBooks(String bookName);
}
