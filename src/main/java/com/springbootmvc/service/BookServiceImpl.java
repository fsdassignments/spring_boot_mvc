
package com.springbootmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootmvc.error.BookNotFoundException;
import com.springbootmvc.model.Book;
import com.springbootmvc.repositories.BookRepository;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Transactional
	@Override
	public long save(Book book) throws Exception {
		bookRepository.save(book);
		return book.getBookId();
	}
	
	@Transactional
	@Override
	public List<Book> list() {
		return bookRepository.findAll();
	}

	@Transactional
	@Override
	public void delete(long id) throws BookNotFoundException {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> searchBooks(String bookName) {
		return bookRepository.findByTitleContaining(bookName);
	}

}
