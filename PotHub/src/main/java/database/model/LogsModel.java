package database.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

public class LogsModel {
	private int logID;
	private String iGN;
	private Timestamp logDate;
	private String iPAddress;
	private String logType;
	private String logActivity;
	
	public LogsModel() {
		
	}
	
	public LogsModel(int logID, String iGN, Timestamp logDate, String iPAddress, String logType, String logActivity) {
		this.logID = logID;
		this.iGN = iGN;
		this.logDate = logDate;
		this.iPAddress = iPAddress;
		this.logType = logType;
		this.logActivity = logActivity;
	}
	
	public LogsModel(String iGN, Timestamp logDate, String iPAddress, String logType, String logActivity) {
		this.iGN = iGN;
		this.logDate = logDate;
		this.iPAddress = iPAddress;
		this.logType = logType;
		this.logActivity = logActivity;
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

	public String converTimestamp(Timestamp timestamp) {
		LocalDateTime datetime = timestamp.toLocalDateTime();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		String dateString = datetime.format(format);
		return dateString;
	}
	
	public String getClientIP(HttpServletRequest request) throws UnknownHostException {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.isEmpty()) {
			ip = request.getRemoteAddr();
			if (ip == null || ip.isEmpty() || ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
				ip = InetAddress.getLocalHost().getHostAddress();
			}
		}
		return ip;
	}
}
