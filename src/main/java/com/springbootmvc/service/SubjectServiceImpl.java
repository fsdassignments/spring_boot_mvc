package com.springbootmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootmvc.error.SubjectNotFoundException;
import com.springbootmvc.model.Subject;
import com.springbootmvc.repositories.SubjectRepository;


@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl implements SubjectsService {
	
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> list() {
		return subjectRepository.findAll();
	}

	@Transactional
	@Override
	public void delete(int id) throws SubjectNotFoundException {
		subjectRepository.deleteById(id);
	}

	@Override
	public List<Subject> searchSubject(String subjectName) {
		return subjectRepository.findBySubTitleContaining(subjectName);
	}

	@Override
	public List<Subject> searchSubjectByDuration(int duration) {
		return subjectRepository.findByDuration(duration);
	}

}
