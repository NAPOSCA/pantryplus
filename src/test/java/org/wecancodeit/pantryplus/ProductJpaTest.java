package org.wecancodeit.pantryplus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductJpaTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ProductRepository productRepo;

	private Category category;
	private Product product1;
	private Product product2;

	@Before
	public void setUp() {
		category = new Category("fruit");
		product1 = new Product("pineapple", category);
		product2 = new Product("apple", category);
		category = categoryRepo.save(category);
		product1 = productRepo.save(product1);
		product2 = productRepo.save(product2);
	}

	@Test
	public void shouldSaveAndLoadCategory() {
		long categoryId = category.getId();
		entityManager.flush();
		entityManager.clear();
		category = categoryRepo.findOne(categoryId);
		assertThat(category.getName(), is("fruit"));
	}

	@Test
	public void shouldSaveAndLoadProduct() {
		long productId = product1.getId();
		entityManager.flush();
		entityManager.clear();
		product1 = productRepo.findOne(productId);
		assertThat(product1.getName(), is("pineapple"));
	}

	@Test
	public void shouldSaveManyProductsToOneCategory() {
		long categoryId = category.getId();
		entityManager.flush();
		entityManager.clear();
		category = categoryRepo.findOne(categoryId);
		assertThat(category.getProducts(), containsInAnyOrder(product1, product2));
	}

}
