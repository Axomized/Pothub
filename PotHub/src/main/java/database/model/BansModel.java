package database.model;

import java.sql.Date;

public class BansModel {
	String iGN;
	Date startDate;
	Date endDate;
	String reason;
	String admin;
	boolean pardoned;

	public BansModel(DatabaseUserModel dUM, Date startDate, Date endDate, String reason, String admin,
			boolean pardoned) {
		super();
		this.iGN = dUM.getiGN();
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.admin = admin;
		this.pardoned = pardoned;
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
