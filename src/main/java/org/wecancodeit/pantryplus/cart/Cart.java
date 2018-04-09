package org.wecancodeit.pantryplus.cart;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	private long id;

	@OneToMany(mappedBy = "cart")
	Set<LineItem> lineItems;

	public Cart() {
	}

	public long getId() {
		return id;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public LineItem getLineItemByProductId(long productId) {
		for (LineItem lineItem : lineItems) {
			if (lineItem.getProduct().getId() == productId) {
				return lineItem;
			}
		}
		return null;
	}

	public int getCartQuantity() {
		int totalQuantity = 0;
		for (LineItem item : lineItems) {
			if (isCountedLineItem(item)) {
				totalQuantity += ((CountedLineItem) item).getQuantity();
			}
		}
		return totalQuantity;
	}

	private boolean isCountedLineItem(LineItem item) {
		return item instanceof CountedLineItem;
	}

	public int getLineItemQuantityByProductId(long productId) {
		LineItem lineItem = getLineItemByProductId(productId);
		if (lineItem == null) {
			return 0;
		}
		if (isCountedLineItem(lineItem)) {
			CountedLineItem countedLineItem = (CountedLineItem) lineItem;
			return countedLineItem.getQuantity();
		}
		return 1;
	}

	public CountedLineItem increaseProductByOne(long productId) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.addQuantity(1);
		return countedLineItem;
	}

	public CountedLineItem decreaseProductByOne(long productId) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.reduceQuantity(1);
		return countedLineItem;
	}

	public LineItem addItem(long productId) {
		return null;
	}

	public LineItem addCountedItem(long productId) {
		return null;
	}

	public void updateQuantityOfProduct(long productId, int quantity) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.setQuantity(quantity);
	}

	public void removeItemByProductId(long productId) {
	}

	public void removeAllItems() {
	}

}
