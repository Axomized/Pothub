package donation;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	private static final String SMTP_AUTH_USER = "azure_549896266f26c56c966686e0317ab9d1@azure.com";
	private static final String SMTP_AUTH_PWD = "password123";

	//For sending email by SendGrid
	public void sendEmail(String receipientEmail, String content) throws MessagingException {
		String to = receipientEmail;
		String from = "pothubaspj@gmail.com";

		Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(properties, auth);
        MimeMessage message = new MimeMessage(mailSession);
        Multipart multipart = new MimeMultipart("alternative");
        BodyPart part = new MimeBodyPart();
        part.setText("This is the PIN to confirm your donation. It is valid only for 5 minutes.\n\n" + content);
        multipart.addBodyPart(part);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("PotHub Donation PIN");
        message.setContent(multipart);
        
        Transport transport = mailSession.getTransport();
	    transport.connect();
	    transport.sendMessage(message, message.getAllRecipients());
	    transport.close();
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
		   String username = SMTP_AUTH_USER;
		   String password = SMTP_AUTH_PWD;
		   return new PasswordAuthentication(username, password);
		}
	}
	
	/*
	 * For sending email on localhost
	 * 
	 public void sendEmail(String receipientEmail, String content) {
		String to = receipientEmail;
		String from = "pothubaspj@gmail.com";
		final String username = "pothubaspj@gmail.com";
		final String password = "ITP292-03";
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
				}
		});
		
		try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("PotHub Donation PIN");
	         message.setText("This is the PIN to confirm your donation. It is valid only for 5 minutes.\n\n" + content);
	         Transport.send(message);
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	 */
}
