package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProductTest {

	@Test
	public void shouldHaveName() {
		Product underTest = new Product("Apple");

		assertThat(underTest.getName(), is("Apple"));
	}
}
