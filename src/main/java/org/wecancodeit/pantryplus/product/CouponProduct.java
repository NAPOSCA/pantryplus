package org.wecancodeit.pantryplus.product;

import javax.persistence.Entity;

import org.wecancodeit.pantryplus.Category;

@Entity
public class CouponProduct extends Product {

	private int cost;
	
	
	protected CouponProduct() {
		super();
	}

	public CouponProduct(String name, Category category, int cost) {
		super(name, category);
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

}
