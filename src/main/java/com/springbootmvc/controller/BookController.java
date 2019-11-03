package com.springbootmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springbootmvc.converters.BookConverter;
import com.springbootmvc.error.BookNotFoundException;
import com.springbootmvc.model.Book;
import com.springbootmvc.model.Subject;
import com.springbootmvc.service.BookService;
import com.springbootmvc.service.SubjectsService;
import com.springbootmvc.vo.BookVO;

@Controller
@RequestMapping(value = "/books")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private SubjectsService subjectService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value = "/createBook")
	public ModelAndView createBook(@ModelAttribute BookVO book) {
		Map<String, Object> model = new HashMap<String, Object>();
		book = new BookVO();
		model.put("book", book);
		model.put("subjectsList", getSubjects());
        return new ModelAndView("addBook", model);
    }
	
	@RequestMapping(value ="/saveBook")
	public String saveEmployee(@ModelAttribute("book") BookVO book, BindingResult result) throws Exception {
		System.out.println(book.toString());
		Book bookPojo = BookConverter.convertToPojo(book);
		System.out.println("after conversion " + bookPojo.toString());
		bookService.save(bookPojo);
		return "redirect:/books/getAllBooks";
	}

	@RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
	public ModelAndView listBooks() {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Book> books = bookService.list();
		model.put("subjectsList", getSubjects());
		model.put("booksList", books);
		System.out.println(books);
		return new ModelAndView("books", model);
	}

	@RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") long bookId) throws BookNotFoundException {
		System.out.println("bookID: " + bookId);
		bookService.delete(bookId);
		return "redirect:/books/getAllBooks";
	}

	@RequestMapping(value = "/searchBook")
	public ModelAndView searchBooks(@RequestParam("searchName") String searchName) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("subjectsList", getSubjects());
		model.put("booksList", bookService.searchBooks(searchName));
		return new ModelAndView("books", model);
	}

	private List<Subject> getSubjects() {
		return subjectService.list();
	}
}
