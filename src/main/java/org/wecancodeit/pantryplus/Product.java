package org.wecancodeit.pantryplus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@ManyToOne
	private Category category;

	@SuppressWarnings("unused")
	private Product() {
	}

	public Product(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Product) obj).id;
	}

}
