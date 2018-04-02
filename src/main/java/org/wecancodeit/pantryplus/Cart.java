package org.wecancodeit.pantryplus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	private long id;

	// Set<Product> products = new HashSet<Product>();

	// public Set<Product> getProducts() {
	// return products;
	// }

	// public void addProduct(Product product) {
	// products.add(product);
	// }
	//
	// public void deleteProduct(Product product) {
	// products.remove(product);
	// }

	public long getId() {
		return id;
	}

}
