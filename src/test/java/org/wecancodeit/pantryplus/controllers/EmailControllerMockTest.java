package org.wecancodeit.pantryplus.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

	private Map<String, Object> model;

	private MimeMessage message;

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
	
	@Test
	public void shouldSetSubjectOfMimeMessageHelper() throws MessagingException {
		String subject = "subject";
		controller.setSubject(subject, helper);
		verify(helper).setSubject(subject);
	}
	
	@Test
	public void shouldSetBodyOfMimeMessageHelper() throws MessagingException {
		String html = "<p>Hello World!</p>";
		controller.setBody(helper, html);
		verify(helper).setText(html, true);
	}
	
	@Test
	public void shouldHaveSendEmailCallOverloadedSendEmail() throws Exception {
		EmailController controllerSpy = new EmailController();
		controllerSpy = Mockito.spy(controllerSpy);
		doReturn(message).when(controllerSpy).createMimeMessage();
		doReturn(helper).when(controllerSpy).createMimeMessageHelper(message);
		String html = "<p>Hello World!</p>";
		doReturn(html).when(controllerSpy).processModelIntoHtml(model);
		doNothing().when(controllerSpy).sendEmail(message);
		controllerSpy.sendEmail("subject", model);
		verify(controllerSpy).sendEmail(message);
	}
}
