package org.wecancodeit.pantryplus.controllers;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.product.ProductRepository;

public class CartRestControllerTest {

	@InjectMocks
	private CartRestController cartController;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private ProductRepository productRepo;

	@Mock
	private Cart cart;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

}
