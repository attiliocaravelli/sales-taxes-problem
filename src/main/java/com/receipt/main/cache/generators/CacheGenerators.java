package com.receipt.main.cache.generators;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility for exempt products
 * This utility simulate a DB
 * @author attilio
 *
 */
public class CacheGenerators {
	
		/**
		 * Fake data generator 
		 * Map<key, value> key and value is the "name of product"
		 * @return
		 */
		public static Map<String, String> cacheExemptProductsGenerator() {
			Map<String, String> exemptProductsMap = new HashMap<>();
			exemptProductsMap.put("book", "book");
			exemptProductsMap.put("chocolate bar", "chocolate bar");
			exemptProductsMap.put("box of chocolates", "box of chocolates");
			exemptProductsMap.put("packet of headache pills", "packet of headache pills");		
			return exemptProductsMap;
		}
}
