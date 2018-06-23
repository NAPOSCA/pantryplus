package org.wecancodeit.pantryplus.product;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.lineitem.LineItem;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String image;
	
	@JsonIgnore
	@ManyToOne
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Collection<LineItem> lineItems;
	
	public Product() {
	}

	public Product(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	public Product(String name, Category category, String image) {
		this(name, category);
		this.image = image;
	}

	public long getId() {
		return id;
	}
	
	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}
	
	public Category getCategory() {
		return category;
	}

}
