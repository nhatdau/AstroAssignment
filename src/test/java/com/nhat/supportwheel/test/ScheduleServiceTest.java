package com.nhat.supportwheel.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nhat.supportwheel.model.Company;
import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.model.ScheduleItem;
import com.nhat.supportwheel.service.ScheduleService;
import com.nhat.supportwheel.service.impl.ScheduleServiceImpl;

/**
 * Test class for {@link ScheduleServiceImpl}
 * 
 * @author ngocnhat.dau
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void scheduleSupportWheel_Engineers_CorrectSchedule() {
	Company company = new Company();
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
	Schedule schedule = scheduleService.scheduleSupportWheel(company);
	Assert.assertNotNull(schedule);
	List<ScheduleItem> items = schedule.getItems();
	Assert.assertEquals(10, items.size());
	// check don't have 2 shifts 1 day
	for (int i = 0; i < 10; i++) {
	    Assert.assertNotEquals(items.get(i).getEngineer1().getId(), items.get(i).getEngineer2().getId());
	}
	// check don't have shifts in consecutive days
	for (int i = 1; i < 10; i++) {
	    Engineer currentEngineer1 = items.get(i).getEngineer1();
	    Engineer currentEngineer2 = items.get(i).getEngineer2();
	    Engineer previsousEngineer1 = items.get(i - 1).getEngineer1();
	    Engineer previsousEngineer2 = items.get(i - 1).getEngineer2();
	    Assert.assertNotEquals(previsousEngineer1.getId(), currentEngineer1.getId());
	    Assert.assertNotEquals(previsousEngineer2.getId(), currentEngineer1.getId());
	    Assert.assertNotEquals(previsousEngineer1.getId(), currentEngineer2.getId());
	    Assert.assertNotEquals(previsousEngineer2.getId(), currentEngineer2.getId());
	}
	// check each engineer has 1 day support
	for (int i = 1; i <= 10; i++) {
	    int nShift = 0;
	    for (int j = 0; j < 10; j++) {
		if (items.get(j).getEngineer1().getId().equals(Integer.toString(i))
			|| items.get(j).getEngineer2().getId().equals(Integer.toString(i))) {
		    nShift++;
		    j++;
		}
	    }
	    Assert.assertEquals(2, nShift);
	}
	// check dates in schedule
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.WEEK_OF_YEAR, 1);
	calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	String date = items.get(0).getDate();
	Assert.assertEquals(dateFormatter.format(calendar.getTime()), date);
	date = items.get(1).getDate();
	calendar.add(Calendar.DATE, 1);
	Assert.assertEquals(dateFormatter.format(calendar.getTime()), date);
	date = items.get(5).getDate();
	calendar.add(Calendar.DATE, 6);
	Assert.assertEquals(dateFormatter.format(calendar.getTime()), date);
    }

    @Test
    public void scheduleSupportWheel_EmptyList_Null() {
	Company company = new Company();
	List<Engineer> engineers = new ArrayList<>();
	company.setEngineers(engineers);
	Schedule schedule = scheduleService.scheduleSupportWheel(company);
	Assert.assertNull(schedule);
    }
}
