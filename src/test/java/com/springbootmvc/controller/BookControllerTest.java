package com.springbootmvc.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.springbootmvc.error.BookNotFoundException;
import com.springbootmvc.model.Book;
import com.springbootmvc.model.Subject;
import com.springbootmvc.service.BookService;
import com.springbootmvc.service.SubjectsService;
import com.springbootmvc.vo.BookVO;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	@InjectMocks
	private BookController bookController;
	
	@Mock
	private BookService bookService;
	@Mock
	private SubjectsService subjectService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void createBook() {
		BookVO bookVO = new BookVO();
		
		Subject sub1 = new Subject("sub1", 15);
		Subject sub2 = new Subject("sub2", 25);
		
		List<Subject> lstSubjectList = newArrayList(sub1, sub2);
		
		when(subjectService.list()).thenReturn(lstSubjectList);
		
		ModelAndView createView = bookController.createBook(bookVO);
		
		assertEquals(createView.getViewName(), "addBook");
		BookVO modelAttrBookVO = (BookVO) createView.getModel().get("book");
		assertEquals(modelAttrBookVO.getTitle(), bookVO.getTitle());
		assertEquals(modelAttrBookVO.getPrice(), bookVO.getPrice(), 0.01);
		assertEquals(modelAttrBookVO.getVolume(), bookVO.getVolume());
		assertEquals(modelAttrBookVO.getPublishDate(), bookVO.getPublishDate());
		assertEquals(createView.getModel().get("subjectsList"), lstSubjectList);
		verify(subjectService, times(1)).list();
	}
	
	
	@Test
	public void addBook() throws Exception {
		BindingResult result = null;
		Date publishDate = new Date();
		BookVO bookVO = new BookVO();
		bookVO.setTitle("book1");
		bookVO.setPrice(150);
		bookVO.setVolume(1);
		bookVO.setSubjectId(1l);
		bookVO.setPublishDate(publishDate);
		
		Subject subject = new Subject();
		subject.setSubjectId(1);
		
		Book book = new Book(subject, "book1", 150, 1, publishDate);
		
		when(bookService.save(book)).thenReturn(1l);
		
		String redirectString = bookController.saveEmployee(bookVO, result);
		
		assertEquals(redirectString, "redirect:/books/getAllBooks");
		verify(bookService, times(1)).save(book);
	}
	
	@Test
	public void listBooks() {
		Subject sub1 = new Subject("sub1", 15);
		sub1.setSubjectId(1);
		Subject sub2 = new Subject("sub2", 25);
		sub2.setSubjectId(2);
		
		List<Subject> lstSubjectList = newArrayList(sub1, sub2);
		
		Book book = new Book(sub1, "book1", 150, 1, new Date());
		Book book1 = new Book(sub2, "book2", 250, 2, new Date());
		
		List<Book> lstBooks = newArrayList(book, book1);
		
		when(subjectService.list()).thenReturn(lstSubjectList);
		when(bookService.list()).thenReturn(lstBooks);
		
		ModelAndView getBooks = bookController.listBooks();
		
		assertEquals(getBooks.getViewName(), "books");
		assertEquals(getBooks.getModel().get("subjectsList"), lstSubjectList);
		assertEquals(getBooks.getModel().get("booksList"), lstBooks);
		
		verify(bookService, times(1)).list();
		verify(subjectService, times(1)).list();
	}
	
	@Test
	public void searchBooks() {
		String searchName = "book1";
		Subject sub1 = new Subject("sub1", 15);
		sub1.setSubjectId(1);
		Subject sub2 = new Subject("sub2", 25);
		sub2.setSubjectId(2);
		
		List<Subject> lstSubjectList = newArrayList(sub1, sub2);
		
		Book book = new Book(sub1, "book1", 150, 1, new Date());
		
		List<Book> lstBooks = newArrayList(book);
		
		when(subjectService.list()).thenReturn(lstSubjectList);
		when(bookService.searchBooks(searchName)).thenReturn(lstBooks);
		
		ModelAndView getBooks = bookController.searchBooks(searchName);
		
		assertEquals(getBooks.getViewName(), "books");
		assertEquals(getBooks.getModel().get("subjectsList"), lstSubjectList);
		assertEquals(getBooks.getModel().get("booksList"), lstBooks);
		
		verify(bookService, times(1)).searchBooks(searchName);
		verify(subjectService, times(1)).list();
	}
	
	@Test
	public void deleteBooks() throws BookNotFoundException {
		Long bookId = 1l;
		String redirectTo = bookController.deleteBook(bookId);
		
		assertEquals(redirectTo, "redirect:/books/getAllBooks");
		verify(bookService, times(1)).delete(bookId);
	}
}
