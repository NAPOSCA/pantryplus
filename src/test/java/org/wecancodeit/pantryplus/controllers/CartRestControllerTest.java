package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.LineItem;

public class CartRestControllerTest {

	@InjectMocks
	private CartRestController cartController;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private Cart cart;
	long cartId = 1L;
	
	@Mock
	private LineItem lineItem;

	private long productId = 1L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		when(cart.addProduct(productId)).thenReturn(lineItem);
		when(cart.removeProduct(productId)).thenReturn(lineItem);
	}

	@Test
	public void shouldAddOneToQuantityInCountedLineItemInCart() {
		cartController.increaseQuantityOfProductInCart(productId, cartId);
		verify(cart).addProduct(productId);
	}
	
	@Test
	public void shouldRemoveOneOfQuantityInCountedLineItemInCart() {
		cartController.decreaseQuantityOfProductInCart(productId, cartId);
		verify(cart).removeProduct(productId);
	}
	
	@Test
	public void shouldReturnChangedLineItemWhenAdding() {
		LineItem actual = cartController.increaseQuantityOfProductInCart(productId, cartId);
		assertThat(actual, is(lineItem));
	}
	
	@Test
	public void shouldReturnChangedLineItemWhenRemoving() {
		LineItem actual = cartController.decreaseQuantityOfProductInCart(productId, cartId);
		assertThat(actual, is(lineItem));
	}

}
