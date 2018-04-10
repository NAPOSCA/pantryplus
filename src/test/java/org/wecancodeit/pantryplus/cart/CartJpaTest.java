package org.wecancodeit.pantryplus.cart;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;
import org.wecancodeit.pantryplus.user.User;
import org.wecancodeit.pantryplus.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartJpaTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private UserRepository userRepo;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private ProductRepository productRepo;

	@Resource
	private LineItemRepository lineItemRepo;

	private User user;
	private long userId;
	private Cart cart;
	private long cartId;
	private Cart anotherCart;
	private Product product;
	private long productId;
	private Product anotherProduct;
	private long anotherProductId;
	private LineItem lineItem;
	private LineItem anotherLineItem;
	private CountedLineItem countedLineItem;
	private CountedLineItem anotherCountedLineItem;

	@Before
	public void setUp() {
		user = new User(3, 1, false, "2018-04-09", "43201");
		cart = new Cart(user);
		anotherCart = new Cart(user);
		product = new Product("grapefruit", null);
		anotherProduct = new Product("apple", null);
		lineItem = new LineItem(cart, product);
		anotherLineItem = new LineItem(cart, anotherProduct);
		countedLineItem = new CountedLineItem(cart, product, 1);
		anotherCountedLineItem = new CountedLineItem(cart, anotherProduct, 2);

		user = userRepo.save(user);
		userId = user.getId();
		cart = cartRepo.save(cart);
		cartId = cart.getId();
		product = productRepo.save(product);
		productId = product.getId();
		anotherProduct = productRepo.save(anotherProduct);
		anotherProductId = anotherProduct.getId();
	}

	@Test
	public void shouldSaveAndLoadCart() {
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getId(), is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveManyCartsToOneUser() {
		anotherCart = cartRepo.save(anotherCart);
		entityManager.flush();
		entityManager.clear();
		user = userRepo.findOne(userId);
		assertThat(user.getCarts(), containsInAnyOrder(cart, anotherCart));
	}

	@Test
	public void shouldSaveAndLoadLineItem() {
		lineItem = lineItemRepo.save(lineItem);
		long lineItemId = lineItem.getId();
		entityManager.flush();
		entityManager.clear();
		lineItem = lineItemRepo.findOne(lineItemId);
		assertThat(lineItem.getProduct(), is(product));
	}

	@Test
	public void shouldSaveManyLineItemsToOneCart() {
		lineItem = lineItemRepo.save(lineItem);
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getLineItems(), containsInAnyOrder(lineItem, anotherLineItem));
	}

	@Test
	public void shouldSaveLineItemsToSameCart() {
		lineItem = lineItemRepo.save(lineItem);
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		entityManager.flush();
		entityManager.clear();
		assertThat(lineItem.getCart(), is(anotherLineItem.getCart()));
	}

	@Test
	public void shouldCalculateSumOfAllLineItemQuantities() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		anotherCountedLineItem = lineItemRepo.save(anotherCountedLineItem);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getCartQuantity(), is(3));
	}

	@Test
	public void shouldGetLineItemByProductId() {
		lineItem = lineItemRepo.save(lineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, is(lineItem));
	}

	@Test
	public void shouldGetCountedLineItemByProductId() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReturnNullIfLineItemWithProductIdIsNotFound() {
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, nullValue());
	}

	@Test
	public void shouldReturnLineItemQuantityByProductIdOne() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(countedLineItem.getQuantity()));
	}

	@Test
	public void shouldReturnLineItemQuantityByProductIdTwo() {
		long productId = anotherProduct.getId();
		anotherCountedLineItem = lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(anotherCountedLineItem.getQuantity()));
	}

	@Test
	public void shouldIncreaseProductQuantityByOne() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		int check = countedLineItem.getQuantity();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.increaseProductByOne(productId).getQuantity();
		assertThat(actual, is(check + 1));
	}

	@Test
	public void shouldDecreaseProductQuantityByOne() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		int check = countedLineItem.getQuantity();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.decreaseProductByOne(productId).getQuantity();
		assertThat(actual, is(check - 1));
	}

	@Test
	public void shouldUpdateQuantityOfProduct() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int check = 5;
		cart.updateQuantityOfProduct(productId, check);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(check));
	}

	@Test
	public void shouldNotFindLineItemIfRemoved() {
		lineItem = lineItemRepo.save(lineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.removeItemByProductId(productId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void shouldNotFindAnyLineItemsIfAllAreRemoved() {
		lineItem = lineItemRepo.save(lineItem);
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.removeAllItems();
		lineItem = cart.getLineItemByProductId(productId);
		anotherLineItem = cart.getLineItemByProductId(anotherProductId);
		assertThat(lineItem, is(nullValue()));
		assertThat(anotherLineItem, is(nullValue()));
	}
	
	@Test
	public void shouldHaveCartRemoveCountedLineItemsWhenTheirQuantityIsZero() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		CountedLineItem countedLineItem = cart.decreaseProductByOne(productId);
		cart = cartRepo.save(cart);
		lineItemRepo.save(countedLineItem);
		lineItemRepo.delete(countedLineItem);
		cart = cartRepo.findOne(cartId);
		assertThat(cart.has(productId), is(false));
	}

}
