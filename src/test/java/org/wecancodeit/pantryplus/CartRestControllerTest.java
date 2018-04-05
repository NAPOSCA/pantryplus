package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

	@Test
	public void shouldRetrieveCart() {
		long id = 34L;
		when(cartRepo.findOne(id)).thenReturn(cart);

		Cart result = cartRepo.findOne(id);

		assertThat(result, is(cart));
	}

	// @Test
	// public void shouldAddNewLineItemToCart() {
	// long cartId = 34L;
	// when(cartRepo.findOne(cartId)).thenReturn(cart);
	// long productId = 1L;
	// when(productRepo.findOne(productId)).thenReturn(product);
	//
	//
	// Cart result = cartRepo.findOne(cartId);
	//
	// assertThat(result, is(cart));
	// }

}
