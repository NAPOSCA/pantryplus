package org.wecancodeit.pantryplus.cart;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

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

	private Cart cart;
	private Product product;
	private Product product2;
	private LineItem lineItem;
	private LineItem lineItem2;
	private CountedLineItem countedLine;
	private CountedLineItem countedLine2;

	@Before
	public void setUp() {
		cart = new Cart();
		product = new Product("grapefruit", null);
		product2 = new Product("apple", null);
		lineItem = new LineItem(cart, product);
		lineItem2 = new LineItem(cart, product2);
		countedLine = new CountedLineItem(cart, product, 1);
		countedLine2 = new CountedLineItem(cart, product2, 2);
	}

	@Test
	public void shouldSaveAndLoadCart() {
		cart = cartRepo.save(cart);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getId(), is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadLineItem() {
		cart = cartRepo.save(cart);
		product = productRepo.save(product);
		lineItem = lineItemRepo.save(lineItem);
		long lineItemId = lineItem.getId();
		entityManager.flush();
		entityManager.clear();
		lineItem = lineItemRepo.findOne(lineItemId);
		assertThat(lineItem.getProduct(), is(product));
	}

	@Test
	public void shouldSaveManyLineItemsToOneCart() {
		cart = cartRepo.save(cart);
		product = productRepo.save(product);
		product2 = productRepo.save(product2);
		lineItem = lineItemRepo.save(lineItem);
		lineItem2 = lineItemRepo.save(lineItem2);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getLineItems(), containsInAnyOrder(lineItem, lineItem2));
	}

	@Test
	public void shouldSaveLineItemsToSameCart() {
		cart = cartRepo.save(cart);
		product = productRepo.save(product);
		product2 = productRepo.save(product2);
		lineItem = lineItemRepo.save(lineItem);
		lineItem2 = lineItemRepo.save(lineItem2);
		entityManager.flush();
		entityManager.clear();
		assertThat(lineItem.getCart(), is(lineItem2.getCart()));
	}

	@Test
	public void shouldCalculateSumOfAllLineItemQuantities() {
		cart = cartRepo.save(cart);
		product = productRepo.save(product);
		product2 = productRepo.save(product2);
		countedLine = lineItemRepo.save(countedLine);
		countedLine2 = lineItemRepo.save(countedLine2);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getCartQuantity(), is(3));
	}

}
