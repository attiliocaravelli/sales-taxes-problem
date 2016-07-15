package com.receipt.models;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.receipt.constants.Configuration;
import com.receipt.errors.ItemMalformedError;
import com.receipt.models.utilities.NumericUtilities;

/**
 * Model for a shopping basket
 * Some methods of the CRUD interface are not implemented
 * because they have not been requested
 * @author attilio
 *
 */
public class ShoppingBasket implements Basket<Item>{

	private String id = null;
	private List<Item> items = new LinkedList<>();
	private BigDecimal salesTaxes = BigDecimal.ZERO;
	private BigDecimal totalPrice = BigDecimal.ZERO;

	public ShoppingBasket() {
	}

	public ShoppingBasket(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getSalesTaxes() {
		return salesTaxes;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		String s = "Output " + id + ":\n";
		for (Item item : items) s += item.toString();
		s += "Sales Taxes: " + salesTaxes.setScale(Configuration.SCALE) + Configuration.NEWLINE;
		s += "Total: " + totalPrice.setScale(Configuration.SCALE) + Configuration.NEWLINE;
		return s;
	}

	public void addItem(Item item) throws ItemMalformedError {
		try {
			if (item == null ) throw new ItemMalformedError();
			BigDecimal itemPrice = new BigDecimal (item.getPrice());
			BigDecimal itemTaxes = calculateTaxes(itemPrice, item.isExempt(), item.isImported());
			BigDecimal itemPriceWithTaxes = itemPrice.add(itemTaxes);
			item.setPrice(itemPriceWithTaxes.toString());
			items.add(item);
			salesTaxes = salesTaxes.add(itemTaxes);
			totalPrice = totalPrice.add(itemPriceWithTaxes);
		} catch (NumberFormatException e) {
			throw new ItemMalformedError();
		}
	}
	
	@Override
	public boolean removeItem(Item t) {
		// TODO Not implemented because not required by the test
		return false;
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Not implemented because not required by the test
		return null;
	}
	
	@Override
	public boolean containItem(Item t) {
		// TODO Not implemented because not required by the test
		return false;
	}
	
	@Override
	public void clearItems() {
		// TODO Not implemented because not required by the test
	}

	@Override
	public int numItems() {
		// TODO Not implemented because not required by the test
		return 0;
	}
	
	private BigDecimal calculateTaxes(BigDecimal itemPrice, boolean isExempt, boolean isImported) {
		BigDecimal itemTaxes = BigDecimal.ZERO;
		if (!isExempt) {
			itemTaxes = itemTaxes.add(calculateTax(itemPrice, Configuration.SALES_TAX_PERCENTAGE));
		}
		if (isImported) {
			itemTaxes = itemTaxes.add(calculateTax(itemPrice, Configuration.IMPORT_TAX_PERCENTAGE));
		}
		return itemTaxes;
	}
	
	private BigDecimal calculateTax(BigDecimal price, BigDecimal percentAmount) {
		BigDecimal tax = NumericUtilities.calculatePercentage(price, percentAmount);
		return NumericUtilities.formatNumber(tax, Configuration.FORMAT_NUMBER_FACTOR);
	}
}
