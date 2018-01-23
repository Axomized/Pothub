package database.model;

import java.sql.Date;

public class AppealModel {
	private int appealID;
	private String iGN;
	private Date receiveDate;
	private String message;
	private boolean approval;
	private Date dateApproved;
	private int banID;
	
	public AppealModel(int appealID, String iGN, Date receiveDate, String message, boolean approval, Date dateApproved, int banID) {
		this.appealID = appealID;
		this.iGN = iGN;
		this.receiveDate = receiveDate;
		this.message = message;
		this.approval = approval;
		this.dateApproved = dateApproved;
		this.setBanID(banID);
	}

	public AppealModel() {
		
	}

	public int getAppealID() {
		return appealID;
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

	public void setAppealID(int appealID) {
		this.appealID = appealID;
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

	public int getBanID() {
		return banID;
	}

	public void setBanID(int banID) {
		this.banID = banID;
	}
}
