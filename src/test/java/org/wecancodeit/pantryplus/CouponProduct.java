package org.wecancodeit.pantryplus;

public class CouponProduct extends Product {

	public CouponProduct(String name, Category category, int cost) {
		super(name, category);
	}
	
	public int getCost() {
		return 1;
	}

}
