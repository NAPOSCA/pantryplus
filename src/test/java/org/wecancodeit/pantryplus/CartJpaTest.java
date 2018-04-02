package org.wecancodeit.pantryplus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartJpaTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private ProductRepository productRepo;

	@Resource
	private LineItemRepository lineItemRepo;

	@Test
	public void shouldSaveAndLoadCart() {
		Cart underTest = new Cart();
		underTest = cartRepo.save(underTest);
		long cartId = underTest.getId();

		entityManager.flush();
		entityManager.clear();

		underTest = cartRepo.findOne(cartId);

		assertThat(underTest.getId(), is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadLineItem() {
		Cart cart = new Cart();
		Product product = new Product("grapefruit", null);
		LineItem underTest = new LineItem(cart, product, 1);
		cart = cartRepo.save(cart);
		product = productRepo.save(product);
		underTest = lineItemRepo.save(underTest);
		long lineItemId = underTest.getId();

		entityManager.flush();
		entityManager.clear();

		underTest = lineItemRepo.findOne(lineItemId);

		assertThat(underTest.getProduct(), is(product));
	}

}
