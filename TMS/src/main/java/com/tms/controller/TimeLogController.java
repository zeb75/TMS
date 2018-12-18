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
		public String startTime(@ModelAttribute("start")TimeLog timeLog, Model model, String userId) {
			
			timeLog.setStartTime(LocalDateTime.now());
			System.out.println(userId + "fu");
			timeLog.setUserId(Integer.parseInt(userId));
			timeLog = timeLogRepository.save(timeLog);
			model.addAttribute("userId", userId);
			model.addAttribute("timeLog", timeLog);
			return "stop";
		}
		

		@PostMapping("/stop")
		public String stop(@ModelAttribute("stop")TimeLog timeLog, Model model, String userId) {
			
			Iterable<TimeLog> timeLogList = timeLogRepository.findAll(); //Create a list and add the timelog to it
			
			int i = ((List<TimeLog>) timeLogList).size() -1; //Find the last index in the list
			
			TimeLog tl = ((List<TimeLog>) timeLogList).get(i); // Get object 
			
//			int id = tl.getTimeLogId(); //Get id
			
			tl.setStopTime(LocalDateTime.now());
			
			timeLogRepository.save(tl);
			System.out.println(tl);
			
			model.addAttribute("userId", userId);
			
			return "start";
		}
}
