package org.wecancodeit.pantryplus;

public class CountedLineItem extends LineItem {

	protected int quantity;

	public CountedLineItem(Cart cart, Product product, int quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int quantityToAdd) {
		quantity += quantityToAdd;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int totalCouponCost() {
		if (hasCouponProduct()) {
			return quantity * ((CouponProduct) product).getCost();
		}
		return 0;
	}
}
