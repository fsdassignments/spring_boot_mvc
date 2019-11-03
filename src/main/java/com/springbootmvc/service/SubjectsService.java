package com.springbootmvc.service;

import java.util.List;

import com.springbootmvc.error.SubjectNotFoundException;
import com.springbootmvc.model.Subject;


public interface SubjectsService {
	List<Subject> list();
	
	void delete(int id) throws SubjectNotFoundException;
	
	List<Subject> searchSubject(String subjectName);
	
	List<Subject> searchSubjectByDuration(int duration);
}
