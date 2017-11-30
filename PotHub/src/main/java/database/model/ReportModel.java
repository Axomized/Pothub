package database.model;

import java.sql.Timestamp;

public class ReportModel {
	int reportID;
	String iGNSend;
	String iGNReceive;
	String reason;
	Timestamp date;
	String evidence;
	boolean guiltyOrNot;

	public ReportModel(int reportID, String iGNSend, String iGNReceive, String reason, Timestamp date, String evidence,
			boolean guiltyOrNot) {
		super();
		this.reportID = reportID;
		this.iGNSend = iGNSend;
		this.iGNReceive = iGNReceive;
		this.reason = reason;
		this.date = date;
		this.evidence = evidence;
		this.guiltyOrNot = guiltyOrNot;
	}

	public int getReportID() {
		return reportID;
	}

	public String getiGNSend() {
		return iGNSend;
	}

	public String getiGNReceive() {
		return iGNReceive;
	}

	public String getReason() {
		return reason;
	}

	public Timestamp getDate() {
		return date;
	}

	public String getEvidence() {
		return evidence;
	}

	public boolean isGuiltyOrNot() {
		return guiltyOrNot;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public void setiGNSend(String iGNSend) {
		this.iGNSend = iGNSend;
	}

	public void setiGNReceive(String iGNReceive) {
		this.iGNReceive = iGNReceive;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public void setGuiltyOrNot(boolean guiltyOrNot) {
		this.guiltyOrNot = guiltyOrNot;
	}
}
