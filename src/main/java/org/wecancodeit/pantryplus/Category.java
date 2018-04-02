package org.wecancodeit.pantryplus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@SuppressWarnings("unused")
	private Category() {}

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

}
