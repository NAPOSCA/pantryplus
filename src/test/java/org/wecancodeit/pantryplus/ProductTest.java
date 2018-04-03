package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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

	@Test
	public void shouldHaveEqualsReturnFalseIfDifferentClass() {
		assertThat(underTest.equals("Apple"), is(false));
	}
}
