package com.receipt.constants;

import java.math.BigDecimal;
/**
 * Item string consists in -> 
 * 		quantity [imported] productDescription {at} price
 * where:
 * at -> delimiter for product description
 * imported -> can exists or not
 * @author attilio
 *
 */
public class Configuration {

	/**
	 * Regex pattern to found numbers in a string
	 */
	public static final String 		NUMERIC_PATTERN = "\\d+(\\.\\d+)?";
	
	public static final String 		NEW_BASKET_TOKEN     = "Input";
	public static final String 		DESCRIPTION_TOKEN    = "at";
	public static final String 		PRODUCT_IMPORT_TOKEN = "imported";
	
	public static final String 		SPACE 		 = " ";
	public static final String 		NEWLINE 	 = "\n";
	
	public static final int 		SCALE = 2;
	public static final int 		TAXES_ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
	
	public static final BigDecimal  ONE_HUNDRED = new BigDecimal(100);
	public static final BigDecimal  SALES_TAX_PERCENTAGE = new BigDecimal(10);
	public static final BigDecimal  IMPORT_TAX_PERCENTAGE = new BigDecimal(5);
	public static final int         FORMAT_NUMBER_FACTOR = 20; // NearestToFiveCents
}
