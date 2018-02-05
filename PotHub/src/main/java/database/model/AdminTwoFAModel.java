package database.model;

import java.sql.Timestamp;

public class AdminTwoFAModel {
	private String iGN;
	private String sessionID;
	private Timestamp timeRequested;
	private String twoFAKey;
	private boolean terminated;
	private int fails;
	
	public AdminTwoFAModel() {

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

	public Timestamp getTimeRequested() {
		return timeRequested;
	}

	public void setTimeRequested(Timestamp timeRequested) {
		this.timeRequested = timeRequested;
	}

	public String getTwoFAKey() {
		return twoFAKey;
	}

	public void setTwoFAKey(String twoFAKey) {
		this.twoFAKey = twoFAKey;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	public int getFails() {
		return fails;
	}

	public void setFails(int fails) {
		this.fails = fails;
	}

	@Override
	public String toString() {
		return "AdminTwoFAModel [iGN=" + iGN + ", sessionID=" + sessionID + ", timeRequested=" + timeRequested
				+ ", twoFAKey=" + twoFAKey + ", terminated=" + terminated + ", fails=" + fails + "]";
	}
}
