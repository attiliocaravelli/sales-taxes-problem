package com.receipt.models.utilities;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.receipt.constants.Configuration;

public class NumericUtilitiesTest {

	@Test
	public void parseNumericString_Test(){
		String test = "11 aa bb";
		String actual = "11";
        String expected = NumericUtilities.parseString(test, Configuration.NUMERIC_PATTERN);
		assertEquals("Item adding failure", expected, actual);
	}
	
	@Test
	public void formatNumber_Test(){
		BigDecimal num = new BigDecimal("3.556");
		BigDecimal actual = NumericUtilities.formatNumber(num, Configuration.FORMAT_NUMBER_FACTOR);
        BigDecimal expected = new BigDecimal("3.60");
        BigDecimal expected1 = new BigDecimal("3.56");
		assertEquals("Empty basket failure", expected, actual);
		assertNotEquals("Empty basket failure", expected1, actual);
	}
	
	@Test
	public void calculatePercentage_Test(){
		BigDecimal num = new BigDecimal("10.00");
		BigDecimal percentage = new BigDecimal("5.00");
		BigDecimal actual = NumericUtilities.calculatePercentage(num, percentage);
		BigDecimal expected = new BigDecimal("0.5000");
		assertEquals("Empty basket failure", expected, actual);
	}
}
