package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;

@Controller
public class EmailController {

	@Resource
	private JavaMailSender sender;

	@Resource
	private CartRepository cartRepo;

	@RequestMapping("/email")
	public String home(@RequestParam long cartId) {
		try {
			Cart cart = cartRepo.findOne(cartId);
			String name;
			String firstname = cart.getUser().getFirstName();
			String lastname = cart.getUser().getLastName();
			name = firstname + " " + lastname;
			String subject = name + "'s Order";
			String message = cart.print();
			sendEmail(subject, message);
			return "redirect:/email-success";
		} catch (Exception exThrown) {
			return "redirect:/email-failure?error=" + exThrown;
		}
	}

	public void sendEmail(String subject, String text) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo("bsfppantryplus@gmail.com");
		helper.setText(text, true);
		helper.setSubject(subject);

		sender.send(message);
	}

}
