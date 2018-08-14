package com.nhat.supportwheel.rule;

import com.nhat.supportwheel.model.Engineer;
import com.nhat.supportwheel.model.Schedule;

/**
 * Rule checker interface
 * 
 * @author ngocnhat.dau
 *
 */
public interface RuleChecker {
    /**
     * Check the scheduling rule for engineer
     * 
     * @param engineer
     *            the engineer
     * @param schedule
     *            schedule for engineers
     * 
     * @return true: pass checking rule, false: not pass checking rule
     */
    boolean check(Engineer engineer, Schedule schedule);
}
