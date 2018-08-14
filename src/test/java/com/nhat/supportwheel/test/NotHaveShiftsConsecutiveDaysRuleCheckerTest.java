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
import com.nhat.supportwheel.rule.impl.NotHaveShiftsConsecutiveDaysRuleChecker;

/**
 * Test class for {@link NotHaveShiftsConsecutiveDaysRuleChecker}
 * 
 * @author ngocnhat.dau
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NotHaveShiftsConsecutiveDaysRuleCheckerTest {
    private Schedule schedule;
    @Autowired
    @Qualifier("notHaveShiftsConsecutiveDaysRuleChecker")
    private RuleChecker notHaveShiftsConsecutiveDaysRuleChecker;

    @Before
    public void before() {
	schedule = new Schedule();
	List<ScheduleItem> scheduleItems = new ArrayList<>();
	schedule.setItems(scheduleItems);
    }

    @Test
    public void check_ShiftsNotConsecutiveDays_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	Engineer engineer3 = new Engineer("3");
	boolean result = notHaveShiftsConsecutiveDaysRuleChecker.check(engineer3, schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_ShiftsConsecutiveDays_False() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	boolean result = notHaveShiftsConsecutiveDaysRuleChecker.check(engineer2, schedule);
	Assert.assertFalse(result);
    }

    @Test
    public void check_SecondEngineerConsecutiveDays_False() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	schedule.getItems().add(item);
	boolean result = notHaveShiftsConsecutiveDaysRuleChecker.check(engineer2, schedule);
	Assert.assertFalse(result);
    }

    @Test
    public void check_SecondEngineerNotConsecutiveDays_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	schedule.getItems().add(item);
	boolean result = notHaveShiftsConsecutiveDaysRuleChecker.check(new Engineer("4"), schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_EmptySchedule_True() {
	boolean result = notHaveShiftsConsecutiveDaysRuleChecker.check(new Engineer("1"), schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_SecondEngineerFirstItem_True() {
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(new Engineer("1"));
	schedule.getItems().add(item);
	boolean result = notHaveShiftsConsecutiveDaysRuleChecker.check(new Engineer("2"), schedule);
	Assert.assertTrue(result);
    }
}
