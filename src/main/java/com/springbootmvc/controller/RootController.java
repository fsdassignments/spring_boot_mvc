package com.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {
	@RequestMapping("/")
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
}
