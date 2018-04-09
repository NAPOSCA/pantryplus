package org.wecancodeit.pantryplus.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

@RestController
public class CartRestController {

	@Resource
	private CartRepository cartRepo;
	
	@Resource
	private ProductRepository productRepo;

	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = POST)
	public LineItem receivePostOnCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestParam boolean dichotomous) {
		if (dichotomous) {
			return tellCartToAddDichotomousProduct(cartId, productId);
		} else {
			return tellCartToAddCountedProduct(cartId, productId);
		}
	}

	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = PUT)
	public void receivePutRequestOnProductInCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestParam int quantity) {
		tellCartToUpdateProductQuantity(cartId, productId, quantity);
	}

	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = PATCH)
	public void receivePatchRequestOnProductInCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestParam boolean increase) {
		if (increase) {
			tellCartToIncreaseProductQuantityByOne(cartId, productId);
		} else {
			tellCartToDecreaseProductQuantityByOne(cartId, productId);
		}
	}

	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = DELETE)
	public void receiveDeleteRequestOnProductInCart(@PathVariable long cartId, @PathVariable long productId) {
		tellCartToRemoveItem(cartId, productId);
	}

	@RequestMapping(path = "/carts/{cartId}/items", method = DELETE)
	public void receiveDeleteRequestOnProductsInCart(@PathVariable long cartId) {
		tellCartToRemoveAllItems(cartId);
	}

	@RequestMapping(path = "/carts/{cartId}", method = DELETE)
	public void receiveDeleteRequestOnCart(@PathVariable long cartId) {
		cartRepo.delete(cartId);
	}

	CountedLineItem tellCartToIncreaseProductQuantityByOne(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		return cart.increaseProductByOne(productId);
	}

	CountedLineItem tellCartToDecreaseProductQuantityByOne(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		return cart.decreaseProductByOne(productId);
	}

	Cart tellCartToRemoveItem(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		cart.removeItemByProductId(productId);
		return cart;
	}

	LineItem tellCartToAddDichotomousProduct(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		Product product = retrieveProductBy(productId);
		return cart.addItem(product);
	}

	LineItem tellCartToAddCountedProduct(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		Product product = retrieveProductBy(productId);
		return cart.addCountedItem(productId, product);
	}

	private void tellCartToUpdateProductQuantity(long cartId, long productId, int quantity) {
		Cart cart = retrieveCartBy(cartId);
		cart.updateQuantityOfProduct(productId, quantity);
	}

	private void tellCartToRemoveAllItems(long cartId) {
		Cart cart = retrieveCartBy(cartId);
		cart.removeAllItems();
	}

	private Cart retrieveCartBy(long cartId) {
		return cartRepo.findOne(cartId);
	}

	private Product retrieveProductBy(long productId) {
		Product product = productRepo.findOne(productId);
		return product;
	}

}
