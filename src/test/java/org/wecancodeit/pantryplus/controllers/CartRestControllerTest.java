package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.controllers.CartRestController;
import org.wecancodeit.pantryplus.product.ProductRepository;

public class CartRestControllerTest {

	@InjectMocks
	private CartRestController cartController;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private ProductRepository productRepo;

	@Mock
	private Cart cart;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldRetrieveCart() {
		long id = 34L;
		when(cartRepo.findOne(id)).thenReturn(cart);

		Cart result = cartRepo.findOne(id);

		assertThat(result, is(cart));
	}

	// @Test
	// public void shouldAddNewLineItemToCart() {
	// long cartId = 34L;
	// when(cartRepo.findOne(cartId)).thenReturn(cart);
	// long productId = 1L;
	// when(productRepo.findOne(productId)).thenReturn(product);
	//
	//
	// Cart result = cartRepo.findOne(cartId);
	//
	// assertThat(result, is(cart));
	// }

}
