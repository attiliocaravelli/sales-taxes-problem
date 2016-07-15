package com.receipt.input.generators;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Fake input data generator
 * @author attilio
 *
 */
public class BasketGenerators {
	
	public static final String basket1 = "Input 1:\n1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85\n"; 
	public static final String basket2 = "Input 2:\n1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50\n";
	public static final String basket3 = "Input 3:\n1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25\n";
	
	public static final String basketWithPointlessBlankLines = "Input 1:\n1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85 \n" + "\n\n" + basket2;
	
	public static final String emptyBasket = "Input 4:\n";
	
	public static final String malformedBasket1 = "Input 3:\n1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25\n";
	
	public static final String expectedOutput1 = "Output 1:\n1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83"; 
	public static final String expectedOutput2 = "Output 2:\n1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15"; 
	public static final String expectedOutput3 = "Output 3:\n1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68";
	
	public static final String expectedOutputWithPointlessBlankLines = expectedOutput1 + "\n" + expectedOutput2;
	
	public static final String expectedEmptyOutput = "Output 4:\nSales Taxes: 0.00\nTotal: 0.00";
	
	public InputStream toInputStream(String input) {	
		return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
	}


}
