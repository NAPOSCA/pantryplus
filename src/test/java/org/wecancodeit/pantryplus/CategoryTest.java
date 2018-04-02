package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class CategoryTest {
	Product testProduct = new Product("testProduct");
	Category testCategory = new Category("testName", testProduct);

	@Test
	public void shouldGetName() {
		String checkName = testCategory.getName();
		assertEquals(checkName, "testName");
	}

	@Test
	public void shouldHaveProductInCategory() {
		Collection<Product> checkProduct = testCategory.getProducts();
		assertThat(testCategory.getProducts(), is(checkProduct));
	}
}
