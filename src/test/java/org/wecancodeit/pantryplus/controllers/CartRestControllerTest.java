package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;
import org.wecancodeit.pantryplus.user.User;
import org.wecancodeit.pantryplus.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRestControllerTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private UserRepository userRepo;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private LineItemRepository lineItemRepo;

	@Resource
	private ProductRepository productRepo;

	private CartRestController underTest;

	private Product product;
	private long productId;

	private Cart cart;
	private long cartId;

	private User user;

	@Before
	public void setup() {
		product = new Product("product", null);
		product = productRepo.save(product);
		productId = product.getId();
		cart = new Cart(user);
		cart = cartRepo.save(cart);
		cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
	}

	@Ignore
	@Test
	public void shouldReceivePostRequestOnCartAndTellCartToCreateCountedLineItem() {
		boolean dichotomous = false;
		CountedLineItem check = new CountedLineItem(cart, product, 1);
		Cart actual = underTest.receivePostOnCart(cartId, productId, dichotomous);
		assertThat(actual, is(check));
	}

	@Ignore
	@Test
	public void shouldAddCountedProductToCart() {
		CountedLineItem check = new CountedLineItem(cart, product, 1);
		Cart actual = underTest.tellLineItemRepoToSaveCountedLineItemBy(cartId, productId);
		assertThat(actual, is(check));
	}

	@Ignore
	@Test
	public void shouldNotDecreaseQuantityWhileIncreasingQuantity() {
		boolean increase = true;
		underTest.receivePatchRequestOnProductInCart(cartId, productId, increase);
		verify(cart, never()).decreaseProductByOne(productId);
	}
}
