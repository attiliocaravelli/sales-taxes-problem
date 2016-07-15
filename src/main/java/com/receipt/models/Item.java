package com.receipt.models;

import com.receipt.constants.Configuration;

/**
 * Model for an item
 * @author attilio
 *
 */
public class Item {

	private String quantity = null;
	private String description = null;
	private String price = null;
	private boolean isExempt = false;
	private boolean isImported = false;
	
	public Item() {
		
	}
	
	public Item(String quantity, String description, String price, boolean isExempt, boolean isImported) {
		super();
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.isExempt = isExempt;
		this.isImported = isImported;
	}

	@Override
	public String toString() {
		String s = quantity + " ";
		if (isImported) s += Configuration.PRODUCT_IMPORT_TOKEN + " ";
		s += description + ": " + price + Configuration.NEWLINE;
		return s; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isExempt ? 1231 : 1237);
		result = prime * result + (isImported ? 1231 : 1237);
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isExempt != other.isExempt)
			return false;
		if (isImported != other.isImported)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public boolean isExempt() {
		return isExempt;
	}

	public void setExempt(boolean isExempt) {
		this.isExempt = isExempt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}
}
