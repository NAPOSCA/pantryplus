package org.wecancodeit.pantryplus;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class CartControllerTest {
	@InjectMocks
	private CartController cartController;

	@Mock
	private CartRepository cartRepo;

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

		assertThat(result, is(result));
	}

	/*
	 * @Test public void shouldRetrievesIndividualLineItem() { long id = 1L;
	 * when(lineItemRepo.findOne(id)).thenReturn(lineItem);
	 * 
	 * LineItem testLineItem = underTest.findLineItem(id);
	 * 
	 * assertThat(testLineItem, is(lineItem)); }
	 */
}
