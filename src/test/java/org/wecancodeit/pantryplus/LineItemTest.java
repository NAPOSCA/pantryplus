package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LineItemTest {

	@Test
	public void lineItemShouldHaveProduct() {
		Product product = new Product("testFruit");
		LineItem underTest = new LineItem(product);

		assertThat(underTest.getProduct().getName(), is("testFruit"));
	}

	@Test
	public void lineItemShouldHaveQuantity() {
		Product product = new Product("testFruit");
		LineItem underTest = new LineItem(product, 1);

		assertThat(underTest.getQuantity(), is(1));
	}
}
