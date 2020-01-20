package com.nt.utility;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtility {
	@Autowired
	private JavaMailSender sender;
	/**
	 * 	This method used for sending mail 
	 * @param to
	 * @param body
	 * @param subject
	 */
	public void sendMail(String to,String body,String subject) {
		 SimpleMailMessage msg=new SimpleMailMessage();
		 msg.setTo(to);
		 msg.setSubject(subject);
		 msg.setText(body);
		 sender.send(msg);
	}
}
