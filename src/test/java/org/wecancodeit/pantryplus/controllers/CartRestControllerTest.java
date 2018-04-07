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

	private long lineItemId = 1L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		when(cart.addOneProduct(productId)).thenReturn(lineItem);
		when(cart.removeOneProduct(productId)).thenReturn(lineItem);
	}

	@Test
	public void shouldAddOneToQuantityInCountedLineItemInCart() {
		cartController.tellCartToAddOneProduct(productId, cartId);
		verify(cart).addOneProduct(productId);
	}
	
	@Test
	public void shouldRemoveOneOfQuantityInCountedLineItemInCart() {
		cartController.tellCartToRemoveOneProduct(productId, cartId);
		verify(cart).removeOneProduct(productId);
	}
	
	@Test
	public void shouldReturnChangedLineItemWhenAdding() {
		LineItem actual = cartController.tellCartToAddOneProduct(productId, cartId);
		assertThat(actual, is(lineItem));
	}
	
	@Test
	public void shouldReturnChangedLineItemWhenRemoving() {
		LineItem actual = cartController.tellCartToRemoveOneProduct(productId, cartId);
		assertThat(actual, is(lineItem));
	}
	
	@Test
	public void shouldDeleteLineItemFromCart() {
		cartController.tellCartToRemoveItem(lineItemId , cartId);
		verify(cart).removeItem(lineItemId);
	}

}
