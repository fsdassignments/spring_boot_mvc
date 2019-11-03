
package com.springbootmvc.error;

public class SubjectNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 112445933945045577L;

	public SubjectNotFoundException(int id) {
		super("Subject not found for given id: " + id);
	}
}
