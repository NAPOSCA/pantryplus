package org.wecancodeit.pantryplus.product;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.wecancodeit.pantryplus.product.Product;

public class ProductTest {

	private Product underTest = new Product("Apple", null);

	@Test
	public void shouldHaveName() {
		assertThat(underTest.getName(), is("Apple"));
	}

	@Test
	public void shouldHaveEqualsReturnFalseIfNull() {
		assertThat(underTest.equals(null), is(false));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void shouldHaveEqualsReturnFalseIfDifferentClass() {
		assertThat(underTest.equals("Apple"), is(false));
	}
	
	@Test
	public void shouldHaveProductEqualsItself() {
		assertThat(underTest.equals(underTest), is(true));
	}
	
	@Test
	public void shouldHaveImageCan() {
		underTest = new Product("Can", null, "can.png");
		String image = underTest.getImage();
		assertThat(image, is("can.png"));
	}
	
	@Test
	public void shouldHaveImageBread() {
		underTest = new Product("Bread", null, "bread.png");
		String image = underTest.getImage();
		assertThat(image, is("bread.png"));
	}
	
	@Test
	public void shouldGetNameFromImageConstructor() {
		underTest = new Product("Cheese", null, "cheese.png");
		String name = underTest.getName();
		assertThat(name, is("Cheese"));
	}
}
