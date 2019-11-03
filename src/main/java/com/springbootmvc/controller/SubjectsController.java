package com.springbootmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springbootmvc.error.SubjectNotFoundException;
import com.springbootmvc.service.SubjectsService;


@Controller
@RequestMapping(value = "/subjects")
public class SubjectsController {
	@Autowired
	private SubjectsService subjectService;

	@RequestMapping(value = "/getAllSubjects", method = RequestMethod.GET)
	public ModelAndView listSubjects() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("subjectsList", subjectService.list());
		return new ModelAndView("subjects", model);
	}

	@RequestMapping(value = "/deleteSubject/{id}", method = RequestMethod.GET)
	public String deleteSubject(@PathVariable("id") int subjectId) throws SubjectNotFoundException {
		System.out.println("subjectId:" + subjectId);
		subjectService.delete(subjectId);
		return "redirect:/subjects/getAllSubjects";
	}
	
	@RequestMapping(value = "/searchSubject")
	public ModelAndView searchSubject(@RequestParam("searchName") String searchName) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("subjectsList", subjectService.searchSubject(searchName));
		return new ModelAndView("subjects", model);
	}
	
	@RequestMapping(value = "/searchSubjectByDuration")
	public ModelAndView searchSubjectByDuration(@RequestParam("searchDuration") String searchDuration) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("subjectsList", subjectService.searchSubjectByDuration(Integer.parseInt(searchDuration)));
		return new ModelAndView("subjects", model);
	}
}
