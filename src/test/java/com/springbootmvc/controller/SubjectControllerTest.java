package com.springbootmvc.controller;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.springbootmvc.error.SubjectNotFoundException;
import com.springbootmvc.model.Subject;
import com.springbootmvc.service.SubjectsService;

@RunWith(MockitoJUnitRunner.class)
public class SubjectControllerTest {
	@InjectMocks
	private SubjectsController subjectController;
	
	@Mock
	private SubjectsService subjectService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listSubjects() {
		Subject sub1 = new Subject("sub1", 15);
		Subject sub2 = new Subject("sub2", 25);
		List<Subject> lstSubjects = newArrayList(sub1, sub2);
		
		when(subjectService.list()).thenReturn(lstSubjects);
		
		ModelAndView listSubjects = subjectController.listSubjects();
		
		assertEquals(listSubjects.getViewName(), "subjects");
		assertEquals(listSubjects.getModel().get("subjectsList"), lstSubjects);
		
		verify(subjectService, times(1)).list();
	}
	
	@Test
	public void deleteSubject() throws SubjectNotFoundException {
		int subId = 1;
		String redirectTo = subjectController.deleteSubject(subId);
		assertEquals(redirectTo, "redirect:/subjects/getAllSubjects");
		verify(subjectService, times(1)).delete(subId);
	}
	
	@Test
	public void searchSubjectByTitle() {
		String searchName = "sub1";
		Subject sub1 = new Subject("sub1", 15);
		List<Subject> lstSubjects = newArrayList(sub1);
		
		when(subjectService.searchSubject(searchName)).thenReturn(lstSubjects);
		
		ModelAndView listSubjects = subjectController.searchSubject(searchName);
		
		assertEquals(listSubjects.getViewName(), "subjects");
		assertEquals(listSubjects.getModel().get("subjectsList"), lstSubjects);
		
		verify(subjectService, times(1)).searchSubject(searchName);
	}
	
	@Test
	public void searchSubjectByDuration() {
		String duration = "15";
		Subject sub1 = new Subject("sub1", 15);
		List<Subject> lstSubjects = newArrayList(sub1);
		
		when(subjectService.searchSubjectByDuration(Integer.parseInt(duration))).thenReturn(lstSubjects);
		
		ModelAndView listSubjects = subjectController.searchSubjectByDuration(duration);
		
		assertEquals(listSubjects.getViewName(), "subjects");
		assertEquals(listSubjects.getModel().get("subjectsList"), lstSubjects);
		
		verify(subjectService, times(1)).searchSubjectByDuration(Integer.parseInt(duration));
	}
}
