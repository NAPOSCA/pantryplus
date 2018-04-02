package org.wecancodeit.pantryplus;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	private long id;

	@OneToMany(mappedBy = "cart")
	Collection<LineItem> lineItems;

	public Cart() {
	}

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
