/*
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.infoarmy.util;

import java.net.URI;

import com.infoarmy.ir.util.GenericTopLevelDomains;
import com.infoarmy.ir.util.StringUtil;
//import com.infoarmy.gwt.report.shared.StringUtils;
//import com.infoarmy.gwt.report.shared.StringUtils;

/**
 * 
 * @author Saravanakumar
 * 
 */
public class URIUtil {

	public URIUtil() {
	}

	/**
	 * @param websiteInp
	 * @return domain name excluding "www.", if it is there in the url
	 */
	public static String getDomainName(String website) {
		if (StringUtil.isEmpty(website)) {
			return null;
		}
		website = website.trim();

		try {
			if (!website.startsWith("http:") && !website.startsWith("https:")) {
				website = "http://" + website;
			}
			URI uri = new URI(website);
			String hostName = uri.getHost();
			if (hostName == null) {
				return null;
			}
			if (hostName.startsWith("www.")) {
				hostName = hostName.substring(4);
			} else if (hostName.indexOf(".") < 0) {
				return null;
			}
			return hostName;
		} catch (Exception ee) {
			// Ignoring this exception as null is returned for invalid URI
		}
		return null;
	}

	
	
}