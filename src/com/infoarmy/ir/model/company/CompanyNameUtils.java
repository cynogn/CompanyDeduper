/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 */
package com.infoarmy.ir.model.company;


/**
 * @author Manoj Vivek
 * 
 */
public class CompanyNameUtils {

	static String exclusions = "( Inc| inc| INC| LLC| Llc| llc| LLP| Llp| llp| LLLP| Lllp| lllp| L\\.P\\.|l\\.p\\.|LP|L P| lp| LTD| Ltd| ltd| corporation| Corporation| CORP| Corp| corp| CO | co | Co |PLC|Plc|plc|\\.|,|&)";// applicationPropertyUtils.getProperty("company.name.exclusions");

	public static boolean compareCompanyNames(String name1, String name2) {
		String nameOne = new String(name1);
		String nameTwo = new String(name2);
		nameOne = nameOne.replaceAll(exclusions, "");
		nameTwo = nameTwo.replaceAll(exclusions, "");
		return nameOne.trim().equalsIgnoreCase(nameTwo.trim());
	}

	public static String getStrippedName(String name) {
		String nameTemp = new String(name);
		nameTemp = nameTemp.replaceAll(exclusions, "");
		return nameTemp.trim();
	}
}
