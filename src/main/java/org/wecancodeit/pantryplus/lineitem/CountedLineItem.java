package org.wecancodeit.pantryplus.lineitem;

import javax.persistence.Entity;

import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.product.PricedProduct;
import org.wecancodeit.pantryplus.product.Product;

@Entity
public class CountedLineItem extends LineItem {

	protected int quantity;
	int couponsUsed;

	public int getQuantity() {
		return quantity;
	}

	public int getCouponsUsed() {
		return couponsUsed;
	}

	public CountedLineItem() {
	}

	public CountedLineItem(Cart cart, Product product, int quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		if (quantity < 0) {
			this.quantity = 0;
		}
		couponsUsed = 0;
		updateCouponsUsed();
		if (product.getCategory().getName() == "Meat") {
			if(!cart.willAddingQuantityBeWithinMeatLimit()) {
				detachFromCart();
			}
		} else if (product instanceof PricedProduct) {
			PricedProduct couponProduct = (PricedProduct) product;
			if (!cart.willAddingQuantityBeWithinCouponLimit(couponProduct, quantity)) {
				detachFromCart();
			}
		}
	}

	private void updateCouponsUsed() {
		if (hasCouponProduct()) {
			int cost = ((PricedProduct) this.product).getPrice();
			couponsUsed = cost * this.quantity;
		}
	}

	public void increaseQuantity(int quantityToIncreaseBy) {
		if (quantityToIncreaseBy > 0) {
			quantity += quantityToIncreaseBy;
		}
		updateCouponsUsed();
	}

	public void reduceQuantity(int quantityToReduceBy) {
		if (quantityToReduceBy > 0) {
			quantity -= quantityToReduceBy;
		}
		if (quantity < 1) {
			quantity = 0;
			detachFromCart();
		}
		updateCouponsUsed();
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		if (quantity < 1) {
			this.quantity = 0;
			detachFromCart();
		}
		updateCouponsUsed();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountedLineItem other = (CountedLineItem) obj;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
