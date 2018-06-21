package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdministrationControllerTest {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TestEntityManager entityManager;

	@Ignore
	@Test
	public void shouldCreateCategoryWhenControllerReceivesAPostRequestOnCategories() {
		AdministrationController underTest = new AdministrationController();
		String categoryName = "coupons";
		underTest.receiveAPostRequestOnCategories(categoryName);
		entityManager.flush();
		entityManager.clear();
		Category category = categoryRepo.findById(1L).get();
		assertThat(category.getName(), is(categoryName));
	}
}
