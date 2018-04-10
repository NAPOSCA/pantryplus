package org.wecancodeit.pantryplus.product;

import javax.persistence.Entity;

import org.wecancodeit.pantryplus.category.Category;

@Entity
public class CouponProduct extends Product {

	private int cost;
	private int couponlimit;

	protected CouponProduct() {
		super();
	}

	public CouponProduct(String name, Category category, int cost) {
		super(name, category);
		this.cost = cost;
	}

	public CouponProduct(String name, Category category, int cost, int couponlimit) {
		super(name, category);
		this.cost = cost;
		this.couponlimit = couponlimit;
	}

	public int getCost() {
		return cost;
	}

	public int getCouponLimit() {
		return couponlimit;
	}

}
