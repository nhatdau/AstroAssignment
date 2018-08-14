package com.nhat.supportwheel.service;

import com.nhat.supportwheel.model.Company;
import com.nhat.supportwheel.model.Schedule;

/**
 * Schedule service interface
 * 
 * @author ngocnhat.dau
 *
 */
public interface ScheduleService {
    /**
     * Schedule support wheel for engineers in company
     * 
     * @param company
     *            Company
     * @return Support wheel schedule for engineers
     */
    Schedule scheduleSupportWheel(Company company);
}
