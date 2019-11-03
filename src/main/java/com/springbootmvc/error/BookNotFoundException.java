
package com.springbootmvc.error;

public class BookNotFoundException extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = -3806193817504611615L;

	public BookNotFoundException(Long id) {
		super("Book not found for given id: " + id);
	}
}
