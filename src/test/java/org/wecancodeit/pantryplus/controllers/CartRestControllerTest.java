package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
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
	private CountedLineItem countedLineItem;

	@Mock
	private LineItem lineItem;

	private long productId = 1L;

	private long anotherProductId = 2L;

	private long countedLineItemId = 1L;

	private long lineItemId = 2L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		when(cart.addOneProduct(productId)).thenReturn(countedLineItem);
		when(cart.removeOneProduct(productId)).thenReturn(countedLineItem);
		when(cart.addItem(anotherProductId)).thenReturn(lineItem);
		when(cart.addCountedItem(productId)).thenReturn(countedLineItem);
	}

	@Test
	public void shouldAddOneToQuantityInCountedLineItemInCart() {
		cartController.tellCartToAddOneProduct(cartId, productId);
		verify(cart).addOneProduct(productId);
	}

	@Test
	public void shouldRemoveOneOfQuantityInCountedLineItemInCart() {
		cartController.tellCartToRemoveOneProduct(cartId, productId);
		verify(cart).removeOneProduct(productId);
	}

	@Test
	public void shouldReturnChangedLineItemWhenAdding() {
		LineItem actual = cartController.tellCartToAddOneProduct(cartId, productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReturnChangedLineItemWhenRemoving() {
		LineItem actual = cartController.tellCartToRemoveOneProduct(cartId, productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldDeleteLineItemFromCart() {
		cartController.tellCartToRemoveItem(cartId, countedLineItemId);
		verify(cart).removeItem(countedLineItemId);
	}

	@Test
	public void shouldReturnCartAfterTellingItToDeleteAItem() {
		Cart actual = cartController.tellCartToRemoveItem(cartId, countedLineItemId);
		assertThat(actual, is(cart));
	}

	@Test
	public void shouldAddProductToCart() {
		cartController.tellCartToAddDichotomousProduct(cartId, productId);
		verify(cart).addItem(productId);
	}

	@Test
	public void shouldAddCountedProductToCart() {
		cartController.tellCartToAddCountedProduct(cartId, productId);
		verify(cart).addCountedItem(productId);
	}

	@Test
	public void shouldReceivePostRequestOnCartAndTellCartToCreateDichotomousLineItem() {
		boolean dichotomous = true;
		cartController.receivePostOnCart(cartId, productId, dichotomous);
		verify(cart).addItem(productId);
	}

	@Test
	public void shouldReceivePostRequestOnCartAndTellCartToCreateCountedLineItem() {
		boolean dichotomous = false;
		cartController.receivePostOnCart(cartId, productId, dichotomous);
		verify(cart).addCountedItem(productId);
	}

	@Test
	public void shouldReceivePostRequestOnCartAndNotCreateCountedItemWhenCreatingDichotomous() {
		boolean dichotomous = true;
		cartController.receivePostOnCart(cartId, productId, dichotomous);
		verify(cart, never()).addCountedItem(productId);
	}

	@Test
	public void shouldReceivePostRequestOnCartAndNotCreateDichotomousItemWhenCreatingCounted() {
		boolean dichotomous = false;
		cartController.receivePostOnCart(cartId, productId, dichotomous);
		verify(cart, never()).addItem(productId);
	}

	@Test
	public void shouldReturnLineItemWhenReceivingAPostRequest() {
		boolean dichotomous = true;
		LineItem actual = cartController.receivePostOnCart(cartId, anotherProductId, dichotomous);
		assertThat(actual, is(lineItem));
	}

	@Test
	public void shouldReturnCountedLineItemWhenReceivingAPostRequest() {
		boolean dichotomous = false;
		LineItem actual = cartController.receivePostOnCart(cartId, productId, dichotomous);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReceivePutRequestOnProductInCartAndSetQuantity() {
		int quantity = 5;
		cartController.receivePutRequestOnProductInCart(cartId, productId, quantity);
		verify(cart).updateQuantityOfProduct(productId, quantity);
	}

}
