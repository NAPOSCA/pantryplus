package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;

@RestController
public class CartRestController {

	@Resource
	CartRepository cartRepo;

	public CountedLineItem tellCartToAddOneProduct(long productId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.addOneProduct(productId);
	}

	public CountedLineItem tellCartToRemoveOneProduct(long productId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.removeOneProduct(productId);
	}

	public Cart tellCartToRemoveItem(long lineItemId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		cart.removeItem(lineItemId);
		return cart;
	}

}
