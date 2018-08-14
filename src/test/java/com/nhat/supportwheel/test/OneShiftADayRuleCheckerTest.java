package com.nhat.supportwheel.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.model.ScheduleItem;
import com.nhat.supportwheel.rule.RuleChecker;
import com.nhat.supportwheel.rule.impl.OneShiftADayRuleChecker;

/**
 * Test class for {@link OneShiftADayRuleChecker}
 * 
 * @author ngocnhat.dau
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OneShiftADayRuleCheckerTest {
    private Schedule schedule;
    @Autowired
    @Qualifier("oneShiftADayRuleChecker")
    private RuleChecker oneShiftADayRuleChecker;

    @Before
    public void before() {
	schedule = new Schedule();
	List<ScheduleItem> scheduleItems = new ArrayList<>();
	schedule.setItems(scheduleItems);
    }

    @Test
    public void check_1Day2Shifts_False() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer1);
	// item.setEngineer2(new Engineer("2"));
	schedule.getItems().add(item);
	boolean result = oneShiftADayRuleChecker.check(engineer1, schedule);
	Assert.assertFalse(result);
    }

    @Test
    public void check_1Day1Shift_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer2);
	// item.setEngineer2(new Engineer("2"));
	schedule.getItems().add(item);
	boolean result = oneShiftADayRuleChecker.check(engineer1, schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_EmptySchedule_True() {
	boolean result = oneShiftADayRuleChecker.check(new Engineer("2"), schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_EmptyDay_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	boolean result = oneShiftADayRuleChecker.check(engineer1, schedule);
	Assert.assertTrue(result);
    }

}
