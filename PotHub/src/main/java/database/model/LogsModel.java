package database.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class LogsModel {
	int logID;
	String iGN;
	Timestamp logDate;
	String iPAddress;
	String logType;
	String logActivity;
	boolean isSuspicious;
	
	public LogsModel(int logID, String iGN, Timestamp logDate, String iPAddress, String logType, String logActivity, boolean isSuspicious) {
		this.logID = logID;
		this.iGN = iGN;
		this.logDate = logDate;
		this.iPAddress = iPAddress;
		this.logType = logType;
		this.logActivity = logActivity;
		this.isSuspicious = isSuspicious;
	}
	
	public LogsModel(String iGN, Timestamp logDate, String iPAddress, String logType, String logActivity, boolean isSuspicious) {
		this.iGN = iGN;
		this.logDate = logDate;
		this.iPAddress = iPAddress;
		this.logType = logType;
		this.logActivity = logActivity;
		this.isSuspicious = isSuspicious;
	}

	public int getLogID() {
		return logID;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getLogDate() {
		return logDate;
	}

	public String getiPAddress() {
		return iPAddress;
	}

	public String getLogType() {
		return logType;
	}

	public String getLogActivity() {
		return logActivity;
	}

	public boolean isSuspicious() {
		return isSuspicious;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public void setLogActivity(String logActivity) {
		this.logActivity = logActivity;
	}

	public void setSuspicious(boolean isSuspicious) {
		this.isSuspicious = isSuspicious;
	}
	
	public String converTimestamp(Timestamp timestamp) {
		Instant instant = timestamp.toInstant();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		String dateString = format.format(Date.from(instant));
		return dateString;
	}
}
