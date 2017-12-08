package database.model;

import java.sql.Timestamp;

public class PrivateMessageModel {
	int messageID;
	String iGNSend;
	String iGNReceive;
	String message;
	Timestamp date;
	boolean readOrNot;

	public PrivateMessageModel(int messageID, String iGNSend, String iGNReceive, String message, Timestamp date, boolean readOrNot) {
		this.messageID = messageID;
		this.iGNSend = iGNSend;
		this.iGNReceive = iGNReceive;
		this.message = message;
		this.date = date;
		this.readOrNot = readOrNot;
	}

	public int getMessageID() {
		return messageID;
	}

	public String getiGNSend() {
		return iGNSend;
	}

	public String getiGNReceive() {
		return iGNReceive;
	}

	public String getMessage() {
		return message;
	}

	public Timestamp getDate() {
		return date;
	}

	public boolean isReadOrNot() {
		return readOrNot;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public void setiGNSend(String iGNSend) {
		this.iGNSend = iGNSend;
	}

	public void setiGNReceive(String iGNReceive) {
		this.iGNReceive = iGNReceive;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setReadOrNot(boolean readOrNot) {
		this.readOrNot = readOrNot;
	}
}
