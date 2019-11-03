package com.springbootmvc.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.springbootmvc.error.SubjectNotFoundException;
import com.springbootmvc.model.Subject;
import com.springbootmvc.repositories.SubjectRepository;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceImplTest {
	@InjectMocks
	private SubjectServiceImpl subjectServiceImpl;
	
	@Mock
	private SubjectRepository subjectRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void list() {
		Subject sub1 = new Subject("subj1", 15);
		Subject sub2 = new Subject("subj2", 25);
		
		List<Subject> lstSubjects = newArrayList(sub1, sub2);
		
		when(subjectRepository.findAll()).thenReturn(lstSubjects);
		
		List<Subject> result = subjectServiceImpl.list();
		
		assertEquals(result, lstSubjects);
		assertEquals(2, result.size());
		verify(subjectRepository, times(1)).findAll();
	}
	
	@Test
	public void delete() throws SubjectNotFoundException {
		int subjectId = 1;
		subjectServiceImpl.delete(subjectId);
		verify(subjectRepository, times(1)).deleteById(subjectId);
	}
	
	@Test
	public void searchSubject() {
		String subTitle = "subj1";
		Subject sub1 = new Subject("subj1", 15);
		
		when(subjectRepository.findBySubTitleContaining(subTitle)).thenReturn(newArrayList(sub1));
		
		List<Subject> result = subjectServiceImpl.searchSubject(subTitle);
		
		assertEquals(1, result.size());
		verify(subjectRepository, times(1)).findBySubTitleContaining(subTitle);
	}
	
	@Test
	public void searchSubjectByDuration() {
		int duration = 15;
		Subject sub1 = new Subject("subj1", 15);
		
		when(subjectRepository.findByDuration(duration)).thenReturn(newArrayList(sub1));
		
		List<Subject> result = subjectServiceImpl.searchSubjectByDuration(duration);
		
		assertEquals(1, result.size());
		verify(subjectRepository, times(1)).findByDuration(duration);
	}
}
