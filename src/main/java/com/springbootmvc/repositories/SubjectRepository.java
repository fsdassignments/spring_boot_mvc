package com.springbootmvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootmvc.model.Subject;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	List<Subject> findBySubTitleContaining(String subTitle);
	
	List<Subject> findByDuration(int duration);
}
