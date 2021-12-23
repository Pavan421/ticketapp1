package com.vinnotech.portal.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private final JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(String toaddress, String subect, String body) throws MessagingException {
		javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setSubject("Welcome " + subect);

		helper.setFrom("admin@cloudcare360.com");
		helper.setText(body, true);
		helper.setTo(toaddress);
		javaMailSender.send(mimeMessage);
		System.out.println("mail sent sucsessfully");
	}

}
