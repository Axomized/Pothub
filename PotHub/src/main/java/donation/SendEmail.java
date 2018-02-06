package donation;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	private static final String SMTP_AUTH_USER = "azure_0305d0fd475a14075c13118cc95f5c34@azure.com";
	private static final String SMTP_AUTH_PWD = "ITP292-03";

	public void sendEmail(String receipientEmail, String content) {
		String to = receipientEmail;
		String from = "pothubaspj@gmail.com";
		final String username = "pothubaspj@gmail.com";
		final String password = "ITP292-03";
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", host);
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
				}
		});
		
		try {
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("PotHub Donation PIN");
	         message.setText("This is the PIN to confirm your donation. It is valid only for 5 minutes.\n\n" + content);
	         Transport transport = session.getTransport();
	         transport.connect();
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
