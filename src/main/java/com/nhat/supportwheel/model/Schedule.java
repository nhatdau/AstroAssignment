package com.nhat.supportwheel.model;

import java.util.List;

/**
 * Schedule
 * 
 * @author ngocnhat.dau
 *
 */
public class Schedule {
    private List<ScheduleItem> items;

    public List<ScheduleItem> getItems() {
	return items;
    }

    public void setItems(List<ScheduleItem> items) {
	this.items = items;
    }

}
