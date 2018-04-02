package org.wecancodeit.pantryplus;

public class LineItem {

	private Product product;
	private int quantity;

	public LineItem(Product product) {
		this.product = product;
		quantity = 1;
	}

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
}
