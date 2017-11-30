package database.model;

import java.sql.Timestamp;

public class EventModel {
	int eventID;
	String iGN;
	Timestamp date;
	String venue;
	int max_No_People;

	public EventModel(int eventID, DatabaseUserModel dUM, Timestamp date, String venue, int max_No_People) {
		super();
		this.eventID = eventID;
		this.iGN = dUM.getiGN();
		this.date = date;
		this.venue = venue;
		this.max_No_People = max_No_People;
	}

	public int getEventID() {
		return eventID;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getDate() {
		return date;
	}

	public String getVenue() {
		return venue;
	}

	public int getMax_No_People() {
		return max_No_People;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public void setMax_No_People(int max_No_People) {
		this.max_No_People = max_No_People;
	}
}
