package org.wecancodeit.pantryplus.controllers;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;

public class AdministrationControllerMockTest {
	
	@InjectMocks
	private AdministrationController underTest;
	
	@Mock
	private Model model;

	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@Mock
	private CategoryRepository categoryRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldHaveAdminControllerDisplayAdminView() {
		String templateName = underTest.displayAdminView(model);
		assertThat(templateName, is("admin"));
	}
	
	@Test
	public void shouldAttachCategoriesToModel() {
		Iterable<Category> categories = asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(categories);
		underTest.displayAdminView(model);
		verify(model).addAttribute("categories", categories);
	}
}
