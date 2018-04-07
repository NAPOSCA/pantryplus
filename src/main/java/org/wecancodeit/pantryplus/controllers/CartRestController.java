package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.LineItem;

@RestController
public class CartRestController {

	@Resource
	CartRepository cartRepo;

	public LineItem increaseQuantityOfProductInCart(long productId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.addOneProduct(productId);
	}

	public LineItem decreaseQuantityOfProductInCart(long productId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		return cart.removeOneProduct(productId);
	}

	public void deleteItemFromCart(long lineItemId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		cart.removeItem(lineItemId);
	}

}
