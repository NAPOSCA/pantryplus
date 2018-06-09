package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PantryControllerTest {

	@Test
	public void shouldProcessUserFormWithValidZipCode43203() {
		PantryController underTest = new PantryController();
//		when(cart.getId()).thenReturn(cartId);
		String actual = underTest.userFormProcessing(null, null, 0, 0, false, "2018-04-04", "43203", null, null);
//		assertThat(actual, is("redirect:/shopping?cartId=" + cartId));
	}
}
