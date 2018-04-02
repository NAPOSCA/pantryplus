package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProductTest {

	@Test
	public void shouldHaveName() {
		Product underTest = new Product("Apple", null);

		assertThat(underTest.getName(), is("Apple"));
	}
	
	@Test
	public void shouldHaveEqualsReturnFalseIfNull() {
		Product underTest = new Product("Apple", null);
		
		assertThat(underTest.equals(null), is(false));

	}
	
	@Test
	public void shouldHaveEqualsReturnFalseIfDifferentClass() {
		Product underTest = new Product("Apple", null);
		
		assertThat(underTest.equals("Apple"), is(false));

	}
}
