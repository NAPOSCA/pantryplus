package org.wecancodeit.pantryplus;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategoryTest {
	Category testCategory = new Category("testName");

	@Test
	public void shouldGetName() {
		String check = testCategory.getName();
		assertEquals(check, "testName");
	}
}
