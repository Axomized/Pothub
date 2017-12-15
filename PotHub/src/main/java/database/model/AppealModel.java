package database.model;

import java.sql.Date;

public class AppealModel {
	String iGN;
	Date receiveDate;
	String message;
	boolean approval;
	Date dateApproved;
	
	public AppealModel(String iGN, Date receiveDate, String message, boolean approval, Date dateApproved) {
		this.iGN = iGN;
		this.receiveDate = receiveDate;
		this.message = message;
		this.approval = approval;
		this.dateApproved = dateApproved;
	}

	public String getiGN() {
		return iGN;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public String getMessage() {
		return message;
	}

	public boolean isApproval() {
		return approval;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}
}
