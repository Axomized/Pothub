package potcastTalk;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import database.Database;
import database.model.PotcastModel;

public class Email {
	private String recipientEmail;
	private String subject;
	private String body;
	
	public Email(){
		
	}
	
	public Email (String recipientEmail, String subject, String body){
		this.recipientEmail=recipientEmail;
		this.subject=subject;
		this.body=body;
	}
	
	public Email(PotcastModel pm) throws SQLException, FileNotFoundException, ClassNotFoundException{
		Database db = new Database(0);
		int bids = db.getBidsForPotcast(pm.getPotcastID()).size();
		this.recipientEmail = db.getEmailByIGN(pm.getiGN());
		if(bids>0){
		this.subject = "It's time to start cooking!";
		this.body = "Your Potcasts bids have concluded and it's time to start cooking to serve your "
				+ bids
				+ " customers who will arrive at "
				+pm.getPickupTime()
				+ ". \n\nHappy Cooking!";
		}
		else{
			this.subject = "Your potcast ended without bids";
			this.body = "Your Potcasts bids have concluded but no placed a bid. We hope this doe not discourage you in your cooking journey. "
					+ "Try writing a better description or using a nicer picture next time."
					+ ". \n\nHappy Cooking!";
		}
	}
	
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
