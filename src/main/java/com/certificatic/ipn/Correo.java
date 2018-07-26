package com.certificatic.ipn;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * Antes de ejecutar la aplicacion es necesario entrar a esta direccion
 * https://myaccount.google.com/lesssecureapps y permitir el acceso a
 * aplicaciones menos seguras.
 */
public class Correo {

	private String userEmail = "tuCorreo@gmail.com";
	private String userPassword = "Contraseña";
	private String sendTo;
	private String mailSubject;
	private String msg;

	public Correo(String sendTo, String mailSubject, String msg) {
		this.sendTo = sendTo;
		this.mailSubject = mailSubject;
		this.msg = msg;
	}

	public boolean enviarCorreo() {

		try {
			Properties p = new Properties();

			p.setProperty("mail.smtp.host", "smtp.gmail.com");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.setProperty("mail.smtp.port", "587");
			p.setProperty("mail.smtp.user", userEmail);
			p.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(p, null);
			BodyPart texto = new MimeBodyPart();
			texto.setText(msg);

			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(texto);

			MimeMessage mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(userEmail));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
			mensaje.setSubject(mailSubject);
			mensaje.setContent(multiPart);

			Transport transport = session.getTransport("smtp");
			transport.connect(userEmail, userPassword);
			transport.sendMessage(mensaje, mensaje.getAllRecipients());
			transport.close();

			return true;
		} catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
			return false;

		}

	}

}
