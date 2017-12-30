package database.model;

import java.sql.Date;

public class ReportModel {
	int reportID;
	String iGNSend;
	String iGNReceive;
	String evidenceType;
	Date date;
	int evidence;
	String reason;
	int guiltyOrNot;
	
	public ReportModel(int reportID, String iGNSend, String iGNReceive, String evidenceType, Date date, int evidence,
			String reason, int guiltyOrNot) {
		this.reportID = reportID;
		this.iGNSend = iGNSend;
		this.iGNReceive = iGNReceive;
		this.evidenceType = evidenceType;
		this.date = date;
		this.evidence = evidence;
		this.reason = reason;
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

	public String getEvidenceType() {
		return evidenceType;
	}

	public Date getDate() {
		return date;
	}

	public int getEvidence() {
		return evidence;
	}

	public String getReason() {
		return reason;
	}

	public int isGuiltyOrNot() {
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

	public void setEvidenceType(String evidenceType) {
		this.evidenceType = evidenceType;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setEvidence(int evidence) {
		this.evidence = evidence;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setGuiltyOrNot(int guiltyOrNot) {
		this.guiltyOrNot = guiltyOrNot;
	}
}
