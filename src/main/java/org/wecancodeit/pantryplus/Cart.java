package org.wecancodeit.pantryplus;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
		for(LineItem lineItem: lineItems) {
			if(lineItem.getProduct().getId() == productId) {
				return lineItem;
			}	
		}
		return null;
	}
	
	public int getCartQuantity() {
		int totalQuantity = 0;
		for (LineItem item : lineItems) {
			totalQuantity += item.getQuantity();
		}
		return totalQuantity;
	}

}
