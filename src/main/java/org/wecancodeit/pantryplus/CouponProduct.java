package org.wecancodeit.pantryplus;

import javax.persistence.Entity;

@Entity
public class CouponProduct extends Product {

	private int cost;

	public CouponProduct(String name, Category category, int cost) {
		super(name, category);
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

}
