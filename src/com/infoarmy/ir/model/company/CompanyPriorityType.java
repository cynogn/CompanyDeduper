/*
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.infoarmy.ir.model.company;

/**
 * Company priorities List
 * 
 * @author Loganathan
 * 
 */
public enum CompanyPriorityType {

	LOW(10), MEDIUM(20), HIGH(200);

	private int priorityNumber;

	CompanyPriorityType(int priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public int getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(int priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

}
