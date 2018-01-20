package database.model;

import java.sql.Date;

public class BansModel {
	private int banID;
	private String iGN;
	private Date startDate;
	private Date endDate;
	private String reason;
	private String admin;
	private boolean pardoned;

	

	public BansModel(int banID, String iGN, Date startDate, Date endDate, String reason, String admin,
			boolean pardoned) {
		super();
		this.banID = banID;
		this.iGN = iGN;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.admin = admin;
		this.pardoned = pardoned;
	}

	public int getBanID() {
		return banID;
	}


	public String getiGN() {
		return iGN;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getReason() {
		return reason;
	}

	public String getAdmin() {
		return admin;
	}

	public boolean isPardoned() {
		return pardoned;
	}

	public void setBanID(int banID) {
		this.banID = banID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public void setPardoned(boolean pardoned) {
		this.pardoned = pardoned;
	}
}
