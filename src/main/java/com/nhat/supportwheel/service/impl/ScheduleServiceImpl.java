package com.nhat.supportwheel.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nhat.supportwheel.model.Company;
import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;
import com.nhat.supportwheel.model.ScheduleItem;
import com.nhat.supportwheel.rule.RuleChecker;
import com.nhat.supportwheel.rule.impl.NotHaveShiftsConsecutiveDaysRuleChecker;
import com.nhat.supportwheel.rule.impl.OneDayIn2WeekRuleChecker;
import com.nhat.supportwheel.rule.impl.OneShiftADayRuleChecker;
import com.nhat.supportwheel.service.ScheduleService;

/**
 * {@link ScheduleService} implementation
 * 
 * @author ngocnhat.dau
 *
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private List<RuleChecker> ruleCheckers;
    @Value("${numberDays}")
    private Integer numberDays;

    public ScheduleServiceImpl() {
	ruleCheckers = new ArrayList<>();
	ruleCheckers.add(new OneShiftADayRuleChecker());
	ruleCheckers.add(new NotHaveShiftsConsecutiveDaysRuleChecker());
	ruleCheckers.add(new OneDayIn2WeekRuleChecker());
    }

    @Override
    public Schedule scheduleSupportWheel(Company company) {
	Schedule schedule = null;
	if (company != null && !CollectionUtils.isEmpty(company.getEngineers())) {
	    schedule = new Schedule();
	    boolean isSchedule;
	    do {
		isSchedule = true;
		List<ScheduleItem> scheduleItems = new ArrayList<>();
		schedule.setItems(scheduleItems);
		for (int i = 0; i < numberDays; i++) {
		    ScheduleItem scheduleItem;
		    Optional<Engineer> oEngineer = findNextEngineer(company, schedule);
		    if (oEngineer.isPresent()) {
			scheduleItem = new ScheduleItem();
			scheduleItem.setEngineer1(oEngineer.get());
			scheduleItems.add(scheduleItem);
			oEngineer = findNextEngineer(company, schedule);
			if (oEngineer.isPresent()) {
			    scheduleItem.setEngineer2(oEngineer.get());

			} else {
			    isSchedule = false;
			    break;
			}
		    } else {
			isSchedule = false;
			break;
		    }
		}
	    } while (!isSchedule);
	    // set Dates for schedule
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.WEEK_OF_YEAR, 1);
	    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    for (int i = 0; i < numberDays; i++) {
		schedule.getItems().get(i).setDate(dateFormatter.format(calendar.getTime()));
		if ((i + 1) % 5 == 0) {
		    calendar.add(Calendar.DATE, 3);
		} else {
		    calendar.add(Calendar.DATE, 1);
		}
	    }
	}
	return schedule;
    }

    protected Optional<Engineer> findNextEngineer(Company company, final Schedule finalSchedule) {
	List<Engineer> engineers = new ArrayList<>(company.getEngineers());
	Collections.shuffle(engineers);
	Optional<Engineer> oEngineer = engineers.stream().filter(engineer -> {
	    for (RuleChecker ruleChecker : ruleCheckers) {
		if (!ruleChecker.check(engineer, finalSchedule)) {
		    return false;
		}
	    }
	    return true;
	}).findFirst();
	return oEngineer;
    }
}
