package com.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimeReportController {

	
	@GetMapping("/userReport")
	public String timeReport(Model model, String userId )
	{
		model.addAttribute("userId", userId);
		
		return "userReport";
	}
}
