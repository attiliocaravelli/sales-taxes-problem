package com.receipt.models.utilities;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.receipt.constants.Configuration;

public class NumericUtilities {
	
	public static String parseString(String data, String regex) {
		Matcher matcher = Pattern.compile(regex).matcher(data);
		if (!matcher.find()) return null;    
		return matcher.group();
	}
	
	public static BigDecimal formatNumber(BigDecimal number, int formatFactor) {
		BigDecimal result =  new BigDecimal(Math.ceil(number.doubleValue() * formatFactor) / formatFactor);
		return result.setScale(Configuration.SCALE, Configuration.TAXES_ROUNDING_MODE);
	}
	
	public static BigDecimal calculatePercentage(BigDecimal number, BigDecimal percentageAmount){
		return number.multiply(percentageAmount).divide(Configuration.ONE_HUNDRED);
	}
}
