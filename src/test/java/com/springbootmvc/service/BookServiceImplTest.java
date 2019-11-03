package com.springbootmvc.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static com.google.common.collect.Lists.newArrayList;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.springbootmvc.error.BookNotFoundException;
import com.springbootmvc.model.Book;
import com.springbootmvc.model.Subject;
import com.springbootmvc.repositories.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	
	@Mock
	private BookRepository bookRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void list() {
		Subject subj1 = new Subject();
		subj1.setSubjectId(1);
		Subject subj2 = new Subject();
		subj2.setSubjectId(2);
		
		Book book1 = new Book(subj1, "book1", 150.00, 1, new Date());
		Book book2 = new Book(subj2, "book2", 180.00, 1, new Date());
		
		List<Book> lstBooks = newArrayList(book1, book2); 
		
		when(bookRepository.findAll()).thenReturn(lstBooks);
		
		List<Book> result = bookServiceImpl.list();
		
		assertEquals(lstBooks, result);
		assertEquals(2, result.size());
		verify(bookRepository, times(1)).findAll();
	}
	
	@Test
	public void searchBooks() {
		String searchString = "book1";
		Subject subj1 = new Subject();
		subj1.setSubjectId(1);
		
		Book book1 = new Book(subj1, "book1", 150.00, 1, new Date());
		
		when(bookRepository.findByTitleContaining(searchString)).thenReturn(newArrayList(book1));
		
		List<Book> result = bookServiceImpl.searchBooks(searchString);
		
		assertEquals(1, result.size());
		verify(bookRepository, times(1)).findByTitleContaining(searchString);
	}
	
	@Test
	public void delete() throws BookNotFoundException {		
		Long bookId = 1L;		
		bookServiceImpl.delete(bookId);
		verify(bookRepository, times(1)).deleteById(bookId);
	}
	
	@Test
	public void save() throws Exception {
		Subject subj1 = new Subject();
		subj1.setSubjectId(1);
		
		Book book1 = new Book(subj1, "book1", 150.00, 1, new Date());
		book1.setBookId(1l);
		
		when(bookRepository.save(book1)).thenReturn(book1);
		
		long bookId = bookServiceImpl.save(book1);
		
		assertEquals(1, bookId);
		verify(bookRepository, times(1)).save(book1);
	}
}
