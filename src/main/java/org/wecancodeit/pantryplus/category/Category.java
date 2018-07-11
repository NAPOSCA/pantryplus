package org.wecancodeit.pantryplus.category;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.wecancodeit.pantryplus.product.Product;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<Product> products;

	protected Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<Product> getProducts() {
		return products;
	}
}
