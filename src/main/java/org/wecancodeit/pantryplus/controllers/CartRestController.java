package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;

@RestController
public class CartRestController {

	@Resource
	CartRepository cartRepo;

	public CountedLineItem tellCartToAddOneProduct(long cartId, long productId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.addOneProduct(productId);
	}

	protected CountedLineItem tellCartToRemoveOneProduct(long cartId, long productId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.removeOneProduct(productId);
	}

	protected Cart tellCartToRemoveItem(long cartId, long lineItemId) {
		Cart cart = cartRepo.findOne(cartId);
		cart.removeItem(lineItemId);
		return cart;
	}

	public LineItem tellCartToAddDichotomousProduct(long cartId, long productId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.addItem(productId);
	}

	public LineItem tellCartToAddCountedProduct(long cartId, long productId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.addCountedItem(productId);
	}

	public LineItem receivePostOnCart(long cartId, long productId, boolean dichotomous) {
		if (dichotomous) {
			return tellCartToAddDichotomousProduct(cartId, productId);
		} else {
			return tellCartToAddCountedProduct(cartId, productId);
		}
	}

	public void receivePutRequestOnProductInCart(long cartId, long productId, int quantity) {
		tellCartToUpdateProductQuantity(cartId, productId, quantity);
	}

	private void tellCartToUpdateProductQuantity(long cartId, long productId, int quantity) {
		Cart cart = cartRepo.findOne(cartId);
		cart.updateQuantityOfProduct(productId, quantity);
	}

}
