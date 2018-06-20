package org.wecancodeit.pantryplus.product;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.lineitem.LineItem;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "HashTagIDGenerator")
	@SequenceGenerator(name = "HashTagIDGenerator", sequenceName = "SEQ_HASHTAG_ID")
	private long id;
	private String name;
	private String image;

	@JsonIgnore
	@ManyToOne
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Collection<LineItem> lineItem;

	protected Product() {
	}

	public Product(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	public Product(String name, Category category, String image) {
		this.name = name;
		this.category = category;
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

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
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

	public Category getCategory() {
		return category;
	}

}
