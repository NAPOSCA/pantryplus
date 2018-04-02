package org.wecancodeit.pantryplus;

import java.util.Collection;
import java.util.HashSet;
import static java.util.Arrays.asList;

public class Category {
	private String name;
	private Collection<Product> products;

	public Category(String name, Product... products) {
		this.name = name;
		this.products = new HashSet<>(asList(products));
	}

	public String getName() {
		return name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

}
