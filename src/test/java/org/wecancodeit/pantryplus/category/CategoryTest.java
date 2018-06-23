package org.wecancodeit.pantryplus.category;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CategoryTest {

	@Test
	public void shouldGetNameFoo() {
		Category underTest = new Category("foo");
		String name = underTest.getName();
		assertThat(name, is("foo"));
	}
	
	@Test
	public void shouldGetNameBar() {
		Category underTest = new Category("bar");
		String name = underTest.getName();
		assertThat(name, is("bar"));
	}
	
}
