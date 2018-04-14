package org.wecancodeit.pantryplus.product;

import javax.persistence.Entity;

import org.wecancodeit.pantryplus.category.Category;

@Entity
public class PricedProduct extends LimitedProduct {

	private int price;
	
	public int getPrice() {
		return price;
	}

	protected PricedProduct() {
		super();
	}

	public PricedProduct(String name, Category category, int price) {
		this(name, category, 5, price);
	}

	public PricedProduct(String name, Category category, int maximumQuantity, int price) {
		super(name, category, maximumQuantity);
		this.price = price;
	}
	
	public PricedProduct(String name, Category category, String image, int maximumQuantity, int price) {
		super(name, category, image, maximumQuantity);
		this.price = price;
	}

}
