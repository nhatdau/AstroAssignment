package com.nhat.supportwheel.rule.impl;

import org.springframework.stereotype.Component;

import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.rule.RuleChecker;

/**
 * Check rule: An engineer can do at most one half day shift in a day.
 * 
 * @author ngocnhat.dau
 *
 */
@Component
public class OneShiftADayRuleChecker implements RuleChecker {

    @Override
    public boolean check(Engineer engineer, Schedule schedule) {
	boolean result = false;
	int size = schedule.getItems().size();
	if (size > 0 && schedule.getItems().get(size - 1).getEngineer2() == null) {
	    if (!schedule.getItems().get(size - 1).getEngineer1().getId().equals(engineer.getId())) {
		result = true;
	    }
	} else {
	    result = true;
	}
	return result;
    }

}
