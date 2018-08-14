package com.nhat.supportwheel.rule.impl;

import org.springframework.stereotype.Component;

import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.model.ScheduleItem;
import com.nhat.supportwheel.rule.RuleChecker;

/**
 * Check rule: Each engineer should have completed one whole day of support in
 * any 2 week period.
 * 
 * @author ngocnhat.dau
 *
 */
@Component
public class OneDayIn2WeekRuleChecker implements RuleChecker {

    @Override
    public boolean check(Engineer engineer, Schedule schedule) {
	boolean result = true;
	int size = schedule.getItems().size();
	if (size > 4 || (size == 4 && schedule.getItems().get(size - 1).getEngineer2() != null)) {
	    int index;
	    if (schedule.getItems().get(size - 1).getEngineer2() == null) {
		index = size - 3;
	    } else {
		index = size - 2;
	    }
	    int nShift = 0;
	    while (index >= 0 && nShift < 2) {
		ScheduleItem item = schedule.getItems().get(index);
		if (engineer.getId().equals(item.getEngineer1().getId())
			|| engineer.getId().equals(item.getEngineer2().getId())) {
		    nShift++;
		    index -= 2;
		} else {
		    index--;
		}
	    }
	    if (nShift == 2) {
		result = false;
	    }
	}
	return result;
    }

}
