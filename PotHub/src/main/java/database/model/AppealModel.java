package database.model;

import java.sql.Timestamp;

public class AppealModel {
	String iGN;
	Timestamp receiveDate;
	String message;
	boolean approval;
	Timestamp dateApproved;
	
	public AppealModel(DatabaseUserModel dUM, Timestamp receiveDate, String message, boolean approval, Timestamp dateApproved) {
		this.iGN = dUM.getiGN();
		this.receiveDate = receiveDate;
		this.message = message;
		this.approval = approval;
		this.dateApproved = dateApproved;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getReceiveDate() {
		return receiveDate;
	}

	public String getMessage() {
		return message;
	}

	public boolean isApproval() {
		return approval;
	}

	public Timestamp getDateApproved() {
		return dateApproved;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setReceiveDate(Timestamp receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public void setDateApproved(Timestamp dateApproved) {
		this.dateApproved = dateApproved;
	}
}
