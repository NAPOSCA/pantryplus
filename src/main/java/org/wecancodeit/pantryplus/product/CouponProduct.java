package org.wecancodeit.pantryplus.product;

import javax.persistence.Entity;

import org.wecancodeit.pantryplus.category.Category;

@Entity
public class CouponProduct extends Product {

	private int cost;
	private int couponlimit;

	public int getCost() {
		return cost;
	}

	public int getCouponLimit() {
		return couponlimit;
	}

	protected CouponProduct() {
		super();
	}

	public CouponProduct(String name, Category category, int cost) {
		this(name, category, cost, 5);
	}

	public CouponProduct(String name, Category category, int cost, int couponLimit) {
		super(name, category);
		this.cost = cost;
		this.couponlimit = couponLimit;
	}

}
