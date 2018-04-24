package org.wecancodeit.pantryplus.controllers;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;

public class EmailControllerMockTest {

	@InjectMocks
	private EmailController controller;
	
	@Mock
	private JavaMailSender sender;
	
	@Mock
	private CartRepository cartRepo;
	
	@Mock
	private Cart cart;
	private long cartId = 1L;
	
	@Mock
	private SpringTemplateEngine templateEngine;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(cartRepo.findOne(cartId)).thenReturn(cart);
	}
}
