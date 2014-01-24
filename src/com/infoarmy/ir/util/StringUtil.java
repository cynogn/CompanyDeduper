/*
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 * 
 */
package com.infoarmy.ir.util;

import java.util.regex.Pattern;

/**
 * @author rmadhavan
 * 
 */
public class StringUtil {

	public static Pattern COMMA_PATTERN = Pattern.compile(",");

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String[] stringArray) {
		if ((stringArray == null) || (stringArray.length == 0)) {
			return true;
		}
		return false;
	}

	public static String getEmailDomain(String email) {
		return (email != null) ? email.substring(email.indexOf("@") + 1) : null;
	}

	public static final String getDisplayNameForEmail(String firstName,
			String email) {
		if (StringUtil.isEmpty(firstName)) {
			return email;
		}
		return firstName;
	}

	public static String getDisplayName(String firstName, String lastName,
			String name, String email) {
		if (StringUtil.isEmpty(firstName)) {
			if (StringUtil.isEmpty(lastName)) {
				if (StringUtil.isEmpty(name)) {
					return email;
				} else {
					return name;
				}
			} else {
				return lastName;
			}
		} else {
			if (StringUtil.isEmpty(lastName)) {
				return firstName;
			} else {
				return firstName + " " + lastName;
			}
		}
	}
}
