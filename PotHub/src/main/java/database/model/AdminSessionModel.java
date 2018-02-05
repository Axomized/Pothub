package database.model;

import java.sql.Timestamp;

public class AdminSessionModel {
	private String iGN;
	private String sessionID;
	private Timestamp timeAuthenticated;
	private boolean terminated;
	
	public AdminSessionModel() {

	}

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Timestamp getTimeAuthenticated() {
		return timeAuthenticated;
	}

	public void setTimeAuthenticated(Timestamp timeAuthenticated) {
		this.timeAuthenticated = timeAuthenticated;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	@Override
	public String toString() {
		return "AdminSession [iGN=" + iGN + ", sessionID=" + sessionID + ", timeAuthenticated=" + timeAuthenticated
				+ ", terminated=" + terminated + "]";
	}
}
