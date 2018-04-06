package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class PantryControllerTest {
	
	@InjectMocks
	private PantryController underTest;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldHaveDisplayUserFormReturnUserForm() {
		String templateName = underTest.displayUserForm(model);
		assertThat(templateName, is("user-form"));
	}
	
	@Test
	public void shouldHaveDisplayShoppingReturnShopping() {
		String templateName = underTest.displayShopping(model);
		assertThat(templateName, is("shopping"));
	}
}
