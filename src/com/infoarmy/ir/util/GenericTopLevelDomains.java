/*
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.infoarmy.ir.util;

import com.infoarmy.util.URIUtil;

/**
 * @author Sambath@infoarmy.com on 13-Aug-2012 12:39:11 PM
 * 
 * 
 */
public enum GenericTopLevelDomains {
	AERO("aero"), ASIA("asia"), BIZ("biz"), CAT("cat"), COM("com"), CO("co"), COOP(
			"coop"), EDU("edu"), GOV("gov"), INFO("info"), INT("int"), IT("it"), IS(
			"is"), JOBS("jobs"), MIL("mil"), MOBI("mobi"), MUSEUM("museum"), NAME(
			"name"), NET("net"), ORG("org"), PRO("pro"), TEL("tel"), TRAVEL(
			"travel"), TV("tv"), XXX("xxx");

	private String gTLD = null;

	GenericTopLevelDomains(String gTLD) {
		this.gTLD = gTLD;
	}

	public String getGTLD() {
		return gTLD;
	}

	@Override
	public String toString() {
		return gTLD;
	}

	public static boolean isAcceptableRegionalDomain(String domainName) {
		URIUtil uriUtil = new URIUtil();
		boolean acceptable = true;
		if (uriUtil.isDomainNameRegionalSpecific(domainName)) {
			if (!domainName.endsWith(".us")) {
				acceptable = false;
			}
		}

		return acceptable;
	}
}
