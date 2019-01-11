package com.tms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tms.model.TimeLog;
import com.tms.repository.TimeLogRepository;

@Controller
public class TimeReportController {

    @Autowired
    private TimeLogRepository timeLogRepository;

    @GetMapping("/userTimeReport")
    public String StartTimeReport(Model model,@RequestParam Integer userId){

        model.addAttribute("userId", userId);
        System.out.println("####### GET REPOSRT " + userId);
        return "userTimeReport";
    }

    @PostMapping("/userTimeReport")
    public String timeReport(Model model,@RequestParam Integer userId,@RequestParam String startTime,@RequestParam String stopTime) {
        LocalDateTime startT = LocalDateTime.parse(startTime+"T00:00:00");
        LocalDateTime stopT = LocalDateTime.parse(stopTime+"T00:00:00");
        List<TimeLog> reportTimes = timeLogRepository.findByUserIdAndStartTimeBetween(userId,startT,stopT);
        model.addAttribute("reportTimes", reportTimes);
        model.addAttribute("userId",userId);

        System.out.println("######### POST REPORT" + userId);

        return "userTimeReport";
    }
}