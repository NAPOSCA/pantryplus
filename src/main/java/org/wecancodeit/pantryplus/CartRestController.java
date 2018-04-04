package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestController {

	@Resource
	CartRepository cartRepo;

	@Resource
	ProductRepository productRepo;

	@Resource
	LineItemRepository lineItemRepo;

	@RequestMapping(path = "/cart/items", method = RequestMethod.POST)
	public LineItem addItemToCart(long productId, int quantity) {
		Cart cart = cartRepo.findOne(1L);
		Product product = productRepo.findOne(productId);
		LineItem newLineItem = new LineItem(cart, product, quantity);
		newLineItem = lineItemRepo.save(newLineItem);
		return newLineItem;
	}

	@RequestMapping(path = "/cart/items", method = RequestMethod.DELETE)
	public Cart deleteItemFromCart(long lineItemId) {
		lineItemRepo.delete(lineItemId);
		return cartRepo.findOne(1L);
	}

}
