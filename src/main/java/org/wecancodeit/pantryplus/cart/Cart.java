package org.wecancodeit.pantryplus.cart;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.product.CouponProduct;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.user.User;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "cart")
	private Set<LineItem> lineItems;

	public User getUser() {
		return user;
	}

	@SuppressWarnings("unused")
	private Cart() {
	}

	public Cart(User user) {
		this.user = user;
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
		Product product = countedLineItem.getProduct();
		if (product instanceof CouponProduct) {
			CouponProduct couponProduct = (CouponProduct) product;
			int couponLimit = couponProduct.getCouponLimit();
			int quantity = countedLineItem.getQuantity();
			if (couponLimit > quantity) {
				countedLineItem.increaseQuantity(1);
			}
		} else {
			countedLineItem.increaseQuantity(1);
		}
		return countedLineItem;
	}

	public CountedLineItem decreaseProductByOne(long productId) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.reduceQuantity(1);
		return countedLineItem;
	}

	public CountedLineItem updateQuantityOfProduct(long productId, int quantity) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.setQuantity(quantity);
		return countedLineItem;
	}

	public void removeItemByProductId(long productId) {
		LineItem lineItem = getLineItemByProductId(productId);
		lineItems.remove(lineItem);
	}

	public void removeAllItems() {
		lineItems.removeIf((lineItem) -> {
			return true;
		});
	}

	public boolean has(long productId) {
		for (LineItem lineItem : lineItems) {
			if (lineItem.getProduct().getId() == productId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		if (id == ((Cart) obj).id) {
			return true;
		}

		return false;
	}

}
