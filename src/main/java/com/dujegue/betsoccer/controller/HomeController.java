package com.dujegue.betsoccer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	public HomeController() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		
		String x = "AAAAAAAAAAAAAAAAAAA";
		
		model.addAttribute("teste", x);
		
		return "home";
	}

}