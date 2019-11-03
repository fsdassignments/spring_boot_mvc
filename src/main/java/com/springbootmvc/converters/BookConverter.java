package com.springbootmvc.converters;

import com.springbootmvc.model.Book;
import com.springbootmvc.model.Subject;
import com.springbootmvc.vo.BookVO;

public class BookConverter {
	public static Book convertToPojo(BookVO bookVO) {
		Book book = new Book();
		book.setBookId(bookVO.getBookId());
		book.setTitle(bookVO.getTitle());
		book.setPrice(bookVO.getPrice());
		book.setVolume(bookVO.getVolume());
		book.setPublishDate(bookVO.getPublishDate());
		Subject subject = new Subject();
		subject.setSubjectId(bookVO.getSubjectId().intValue());
		book.setSubject(subject);
		return book;
	}
}
