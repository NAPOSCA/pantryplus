package org.wecancodeit.pantryplus.controllers;

import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.wecancodeit.pantryplus.cart.Cart;

public class EmailControllerMockTest {

	@InjectMocks
	private EmailController controller;

	@Mock
	private JavaMailSender sender;

//	@Mock
//	private CartRepository cartRepo;

	@Mock
	private Cart cart;
	private long cartId = 1L;
	private Map<String, Object> model = new HashMap<>();

	@Mock
	private SpringTemplateEngine templateEngine;

	private MimeMessage message;

	@Mock
	private MimeMessageHelper helper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
//		when(cartRepo.findOne(cartId)).thenReturn(cart);
//		when(cart.toModel()).thenReturn(model);
//		when(sender.createMimeMessage()).thenReturn(message);
	}

//	@Test
//	public void shouldSendEmail() throws Exception {
//		controller.sendEmail("", cart.toModel());
//		verify(sender).send(message);
//	}

//	@Test
//	public void shouldCreateHtmlEmailFromTemplate() throws Exception {
//		controller.sendEmail("", cart.toModel());
//		Context context = new Context();
//		context.setVariables(cart.toModel());
//		System.out.println(templateEngine);
//		verify(templateEngine).process("order", context);
//	}

	@Test
	public void shouldSetToOfMimeMessageHelper() throws MessagingException {
		String address = "bsfppantryplus@gmail.com";
		controller.setRecipient(helper);
		verify(helper).setTo(address);
	}
}
