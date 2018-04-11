package org.wecancodeit.pantryplus.lineitem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.product.CouponProduct;
import org.wecancodeit.pantryplus.product.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LineItem {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	protected Cart cart;

	@ManyToOne
	protected Product product;

	protected LineItem() {
	}

	public LineItem(Cart cart, Product product) {
		this.product = product;
		this.cart = cart;
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

	public boolean hasCouponProduct() {
		return product instanceof CouponProduct;
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

	public void detachFromCart() {
		cart = null;
	}

}
