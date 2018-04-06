package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.Cart;
import org.wecancodeit.pantryplus.CartRepository;
import org.wecancodeit.pantryplus.CountedLineItem;
import org.wecancodeit.pantryplus.LineItem;
import org.wecancodeit.pantryplus.LineItemRepository;
import org.wecancodeit.pantryplus.Product;
import org.wecancodeit.pantryplus.ProductRepository;

@RestController
public class CartRestController {

	@Resource
	CartRepository cartRepo;

	@Resource
	ProductRepository productRepo;

	@Resource
	LineItemRepository lineItemRepo;

	@RequestMapping(path = "/cart/items", method = RequestMethod.POST)
	public LineItem increaseQuantityOfProductInCart(@RequestParam(value = "productId") long id,
			@RequestParam(value = "quantity") int quantity) {
		Cart cart = cartRepo.findOne(1L);
		LineItem lineItem = cart.getLineItemByProductId(id);
		if (lineItem == null) {
			Product product = productRepo.findOne(id);
			lineItem = new CountedLineItem(cart, product, 0);
		}
		((CountedLineItem) lineItem).addQuantity(quantity);
		lineItem = lineItemRepo.save(lineItem);

		return lineItem;
	}

	@RequestMapping(path = "/cart/items", method = RequestMethod.PUT)
	public LineItem updateQuantityOfProductInCart(@RequestParam(value = "productId") long id,
			@RequestParam(value = "quantity") int quantity) {
		Cart cart = cartRepo.findOne(1L);
		LineItem lineItem = cart.getLineItemByProductId(id);
		if (lineItem == null) {
			Product product = productRepo.findOne(id);
			lineItem = new CountedLineItem(cart, product, quantity);
		} else {
			((CountedLineItem) lineItem).setQuantity(quantity);
		}
		lineItem = lineItemRepo.save(lineItem);

		return lineItem;
	}

	@RequestMapping(path = "/cart/items", method = RequestMethod.DELETE)
	public Cart deleteItemFromCart(@RequestParam(value = "lineItemId") long lineItemId) {
		lineItemRepo.delete(lineItemId);
		return cartRepo.findOne(1L);
	}

}
