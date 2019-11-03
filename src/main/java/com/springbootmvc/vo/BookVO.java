package com.springbootmvc.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BookVO {
	public BookVO() {
		
	}
	
	private Long bookId;
	private Long subjectId;
	private String title;
	private double price;
	private int volume;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publishDate;
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", subjectId=" + subjectId + ", title=" + title + ", price=" + price
				+ ", volume=" + volume + ", publishDate=" + publishDate + "]";
	}
}
