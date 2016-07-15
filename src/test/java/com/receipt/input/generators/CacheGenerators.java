package com.receipt.input.generators;

import java.util.HashMap;
import java.util.Map;

public class CacheGenerators {

	/**
	 * Fake input data generator for the exempt products from sales taxes
	 * Map<key, value> key and value is the "name of product"
	 * @return
	 */
	public static Map<String, String> cacheExemptProductsGenerator() {
		Map<String, String> productCategory = new HashMap<>();
		productCategory.put("book", "book");
		productCategory.put("chocolate bar", "chocolate bar");
		productCategory.put("box of chocolates", "box of chocolates");
		productCategory.put("packet of headache pills", "packet of headache pills");		
		return productCategory;
	}
}
