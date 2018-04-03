package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CartTest {
	Cart testCart = new Cart();
	Product testProduct = new Product("testProduct");
	Product testProduct2 = new Product("testProduct2");

	@Test
	public void shouldAddProduct() {
		testCart.addProduct(testProduct);
		assertThat(testCart.getProducts(), contains(testProduct));
	}

	@Test
	public void shouldDeleteProduct() {
		testCart.addProduct(testProduct);
		testCart.addProduct(testProduct2);
		testCart.deleteProduct(testProduct2);

		assertThat(testCart.getProducts(), containsInAnyOrder(testProduct));
		assertThat(testCart.getProducts(), not(containsInAnyOrder(testProduct2)));
	}
}
