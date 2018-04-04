package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LineItemTest {

	private Product product;
	private Product product2;
	private LineItem lineItem;

	@Before
	public void setUp() {
		product = new Product("testFruit", null);
		product2 = new Product("testFruit2", null);
		lineItem = new LineItem(product, 1);
	}

	@Test
	public void lineItemShouldHaveProduct() {
		assertThat(lineItem.getProduct().getName(), is("testFruit"));
	}

	@Test
	public void lineItemShouldHaveQuantity() {
		assertThat(lineItem.getQuantity(), is(1));
	}

	@Test
	public void shouldHaveEqualsReturnFalseIfNull() {
		assertThat(lineItem.equals(null), is(false));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void shouldHaveEqualsReturnFalseIFDifferentClass() {
		assertThat(lineItem.equals(product2), is(false));
	}
	
	@Test
	public void shouldCreateSingleElementLineItem() {
		Product product = new Product("product", null);
		LineItem underTest = new LineItem(product);
		assertThat(underTest.getQuantity(), is(1));
	}
}
