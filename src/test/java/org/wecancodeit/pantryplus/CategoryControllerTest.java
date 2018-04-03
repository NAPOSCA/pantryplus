package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryControllerTest {

	@InjectMocks
	CategoryController categoryController;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldFindAllCategories() {
		when(categoryRepo.findAll()).thenReturn(Collections.singleton(category));
		
		Iterable<Category> results = categoryController.findAllCategories();
		
		assertThat(results, contains(any(Category.class)));
	}
	
}
