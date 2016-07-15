package com.receipt.managers;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.receipt.errors.BasketMalformedError;
import com.receipt.errors.ItemMalformedError;
import com.receipt.errors.NoExemptProductCacheFoundError;
import com.receipt.input.generators.BasketGenerators;
import com.receipt.input.generators.CacheGenerators;
import com.receipt.managers.BasketManager;
import com.receipt.managers.utilities.ConsoleOutputCapturer;
import com.receipt.models.Item;
import com.receipt.models.ShoppingBasket;

public class BasketManagerTest {

	private BasketManager sbm = null;
	private BasketGenerators bg = null;
	ConsoleOutputCapturer consoleRedirection = null;
	
	@Before 
	public void initialize() {
		sbm = new BasketManager(CacheGenerators.cacheExemptProductsGenerator());
		bg = new BasketGenerators();
		consoleRedirection = new ConsoleOutputCapturer();
	}
	
	@Test
	public void writeReceipts_TestCasesProvided_Test(){
		String actualOutput1 = getOutputReceipts(BasketGenerators.basket1);
		String actualOutput2 = getOutputReceipts(BasketGenerators.basket2);
		String actualOutput3 = getOutputReceipts(BasketGenerators.basket3);
		assertEquals("Different output1",BasketGenerators.expectedOutput1,actualOutput1);
		assertEquals("Different output2",BasketGenerators.expectedOutput2,actualOutput2);
		assertEquals("Different output3",BasketGenerators.expectedOutput3,actualOutput3);
	}
	
	@Test
	public void writeReceipts_TestCasesWithBlankLines_Test(){
		String actualOutput1 = getOutputReceipts(BasketGenerators.basketWithPointlessBlankLines);
		assertEquals("Different output1",BasketGenerators.expectedOutputWithPointlessBlankLines,actualOutput1);
	}
	
	@Test
	public void writeReceipts_EmptyBasket_Test(){
		String actualOutput = getOutputReceipts(BasketGenerators.emptyBasket);
		assertEquals("Different empty output",BasketGenerators.expectedEmptyOutput,actualOutput);
	}
	
	@Test
	public void writeReceipts_EmptyInput_Test(){
		String actualOutput = getOutputReceipts(" ");
		String emptyStringExpected = "";
		assertEquals("Different empty output",emptyStringExpected,actualOutput);
		actualOutput = getOutputReceipts("");
		assertEquals("Different empty output",emptyStringExpected,actualOutput);
		actualOutput = getOutputReceipts("\n");
		assertEquals("Different empty output",emptyStringExpected,actualOutput);
	}
	
	@Test
	public void parseBasketId_Test(){
		String inputShoppingBasket = "Input 1:";
		String expectedId = "1";
		ShoppingBasket actual = sbm.parseBasket(inputShoppingBasket);
		assertEquals("Different id", expectedId, actual.getId());
	}
	
	@Test(expected=BasketMalformedError.class)
	public void parseBasketId_Malformed_Test(){
		String inputShoppingBasket = "1 Input:";
	    sbm.parseBasket(inputShoppingBasket);
	}
	
	@Test(expected=BasketMalformedError.class)
	public void parseBasketId_MoreTwoElements_Test(){
		String inputShoppingBasket = "Input: malformed";
	    sbm.parseBasket(inputShoppingBasket);
	}
	
	@Test
	public void parseItem_Test(){
		String itemAsString = "1 book at 12.49";
		Item expectedItem = new Item("1","book","12.49",true,false);
		Item actualItem = sbm.parseItem(itemAsString);
		assertTrue("Different items", expectedItem.equals(actualItem));
		String itemImportedAsString = "1 imported book at 12.49";
		Item actualItemImported = sbm.parseItem(itemImportedAsString);
	    expectedItem.setImported(true);
		assertTrue("Different items", expectedItem.equals(actualItemImported));
	}

	@Test(expected=ItemMalformedError.class)
	public void parseitem_LessFourElements_Test(){
		String itemAsString = "1 book 12.49";
		sbm.parseItem(itemAsString);
	}
	
	@Test(expected=ItemMalformedError.class)
	public void parseitem_WithoutProductDelimiter_Test(){
		String itemAsString = "1 audio book 12.49";
	    sbm.parseItem(itemAsString);
	}
	
	@Test(expected=NoExemptProductCacheFoundError.class)
	public void parseItem_NoProductRecognized_Test(){
		sbm.setCacheExemptProducts(null);
		String itemAsString = "1 bookB at 12.49";
	    sbm.parseItem(itemAsString);
	}
	
	private String getOutputReceipts(String input){
		InputStream inputStream = bg.toInputStream(input);
		consoleRedirection.start();
		sbm.writeReceipts(inputStream, System.out);
		return consoleRedirection.stop().trim();
	}
}
