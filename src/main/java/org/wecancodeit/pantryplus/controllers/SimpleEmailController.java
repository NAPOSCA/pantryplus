package org.wecancodeit.pantryplus.controllers;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {

	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/simpleemail")
	@ResponseBody
	String home() {
		try {
			sendEmail();
			return "Order Sent!";
		} catch (Exception exThrown) {
			return "Error in sending email: " + exThrown;
		}
	}

	private void sendEmail() throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo("bsfppantryplus@gmail.com");
		helper.setText("Canned Green Beans  Qty: 2");
		helper.setSubject("Food Pantry Order");

		sender.send(message);
	}

}
