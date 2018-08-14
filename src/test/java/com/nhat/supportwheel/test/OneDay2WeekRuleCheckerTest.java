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
import com.nhat.supportwheel.rule.impl.OneDayIn2WeekRuleChecker;

/**
 * Test class for {@link OneDayIn2WeekRuleChecker}
 * 
 * @author ngocnhat.dau
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OneDay2WeekRuleCheckerTest {
    private Schedule schedule;
    @Autowired
    @Qualifier("oneDayIn2WeekRuleChecker")
    private RuleChecker oneDayIn2WeekRuleChecker;

    @Before
    public void before() {
	schedule = new Schedule();
	List<ScheduleItem> scheduleItems = new ArrayList<>();
	schedule.setItems(scheduleItems);
    }

    @Test
    public void check_Over1Day_False() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	Engineer engineer4 = new Engineer("4");
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer3);
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	boolean result = oneDayIn2WeekRuleChecker.check(engineer1, schedule);
	Assert.assertFalse(result);
    }

    @Test
    public void check_NotOver1Day_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	Engineer engineer4 = new Engineer("4");
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer3);
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	boolean result = oneDayIn2WeekRuleChecker.check(new Engineer("5"), schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_SecondEngineerOver1Day_False() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	Engineer engineer4 = new Engineer("4");
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer3);
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(new Engineer("5"));
	schedule.getItems().add(item);
	boolean result = oneDayIn2WeekRuleChecker.check(engineer2, schedule);
	Assert.assertFalse(result);
    }

    @Test
    public void check_SecondEngineerNotOver1Day_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	Engineer engineer4 = new Engineer("4");
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer3);
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(new Engineer("5"));
	schedule.getItems().add(item);
	boolean result = oneDayIn2WeekRuleChecker.check(new Engineer("6"), schedule);
	Assert.assertTrue(result);
    }

    @Test
    public void check_ScheduleItemsLessThan4_True() {
	Engineer engineer1 = new Engineer("1");
	Engineer engineer2 = new Engineer("2");
	ScheduleItem item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	Engineer engineer3 = new Engineer("3");
	item.setEngineer1(engineer3);
	Engineer engineer4 = new Engineer("4");
	item.setEngineer2(engineer4);
	schedule.getItems().add(item);
	item = new ScheduleItem();
	item.setEngineer1(engineer1);
	item.setEngineer2(engineer2);
	schedule.getItems().add(item);
	boolean result = oneDayIn2WeekRuleChecker.check(engineer3, schedule);
	Assert.assertTrue(result);
    }
}
