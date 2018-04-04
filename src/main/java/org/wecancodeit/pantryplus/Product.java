package org.wecancodeit.pantryplus;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@JsonIgnore
	@ManyToOne
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Collection<LineItem> lineItem;

	@SuppressWarnings("unused")
	private Product() {
	}

	public Product(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public Collection<LineItem> getLineItem() {
		return lineItem;
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

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		if (id == ((Product) obj).id) {
			return true;
		}

		return false;
	}

}
