package org.wecancodeit.pantryplus.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
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

	private Map<String, Object> cartModel;

	private MimeMessage message;
	
	@Mock
	Model springModel;

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
		doReturn(html).when(controllerSpy).processModelIntoHtml(cartModel);
		doNothing().when(controllerSpy).sendEmail(message);
		controllerSpy.sendEmail("subject", cartModel);
		verify(controllerSpy).sendEmail(message);
	}
	
	@Test
	public void shouldReturnEmailFailureView() {
		String actual = controller.emailFailure("error", springModel);
		assertThat(actual, is("email-failure"));
	}
	
	@Test
	public void shouldAttachTheErrorToTheModel() {
		String error = "Off by 1";
		controller.emailFailure(error, springModel);
		verify(springModel).addAttribute("error", error);
	}
	
	@Test
	public void shouldTellJavaMailSenderToSendAnEmail() {
		controller.sendEmail(message);
		verify(sender).send(message);
	}
	
	@Test
	public void shouldGetANewMimeMessageFromTheJavaMailSender() {
		when(sender.createMimeMessage()).thenReturn(message);
		MimeMessage actual = controller.createMimeMessage();
		assertThat(actual, is(message));
	}
}
