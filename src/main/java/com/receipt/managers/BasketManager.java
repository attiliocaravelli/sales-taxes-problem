package com.receipt.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import com.receipt.constants.Configuration;
import com.receipt.errors.BasketMalformedError;
import com.receipt.errors.ItemMalformedError;
import com.receipt.errors.NoExemptProductCacheFoundError;
import com.receipt.models.Item;
import com.receipt.models.utilities.NumericUtilities;
import com.receipt.models.ShoppingBasket;

/**
 * Shopping Basket Manager 
 * @author attilio
 *
 */
public class BasketManager {

	private static final Logger LOGGER = Logger.getLogger(BasketManager.class);
	/**
	 * Cache of exempt products
	 */
	private Map<String,String> cacheExemptProducts = null;

	public BasketManager(){
	}

	public BasketManager(Map<String,String> cacheExemptProducts){
		this.cacheExemptProducts = cacheExemptProducts;
	}

	public Map<String,String> getCacheExemptProducts() {
		return cacheExemptProducts;
	}

	public void setCacheExemptProducts(Map<String,String> cacheExemptProducts) {
		this.cacheExemptProducts = cacheExemptProducts;
	}

	/**
	 * Read the inputs and write the receipts
	 * The idea is like a SAX Parser in order to avoid to keep in memory
	 * big amount of data -> memory overflow
	 * @param input -  General stream like file, string -> easily convertible
	 * @param output -  General stream like file, string -> easily convertible
	 */
	public void writeReceipts(InputStream input, OutputStream output) {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
		String basketTokenLine = null;
		String itemLine = null;
		try {
			basketTokenLine = br.readLine();
			while (basketTokenLine != null) {
				if(!isValidString(basketTokenLine)) {
					basketTokenLine = br.readLine();
					continue;
				}
				ShoppingBasket sb = null;
				if (basketTokenLine != null && basketTokenLine.contains(Configuration.NEW_BASKET_TOKEN)) {	
					sb = parseBasket(basketTokenLine);
					basketTokenLine = null;
				}
				while ((itemLine = br.readLine()) != null) {
					// jump potential blank lines
					if(!isValidString(itemLine)) continue;
					if (itemLine.contains(Configuration.NEW_BASKET_TOKEN)) {
						basketTokenLine = itemLine;
						itemLine = null;break;
					} else {
						sb.addItem(parseItem(itemLine));
					}

				}
				bw.write(sb.toString());
				bw.flush();
			}
		}  catch (IOException e) {
			LOGGER.error("IO error", e);
		}  catch (BasketMalformedError e) {
			LOGGER.error("Basket malformed at " + itemLine);
		} catch (ItemMalformedError e) {
			LOGGER.error("Item malformed at " + itemLine);
		}  catch (NoExemptProductCacheFoundError e) {
			LOGGER.error("No Product Found at " + itemLine);
		}
	}

	/**
	 * Parse a string for a new basket
	 * @param basket - is the string to parse
	 * @return ShoppingBasket
	 * @throws BasketMalformedError
	 */
	public ShoppingBasket parseBasket(String basket) throws BasketMalformedError{
		if (!isValidString(basket)) throw new BasketMalformedError(); 
		if (!basket.contains(Configuration.NEW_BASKET_TOKEN)) throw new BasketMalformedError();
		int idx = basket.indexOf(Configuration.NEW_BASKET_TOKEN);
		if (idx != 0) throw new BasketMalformedError();
		String id = NumericUtilities.parseString(basket, Configuration.NUMERIC_PATTERN);
		if (!isValidString(id)) throw new BasketMalformedError(); 
		return new ShoppingBasket(id);              
	}

	/**
	 * Parse a string for a new item
	 * @param item - is the string to parse
	 * @return Item
	 */
	public Item parseItem(String item) {
		String importedSubString = Configuration.SPACE + Configuration.PRODUCT_IMPORT_TOKEN + Configuration.SPACE;
		String productSubString = Configuration.SPACE + Configuration.DESCRIPTION_TOKEN + Configuration.SPACE;
		if (!isValidString(item)) throw new ItemMalformedError();
		boolean imported = item.contains(importedSubString);
		if (imported) item = item.replaceFirst(importedSubString, Configuration.SPACE);
		int idx = item.indexOf(productSubString);
		if (idx == -1) throw new ItemMalformedError();
		String qtyAndDescr = item.substring(0, idx).trim();
		String price = item.substring(idx + productSubString.length(), item.length()).trim();
		if (!isValidString(price)) throw new ItemMalformedError();
		String qty = getNumericValue(qtyAndDescr);
		if (!isValidString(qty)) throw new ItemMalformedError();
		String descr = qtyAndDescr.substring(qty.length()).trim();
		if (!isValidString(descr)) throw new ItemMalformedError();
		if (cacheExemptProducts == null) throw new NoExemptProductCacheFoundError();
		boolean exempt = cacheExemptProducts.containsKey(descr);
		return new Item(qty,descr,price,exempt,imported);
	}

	private boolean isValidString(String s) {
		if (s == null) return false;
		if (s != null && s.trim().isEmpty()) return false;
		return true;
	}

	private String getNumericValue(String input) throws ItemMalformedError{
		String number = NumericUtilities.parseString(input, Configuration.NUMERIC_PATTERN);
		if (number == null || number.isEmpty()) throw new ItemMalformedError();
		return number;
	}
}