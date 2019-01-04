package com.tms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tms.model.TimeLog;
import com.tms.repository.TimeLogRepository;



	@Controller
	public class TimeLogController {

		@Autowired
		private TimeLogRepository timeLogRepository;
		
		@GetMapping("/start")
		public String start(Model model, String userId)
		{
			System.out.println(userId + "hej");
			model.addAttribute("userId", userId);
			return "start";
		}

		@PostMapping("/start")
		public String startTime( Model model, String userId) {
			
			TimeLog timeLog = new TimeLog();
			
			timeLog.setStartTime(LocalDateTime.now());
			timeLog.setUserId(Integer.parseInt(userId));
			
			timeLogRepository.save(timeLog);
			
			model.addAttribute("userId", userId);
			model.addAttribute("timeLog", timeLog.getId());
			
			return "stop";
		}
		
	
		@PostMapping("/stop")
		public String start(Model model, String userId, Integer timeLog) {
			
			TimeLog tl = timeLogRepository.findById(timeLog).get();
			
			tl.setStopTime(LocalDateTime.now());
			
			timeLogRepository.save(tl);
			
			model.addAttribute("userId", userId);
		    model.addAttribute("timeLog", tl );

			return "start";
		}
		
}
