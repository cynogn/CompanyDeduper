/*
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.infoarmy.ir.cache.base;

import java.io.Serializable;

/**
 * @author Karthik Jayaraman Base interface that defines Cache Entity and
 *         enforces it to be serializable
 * 
 */
public interface Cacheable extends Serializable {

	/**
	 * @return the id which will be used as the key to store the object in cache
	 */
	String getCacheId();
}
