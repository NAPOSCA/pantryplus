package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public LineItem addItemToCart(@RequestParam(value = "productId") long id,
			@RequestParam(value = "quantity") int quantity) {
		Cart cart = cartRepo.findOne(1L);
		Product product = productRepo.findOne(id);
		LineItem newLineItem = new LineItem(cart, product, quantity);
		newLineItem = lineItemRepo.save(newLineItem);
		return newLineItem;
	}

	@RequestMapping(path = "/cart/items", method = RequestMethod.PUT)
	public LineItem updateLineItemQuantity(@RequestParam(value = "lineItemId") long id,
			@RequestParam(value = "quantity") int quantity) {
		LineItem lineItem = lineItemRepo.findOne(id);
		lineItem.updateQuantity(quantity);
		return lineItemRepo.save(lineItem);
	}

	@RequestMapping(path = "/cart/items", method = RequestMethod.DELETE)
	public Cart deleteItemFromCart(@RequestParam(value = "lineItemId") long lineItemId) {
		lineItemRepo.delete(lineItemId);
		return cartRepo.findOne(1L);
	}

}
