package com.nhat.supportwheel.rule.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.model.ScheduleItem;
import com.nhat.supportwheel.rule.RuleChecker;

/**
 * Check rule: An engineer cannot have half day shifts on consecutive days.
 * 
 * @author ngocnhat.dau
 *
 */
@Component
public class NotHaveShiftsConsecutiveDaysRuleChecker implements RuleChecker {

    @Override
    public boolean check(Engineer engineer, Schedule schedule) {
	boolean result = false;
	List<ScheduleItem> scheduleItems = schedule.getItems();
	int size = scheduleItems.size();
	if (CollectionUtils.isEmpty(scheduleItems) || (size == 1 && scheduleItems.get(0).getEngineer2() == null)) {
	    result = true;
	} else {
	    ScheduleItem lastItem = scheduleItems.get(size - 1);
	    if (lastItem.getEngineer2() == null) {
		lastItem = scheduleItems.get(size - 2);

	    }
	    if (!lastItem.getEngineer1().getId().equals(engineer.getId())
		    && !lastItem.getEngineer2().getId().equals(engineer.getId())) {
		result = true;
	    }
	}
	return result;
    }

}
