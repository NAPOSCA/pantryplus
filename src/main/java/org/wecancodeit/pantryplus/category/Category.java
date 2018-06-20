package org.wecancodeit.pantryplus.category;

import static java.util.Arrays.asList;
import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.wecancodeit.pantryplus.product.Product;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "HashTagIDGenerator")
	@SequenceGenerator(name = "HashTagIDGenerator", sequenceName = "SEQ_HASHTAG_ID")
	private long id;
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<Product> products;

	@SuppressWarnings("unused")
	private Category() {
	}

	public Category(String name, Product... products) {
		this.name = name;
		this.products = new HashSet<Product>(asList(products));
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
