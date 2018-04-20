package org.wecancodeit.pantryplus.controllers;

import java.util.Map;

import javax.annotation.Resource;
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
			Map<String, Object> message = cart.print();
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
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("order", context);

		helper.setTo("bsfppantryplus@gmail.com");
		helper.setText(html, true);
		helper.setSubject(subject);

		sender.send(message);
	}

}
