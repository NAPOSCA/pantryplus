package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;

@RestController
public class CartRestController {

	@Resource
	CartRepository cartRepo;

	public void increaseQuantityOfProductInCart(long productId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		cart.addProduct(productId);
	}

	public void decreaseQuantityOfProductInCart(long productId, long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		cart.removeProduct(productId);
	}

}
