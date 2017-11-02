package database.model;

public class PeopleEventListModel {
	int eventID;
	String iGN;
	boolean bofirmedOrPending;
	
	public PeopleEventListModel(EventModel eM, DatabaseUserModel dUM, boolean bofirmedOrPending) {
		super();
		this.eventID = eM.getEventID();
		this.iGN = dUM.getiGN();
		this.bofirmedOrPending = bofirmedOrPending;
	}

	public int getEventID() {
		return eventID;
	}

	public String getiGN() {
		return iGN;
	}

	public boolean isBofirmedOrPending() {
		return bofirmedOrPending;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setBofirmedOrPending(boolean bofirmedOrPending) {
		this.bofirmedOrPending = bofirmedOrPending;
	}
}
