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
			if (lineItem instanceof CountedLineItem) {
				if (lineItem.getProduct().getId() == productId)
					return lineItem;
			}
			if (lineItem.getProduct().getId() == productId) {
				return lineItem;
			}
		}
		return null;
	}

	public int getCartQuantity() {
		int totalQuantity = 0;
		for (LineItem item : lineItems) {
			if (item instanceof CountedLineItem) {
				totalQuantity += ((CountedLineItem) item).getQuantity();
			}
		}
		return totalQuantity;
	}

	public int getLineItemQuantityByProductId(long productId) {
		LineItem lineItem = getLineItemByProductId(productId);
		if (lineItem == null) {
			return 0;
		}
		if (lineItem instanceof CountedLineItem) {
			return ((CountedLineItem) lineItem).getQuantity();
		}
		return 1;
	}

}
