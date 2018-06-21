package org.wecancodeit.pantryplus.category;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CategoryTest {

	@Test
	public void shouldGetName() {
		Category underTest = new Category("testName");
		String actualName = underTest.getName();
		assertThat(actualName, is("testName"));
	}
}
