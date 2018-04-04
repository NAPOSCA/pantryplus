package org.wecancodeit.pantryplus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LineItem {

	@Id
	@GeneratedValue
	private long id;

	@JsonIgnore
	@ManyToOne
	private Cart cart;

	@ManyToOne
	private Product product;
	private int quantity;

	@SuppressWarnings("unused")
	private LineItem() {
	}

	public LineItem(Product product) {
		this(product, 1);
	}

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public LineItem(Cart cart, Product product, int quantity) {
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

	public void addOneQuantity(int quantity) {
		quantity += 1;
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

		if (id == ((LineItem) obj).id) {
			return true;
		}

		return false;
	}

}
