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
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

public class CartRestControllerMockTest {

	@InjectMocks
	private CartRestController controller;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private ProductRepository productRepo;

	@Mock
	private LineItemRepository lineItemRepo;

	@Mock
	private Cart cart;
	long cartId = 1L;

	@Mock
	private CountedLineItem countedLineItem;
	private long countedLineItemId = 1L;

	@Mock
	private LineItem lineItem;
	private long lineItemId = 2L;

	@Mock
	private Product product;
	private long productId = 1L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		when(cart.increaseProductByOne(productId)).thenReturn(countedLineItem);
		when(cart.decreaseProductByOne(productId)).thenReturn(countedLineItem);
		when(lineItemRepo.findOne(countedLineItemId)).thenReturn(countedLineItem);
		when(lineItemRepo.findOne(lineItemId)).thenReturn(lineItem);
		when(lineItem.getId()).thenReturn(lineItemId);
		when(lineItemRepo.save(countedLineItem)).thenReturn(countedLineItem);
	}

	@Test
	public void shouldAddOneToQuantityInCountedLineItemInCart() {
		when(cart.has(productId)).thenReturn(true);
		controller.tellCartToIncreaseProductQuantityByOne(cartId, productId);
		verify(cart).increaseProductByOne(productId);
	}

	@Test
	public void shouldRemoveOneOfQuantityInCountedLineItemInCart() {
		when(cart.has(productId)).thenReturn(true);
		controller.tellCartToDecreaseProductQuantityByOne(cartId, productId);
		verify(cart).decreaseProductByOne(productId);
	}

	@Test
	public void shouldReturnChangedLineItemWhenAdding() {
		when(cart.has(productId)).thenReturn(true);
		LineItem actual = controller.tellCartToIncreaseProductQuantityByOne(cartId, productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReturnChangedLineItemWhenRemoving() {
		when(cart.has(productId)).thenReturn(true);
		LineItem actual = controller.tellCartToDecreaseProductQuantityByOne(cartId, productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldDeleteLineItemFromCart() {
		controller.tellCartToRemoveItem(cartId, productId);
		verify(cart).popItemByProductId(productId);
	}

	@Test
	public void shouldReturnCartAfterTellingItToDeleteAItem() {
		Cart actual = controller.tellCartToRemoveItem(cartId, productId);
		assertThat(actual, is(cart));
	}

	@Test
	public void shouldAddProductToCart() {
		LineItem check = new LineItem(cart, product);
		LineItem actual = controller.tellLineItemRepoToSaveDichotomousLineItemBy(cartId, productId);
		assertThat(actual, is(check));
	}

	@Test
	public void shouldReceivePostRequestOnCartAndTellCartToCreateDichotomousLineItem() {
		boolean dichotomous = true;
		LineItem check = new LineItem(cart, product);
		LineItem actual = controller.receivePostOnCart(cartId, productId, dichotomous);
		assertThat(actual, is(check));
	}

	@Test
	public void shouldReceivePutRequestOnProductInCartAndSetQuantity() {
		int quantity = 5;
		when(cart.updateQuantityOfProduct(productId, quantity)).thenReturn(countedLineItem);
		when(cart.has(productId)).thenReturn(true);
		CountedLineItem actual = controller.receivePutRequestOnProductInCart(cartId, productId, quantity);
		verify(cart).updateQuantityOfProduct(productId, quantity);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReceivePatchRequestOnProductInCartAndIncreaseQuantity() {
		when(cart.has(productId)).thenReturn(true);
		CountedLineItem actual = controller.receivePatchRequestOnProductInCart(cartId, productId, true);
		verify(cart).increaseProductByOne(productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReceivePatchRequestOnProductInCartAndDecreaseQuantity() {
		when(cart.has(productId)).thenReturn(true);
		CountedLineItem actual = controller.receivePatchRequestOnProductInCart(cartId, productId, false);
		verify(cart).decreaseProductByOne(productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldNotIncreaseQuantityWhileDecreasingQuantity() {
		boolean increase = false;
		controller.receivePatchRequestOnProductInCart(cartId, productId, increase);
		verify(cart, never()).increaseProductByOne(productId);
	}

	@Test
	public void shouldRemoveItemWhenReceivingDeleteRequestOnProductInCart() {
		controller.receiveDeleteRequestOnProductInCart(cartId, productId);
		verify(cart).popItemByProductId(productId);
	}

	@Test
	public void shouldRemoveAllItemsWhenReceivingDeleteRequestOnProductCollectionInCart() {
		controller.receiveDeleteRequestOnProductsInCart(cartId);
		verify(cart).removeAllItems();
	}

	@Test
	public void shouldRemoveCartWhenReceivingDeleteRequestOnCart() {
		controller.receiveDeleteRequestOnCart(cartId);
		verify(cartRepo).delete(cartId);
	}

}
