package potcastTalk;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import database.Database;
import database.model.PotcastModel;
import donation.SendEmail;

public class EmailTask extends TimerTask {
	private Date sendWhen;
	private Email email;
	private PotcastModel potcast;
	
	public EmailTask(Date sendWhen, Email email){
		this.setSendWhen(sendWhen);
		this.email=email;
	}
	
	public EmailTask(PotcastModel potcast){
		this.setSendWhen(potcast.getBidStopTime());
		this.setPotcast(potcast);
	}

    @Override
    public void run() {
    	try{
    		try {
				this.email = new Email(potcast);
		        completeTask();
			} catch (FileNotFoundException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    	}
		catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }

    private void completeTask() throws AddressException, MessagingException, FileNotFoundException, ClassNotFoundException, SQLException {
		SendEmail se = new SendEmail();
		se.sendEmail(email);
       Database db = new Database(2);
       db.setPotcastEmailAsSend(potcast.getPotcastID());
    }

	public Date getSendWhen() {
		return sendWhen;
	}

	public void setSendWhen(Date sendWhen) {
		this.sendWhen = sendWhen;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public PotcastModel getPotcast() {
		return potcast;
	}

	public void setPotcast(PotcastModel potcast) {
		this.potcast = potcast;
	}
}