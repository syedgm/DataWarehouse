/**
 * 
 */
package com.warehouse.util;

import java.time.format.DateTimeFormatter;

/**
 * @author dell
 *
 */
public class Constants {
	
	// CSV File Headers
	public static final String DEAL_ID = "DEAL_ID";
	public static final String FROM_CURRENCY = "FROM_CURRENCY";
	public static final String TO_CURRENCY = "TO_CURRENCY";
	public static final String TIMESTAMP = "TIMESTAMP";
	public static final String AMOUNT = "AMOUNT";
	
	// Date Formator
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
