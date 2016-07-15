package com.receipt.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {
		
	@Test
	public void toString_Test(){
		Item item = new Item("12", "description", "2.33", false, true);
		String actual = item.toString();
		String expected = "12 imported description: 2.33\n";
		assertEquals("Item string conversion is different", expected, actual);
		expected = "12 description: 2.33\n";
		item.setImported(false);
		actual = item.toString();
		assertEquals("Item string conversion is different", expected, actual);
	}
	
	@Test
	public void egualItems_Test(){
		Item item1 = new Item("12", "description", "2.33", false, true);
		Item item2 = new Item("123", "description", "2.33", false, true);
		Item item3 = new Item("12", "description", "2.33", false, true);
		assertEquals("Item1 and Item3 are differents", item1, item3);
		assertNotEquals("Item1 and Item2 are equals", item1, item2);
	}
}
