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

	public void sendEmail(String receipientEmail, String content) {
		// Recipient's email ID needs to be mentioned
		String to = receipientEmail;

		// Sender's email ID needs to be mentioned
		String from = "pothubaspj@gmail.com";
		final String username = "pothubaspj@gmail.com";
		final String password = "ITP292-03";

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
				}
		});
		
		try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("PotHub Donation PIN");

	         // Now set the actual message
	         message.setText("This is the PIN to confirm your donation. It is valid only for 5 minutes.\n\n" + content);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

	public static void main(String[] args) {
		SendEmail SE = new SendEmail();
		SE.sendEmail("XXX@gmail.com", "Content");
	}
}
