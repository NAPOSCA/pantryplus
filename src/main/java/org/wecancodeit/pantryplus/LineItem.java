package org.wecancodeit.pantryplus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LineItem {

	@Id
	@GeneratedValue
	private long id;

	private Cart cart;
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

	public LineItem(Cart cart, Product product, int quantity) {
		this.cart = cart;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public Cart getCart() {
		return cart;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

}
