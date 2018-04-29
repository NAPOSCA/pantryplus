package org.wecancodeit.pantryplus.controllers;

import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;

@Controller
public class EmailController {

	private static final String RECIPIENT = "bsfppantryplus@gmail.com";

	@Resource
	private JavaMailSender sender;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private SpringTemplateEngine templateEngine;

	@RequestMapping("/email")
	public String home(@RequestParam long cartId) {
		try {
			Cart cart = cartRepo.findOne(cartId);
			String name;
			String firstname = cart.getUser().getFirstName();
			String lastname = cart.getUser().getLastName();
			name = firstname + " " + lastname;
			String subject = name + "'s Order";
			Map<String, Object> message = cart.toModel();
			sendEmail(subject, message);
			return "redirect:/email-success.html";
		} catch (Exception exThrown) {
			return "redirect:/email-failure.html?error=" + exThrown;
		}
	}

	@RequestMapping("/email-failure")
	public String emailFailure(@RequestParam String error, Model model) {
		model.addAttribute("error", error);
		return "email-failure";
	}

	public void sendEmail(String subject, Map<String, Object> model) throws Exception {
		MimeMessage message = createMimeMessage();
		MimeMessageHelper helper = createMimeMessageHelper(message);

		Context context = createContext(model);
		String html = processTemplate(context);

		setRecipient(helper);
		setBody(helper, html);
		setSubject(subject, helper);

		sendEmail(message);
	}

	public void sendEmail(MimeMessage message) {
		sender.send(message);
	}

	public void setSubject(String subject, MimeMessageHelper helper) throws MessagingException {
		helper.setSubject(subject);
	}

	private void setBody(MimeMessageHelper helper, String html) throws MessagingException {
		helper.setText(html, true);
	}

	private MimeMessage createMimeMessage() {
		MimeMessage message = sender.createMimeMessage();
		return message;
	}

	private MimeMessageHelper createMimeMessageHelper(MimeMessage message) {
		MimeMessageHelper helper = new MimeMessageHelper(message);
		return helper;
	}

	private String processTemplate(Context context) {
		String html = templateEngine.process("order", context);
		return html;
	}

	private Context createContext(Map<String, Object> model) {
		Context context = new Context();
		context.setVariables(model);
		return context;
	}

	public void setRecipient(MimeMessageHelper helper) throws MessagingException {
		helper.setTo(RECIPIENT);
	}

}
