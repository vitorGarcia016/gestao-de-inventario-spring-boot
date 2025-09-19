package com.vitor.gestaodeiventario.gestao_de_inventario.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	public void enviarEmail(String destinatario, String assunto, String mensagem) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom(from);
		mailMessage.setTo(destinatario);
		mailMessage.setSubject(assunto);
		mailMessage.setText(mensagem);
		javaMailSender.send(mailMessage);

	}
}
