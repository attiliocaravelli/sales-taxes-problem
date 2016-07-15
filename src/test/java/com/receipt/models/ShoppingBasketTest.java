package com.receipt.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShoppingBasketTest {

	@Test
	public void addItem_Test(){
		ShoppingBasket basket = new ShoppingBasket("1");
		Item item = new Item("12", "description", "2.33", false, true);
		basket.addItem(item);
		String actual = basket.toString();
        String expected = "Output 1:\n12 imported description: 2.73\nSales Taxes: 0.40\nTotal: 2.73\n";
		assertEquals("Item adding failure", expected, actual);
	}
	
	@Test
	public void emptyBasket_Test(){
		ShoppingBasket basket = new ShoppingBasket("1");
		String actual = basket.toString();
        String expected = "Output 1:\nSales Taxes: 0.00\nTotal: 0.00\n";
		assertEquals("Empty basket failure", expected, actual);
	}
}
