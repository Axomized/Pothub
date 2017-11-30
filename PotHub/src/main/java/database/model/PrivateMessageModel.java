package database.model;

import java.sql.Timestamp;

public class PrivateMessageModel {
	String iGNSend;
	String iGNReceive;
	String message;
	Timestamp date;
	boolean readOrNot;

	public PrivateMessageModel(String iGNSend, String iGNReceive, String message, Timestamp date, boolean readOrNot) {
		super();
		this.iGNSend = iGNSend;
		this.iGNReceive = iGNReceive;
		this.message = message;
		this.date = date;
		this.readOrNot = readOrNot;
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
