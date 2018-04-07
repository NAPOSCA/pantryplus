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
}
