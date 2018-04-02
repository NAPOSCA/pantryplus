package org.wecancodeit.pantryplus;

import java.util.HashSet;
import java.util.Set;

public class Cart {

	Set<Product> products = new HashSet<Product>();

	public Set<Product> getProducts() {
		return products;
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public void deleteProduct(Product product) {
		products.remove(product);
	}

}
