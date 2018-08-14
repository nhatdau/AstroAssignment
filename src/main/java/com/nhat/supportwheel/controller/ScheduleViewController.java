package com.nhat.supportwheel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhat.supportwheel.model.Company;
import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.service.ScheduleService;

/**
 * Schedule web view controller
 * 
 * @author ngocnhat.dau
 *
 */
@Controller
public class ScheduleViewController {
    @Autowired
    private ScheduleService scheduleService;
    private Company company;

    public ScheduleViewController() {
	company = new Company();
	List<Engineer> engineers = new ArrayList<>();
	engineers.add(new Engineer("1"));
	engineers.add(new Engineer("2"));
	engineers.add(new Engineer("3"));
	engineers.add(new Engineer("4"));
	engineers.add(new Engineer("5"));
	engineers.add(new Engineer("6"));
	engineers.add(new Engineer("7"));
	engineers.add(new Engineer("8"));
	engineers.add(new Engineer("9"));
	engineers.add(new Engineer("10"));
	company.setEngineers(engineers);
    }

    @RequestMapping("/viewSchedule")
    public String showSchedule(Map<String, Object> model) {
	Schedule schedule = scheduleService.scheduleSupportWheel(company);
	model.put("schedule", schedule);
	return "schedule";
    }
}
