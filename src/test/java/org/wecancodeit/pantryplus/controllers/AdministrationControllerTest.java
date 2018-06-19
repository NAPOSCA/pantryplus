package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AdministrationControllerTest {
	
	private AdministrationController underTest;

	@Test
	public void shouldHaveAdminControllerDisplayAdminView() {
		String templateName = underTest.displayAdminView();
		assertThat(templateName, is("admin"));
	}
}
