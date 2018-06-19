package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;

public class AdministrationControllerTest {
	
	@Resource
	private CategoryRepository categoryRepo;

	@Test
	public void shouldCreateCategoryWhenControllerReceivesAPostRequestOnCategories() {
		AdministrationController underTest = new AdministrationController();
		String categoryName = "coupons";
		underTest.receiveAPostRequestOnCategories(categoryName);
		Category category = categoryRepo.findOne(1L);
		assertThat(category.getName(), is(categoryName));
	}
}
