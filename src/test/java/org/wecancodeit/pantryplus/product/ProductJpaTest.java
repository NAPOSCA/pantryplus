package org.wecancodeit.pantryplus.product;

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
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

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
	private Product product;
	private Product anotherProduct;

	private long categoryId;

	private long productId;

	private long anotherProductId;

	@Before
	public void setUp() {
		category = new Category("fruit");
		product = new Product("pineapple", category);
		anotherProduct = new Product("apple", category);
		category = categoryRepo.save(category);
		categoryId = category.getId();
		product = productRepo.save(product);
		productId = product.getId();
		anotherProduct = productRepo.save(anotherProduct);
		anotherProductId = anotherProduct.getId();
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void shouldSaveAndLoadCategory() {
		category = categoryRepo.findOne(categoryId);
		assertThat(category.getName(), is("fruit"));
	}

	@Test
	public void shouldSaveAndLoadProduct() {
		product = productRepo.findOne(productId);
		assertThat(product.getName(), is("pineapple"));
	}

	@Test
	public void shouldSaveManyProductsToOneCategory() {
		category = categoryRepo.findOne(categoryId);
		product = productRepo.findOne(productId);
		anotherProduct = productRepo.findOne(anotherProductId);
		assertThat(category.getProducts(), containsInAnyOrder(product, anotherProduct));
	}

}
