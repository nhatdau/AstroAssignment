package com.nhat.supportwheel.model;

import java.util.List;

/**
 * Company
 * 
 * @author ngocnhat.dau
 *
 */
public class Company {
    private List<Engineer> engineers;

    public List<Engineer> getEngineers() {
	return engineers;
    }

    public void setEngineers(List<Engineer> engineers) {
	this.engineers = engineers;
    }
}
