package login;

import java.security.Security;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.SMTPTransport;

import database.model.ForgetPasswordModel;


public class SendEmail {
	public static String USER_NAME = "aspjproject1";  // GMail user name (just the part before "@gmail.com")
	public static String PASSWORD = "AspjProject1234567890"; // GMail password
	public static String SUBJECT ="PotHub Password Reset";
	String newPassword;
	
	public static void main(String [] args) throws AddressException, MessagingException{
        ForgetPasswordModel fpm = new ForgetPasswordModel();
        fpm.setNewPassword(getRandomPassword());
		String recipientEmail = "xiangjing90@hotmail.com";
		String body = fpm.getNewPassword();
		sendEmail(USER_NAME, PASSWORD, recipientEmail, SUBJECT, body);
	}
	public static void sendEmail(String username, String password, String recipientEmail, String subject, String body) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        msg.setSubject(subject);
        msg.setText(body, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
    }
	
		
	
	public static String getRandomPassword() {
        String PASSWORDCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder newPassword = new StringBuilder();
        Random rnd = new Random();
        while (newPassword.length() < 18) { // length of the random password.
            int index = (int) (rnd.nextFloat() * PASSWORDCHARS.length());
            newPassword.append(PASSWORDCHARS.charAt(index));
        }
        String newPasswordStr = newPassword.toString();
        System.out.println(newPasswordStr);
        
        ForgetPasswordModel fpm = new ForgetPasswordModel();
        fpm.setNewPassword(newPasswordStr);
        return newPasswordStr;

    }
}



