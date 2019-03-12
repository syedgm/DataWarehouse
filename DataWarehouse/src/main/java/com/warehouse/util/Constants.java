/**
 * 
 */
package com.warehouse.util;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

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
	
	// Date Formatter
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	// All Possible Currencies
	public static final List<String> currencies = getAllCurrencies();

	
	private static List<String> getAllCurrencies() {
        List<String> currencies = new ArrayList<>();
        Locale[] locs = Locale.getAvailableLocales();

        for(Locale loc : locs) {
            try {
            	String val=Currency.getInstance(loc).getCurrencyCode();
                if(!currencies.contains(val))
                	currencies.add(val);
            } catch(Exception exc) {
                // Locale not found
            }
        }

        return currencies;
    }
	
}
