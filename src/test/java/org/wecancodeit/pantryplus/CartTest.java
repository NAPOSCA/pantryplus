package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CartTest {
	Cart testCart = new Cart();
	Product testProduct = new Product("testProduct", null);
	Product testProduct2 = new Product("testProduct2", null);

	@Test
	public void shouldAddProduct() {
		testCart.addProduct(testProduct);
		assertThat(testCart.getProducts(), contains(testProduct));
	}

	@Test
	public void shouldDeleteProduct() {
		testCart.addProduct(testProduct);
		testCart.deleteProduct(testProduct);

		assertThat(testCart.getProducts(), not(contains(testProduct)));
	}
}
