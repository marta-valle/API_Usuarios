package br.com.cotiinformatica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

//CRIADA 13/06
@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	// CAPTURANDO A CONF. DO ARQUIVO: application.properties
	@Value("${spring.mail.username}")
	private String userName;

	// MÉTODO QUE REALIZA O ENVIO DOS EMAILS
	public void send(String to, String subject, String body) throws Exception {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(userName);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);
		
		javaMailSender.send(message);
	}
}
