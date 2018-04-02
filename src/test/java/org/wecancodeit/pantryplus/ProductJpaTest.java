package org.wecancodeit.pantryplus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

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
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category underTest = new Category("Fruit");
		underTest = categoryRepo.save(underTest);
		long categoryId = underTest.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		underTest = categoryRepo.findOne(categoryId);
		
		assertThat(underTest.getName(), is("Fruit"));		
	}
	
	@Test
	public void shouldSaveAndLoadProduct() {
		Category category = new Category("Fruit");
		Product underTest = new Product("pineapple", category);
		category = categoryRepo.save(category);
		underTest = productRepo.save(underTest);
		long productId = underTest.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		underTest = productRepo.findOne(productId);
		
		assertThat(underTest.getName(), is("pineapple"));		
	}
	
	@Test
	public void shouldSaveManyProductsToOneCategory() {
		Category category = new Category("fruit");
		Product underTest1 = new Product("pineapple", category);
		Product underTest2 = new Product("apple", category);
		category = categoryRepo.save(category);
		underTest1 = productRepo.save(underTest1);
		underTest2 = productRepo.save(underTest2);
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		category = categoryRepo.findOne(categoryId);
		
		assertThat(category.getProducts(), containsInAnyOrder(underTest1, underTest2));
		
	}
	
	

}
