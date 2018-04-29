package org.wecancodeit.pantryplus.controllers;

import static org.mockito.Mockito.verify;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.spring4.SpringTemplateEngine;

public class EmailControllerMockTest {

	@InjectMocks
	private EmailController controller;

	@Mock
	private JavaMailSender sender;

	@Mock
	private SpringTemplateEngine templateEngine;

	@Mock
	private MimeMessageHelper helper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSetToOfMimeMessageHelper() throws MessagingException {
		String address = "bsfppantryplus@gmail.com";
		controller.setRecipient(helper);
		verify(helper).setTo(address);
	}
}
