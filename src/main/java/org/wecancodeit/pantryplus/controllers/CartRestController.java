package org.wecancodeit.pantryplus.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

@RestController
public class CartRestController {

	@Resource
	private EntityManager entityManager;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private ProductRepository productRepo;

	@Resource
	private LineItemRepository lineItemRepo;

	@Transactional
	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = POST)
	public Cart receivePostOnCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestParam boolean dichotomous) {

		if (dichotomous) {
			return tellLineItemRepoToSaveDichotomousLineItemBy(cartId, productId);
		}
		return tellLineItemRepoToSaveCountedLineItemBy(cartId, productId);
	}

	@Transactional
	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = PUT)
	public Cart receivePutRequestOnProductInCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestParam int quantity) {

		return tellCartToUpdateProductQuantity(cartId, productId, quantity);
	}

	@Transactional
	@RequestMapping(path = "/carts/{cartId}/items/{productId}", method = PATCH)
	public Cart receivePatchRequestOnProductInCart(@PathVariable long cartId, @PathVariable long productId,
			@RequestParam boolean increase) {

		if (increase) {
			return tellCartToIncreaseProductQuantityByOne(cartId, productId);
		}
		return tellCartToDecreaseProductQuantityByOne(cartId, productId);
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
		cartRepo.deleteById(cartId);
	}

	Cart tellCartToIncreaseProductQuantityByOne(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		if (cart.has(productId)) {
			CountedLineItem countedLineItem = cart.increaseProductByOne(productId);
			lineItemRepo.save(countedLineItem);
			entityManager.flush();
			entityManager.clear();
			return retrieveCartBy(cartId);
		}
		return tellLineItemRepoToSaveCountedLineItemBy(cartId, productId);
	}

	Cart tellCartToDecreaseProductQuantityByOne(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		if (cart.has(productId)) {
			CountedLineItem countedLineItem = cart.decreaseProductByOne(productId);
			countedLineItem = lineItemRepo.save(countedLineItem);
			entityManager.flush();
			entityManager.clear();
		}
		return retrieveCartBy(cartId);
	}

	Cart tellCartToRemoveItem(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		LineItem orphan = cart.popItemByProductId(productId);
		lineItemRepo.save(orphan);
		return cart;
	}

	Cart tellLineItemRepoToSaveDichotomousLineItemBy(long cartId, long productId) {
		Cart cart = retrieveCartBy(cartId);
		Product product = retrieveProductBy(productId);
		LineItem lineItem = new LineItem(cart, product);
		lineItemRepo.save(lineItem);
//		return lineItem;
		entityManager.flush();
		entityManager.clear();
		return retrieveCartBy(cartId);
	}

	Cart tellLineItemRepoToSaveCountedLineItemBy(long cartId, long productId) {
		return tellLineItemRepoToSaveCountedLineItemBy(cartId, productId, 1);
	}

	public Cart tellLineItemRepoToSaveCountedLineItemBy(long cartId, long productId, int quantity) {
		Cart cart = retrieveCartBy(cartId);
		Product product = retrieveProductBy(productId);
		CountedLineItem countedLineItem = new CountedLineItem(cart, product, quantity);
		countedLineItem = lineItemRepo.save(countedLineItem);
//		long id = countedLineItem.getId();
		entityManager.flush();
		entityManager.clear();
		cart = retrieveCartBy(cartId);
		cart.refreshStats();
		return cartRepo.save(cart);
//		entityManager.flush();
//		entityManager.clear();
//		countedLineItem = (CountedLineItem) lineItemRepo.findOne(id);
//		return countedLineItem;
	}

	private Cart tellCartToUpdateProductQuantity(long cartId, long productId, int quantity) {
		Cart cart = retrieveCartBy(cartId);
		if (cart.has(productId)) {
			CountedLineItem countedLineItem = cart.updateQuantityOfProduct(productId, quantity);
			lineItemRepo.save(countedLineItem);
			entityManager.flush();
			entityManager.clear();
			return retrieveCartBy(cartId);
		}
		return tellLineItemRepoToSaveCountedLineItemBy(cartId, productId, quantity);
	}

	private void tellCartToRemoveAllItems(long cartId) {
		Cart cart = retrieveCartBy(cartId);
		cart.removeAllItems();
	}

	private Cart retrieveCartBy(long cartId) {
		return cartRepo.findById(cartId).get();
	}

	private Product retrieveProductBy(long productId) {
		Product product = productRepo.findById(productId).get();
		return product;
	}

}
