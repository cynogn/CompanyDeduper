/*
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.infoarmy.ir.common;

/**
 * 
 * @author Loganathan
 * 
 */
public enum CompanyStatus {

	TO_BE_SEEDED("To Be Seeded"), NEW("New"), IN_PROGRESS(
			"Research in progress"), READY_FOR_REVIEW(
			"Ready for collaborator review"), REVIEW_IN_PROGRESS(
			"Collaboration in progress"), READY_FOR_PUBLISH(
			"Ready for publishing"), JUDGEMENT_REQUIRED("Judgement pending"), JUDGEMENT_IN_PROGRESS(
			"Judgement In Progress"), JUDGEMENT_GIVEN("Judgement given"), COMMENTS_ADDED(
			"Comment Added"), ABANDONED("Abandoned"), PRE_PURCHASED(
			"Pre Purchased"), PURCHASED("Purchased"), PUBLISHED("Published"), PUBLISH_IN_PROGRESS(
			"Publish In Progress"), REVIEW_FOR_CONSUMPTION(
			"Review For Consumption"), READY_FOR_CONSUMPTION(
			"Ready For Consumption"), REJECTED("Rejected"), PUBLISHED_PREVIEW_DELAYED(
			"Published Preview Delayed"), DUPLICATE("Duplicate");

	private String status;

	CompanyStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public static boolean isCollaborationStatus(CompanyStatus reportStatus) {
		switch (reportStatus) {
		case REVIEW_IN_PROGRESS:
		case JUDGEMENT_REQUIRED:
			// case JUDGEMENT_IN_PROGRESS:
		case JUDGEMENT_GIVEN:
			return true;
		}
		return false;
	}
}
