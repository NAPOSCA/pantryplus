package org.wecancodeit.pantryplus.product;

import javax.persistence.Entity;

import org.wecancodeit.pantryplus.category.Category;

@Entity
public class LimitedProduct extends Product {

	private int maximumQuantity;

	public LimitedProduct() {
	}

	public LimitedProduct(String name, Category category, int maximumQuantity) {
		super(name, category);
		this.maximumQuantity = maximumQuantity;
	}
	
	public LimitedProduct(String name, Category category, String image, int maximumQuantity) {
		super(name, category, image);
		this.maximumQuantity = maximumQuantity;
	}

	public int getMaximumQuantity() {
		return maximumQuantity;
	}

}
