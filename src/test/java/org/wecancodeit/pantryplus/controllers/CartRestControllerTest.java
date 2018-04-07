package org.wecancodeit.pantryplus.controllers;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;

public class CartRestControllerTest {

	@InjectMocks
	private CartRestController cartController;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private Cart cart;
	long cartId = 1L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(cartRepo.findOne(cartId)).thenReturn(cart);
	}

	@Test
	public void shouldAddOneToQuantityInCountedLineItemInCart() {
		long productId = 1L;
		cartController.increaseQuantityOfProductInCart(productId, cartId);
		verify(cart).addProduct(productId);
	}
	
	@Test
	public void shouldRemoveOneOfQuantityInCountedLineItemInCart() {
		long productId = 1L;
		cartController.decreaseQuantityOfProductInCart(productId, cartId);
		verify(cart).removeProduct(productId);
	}

}
