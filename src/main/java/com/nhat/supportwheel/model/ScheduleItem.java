package com.nhat.supportwheel.model;

/**
 * Schedule Item
 * 
 * @author ngocnhat.dau
 *
 */
public class ScheduleItem {
    private Engineer engineer1;
    private Engineer engineer2;
    private String date;

    public Engineer getEngineer1() {
	return engineer1;
    }

    public void setEngineer1(Engineer engineer1) {
	this.engineer1 = engineer1;
    }

    public Engineer getEngineer2() {
	return engineer2;
    }

    public void setEngineer2(Engineer engineer2) {
	this.engineer2 = engineer2;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

}
