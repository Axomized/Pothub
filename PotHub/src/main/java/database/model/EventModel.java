package database.model;

import java.sql.Timestamp;

public class EventModel {
	int eventID;
	String iGN;
	Timestamp date;
	String venue;
	int max_No_People;
	String postalCode;
	String unitNo;
	String fileList;

	public EventModel(int eventID, DatabaseUserModel dUM, Timestamp date, String venue, int max_No_People, String postalCode,
			String unitNo) {
		this.eventID = eventID;
		this.iGN = dUM.getiGN();
		this.date = date;
		this.venue = venue;
		this.max_No_People = max_No_People;
		this.postalCode = postalCode;
		this.unitNo = unitNo;
	}

	public EventModel(int eventID, DatabaseUserModel dUM, Timestamp date, String venue, int max_No_People, String postalCode,
			String unitNo, String fileList) {
		this.eventID = eventID;
		this.iGN = dUM.getiGN();
		this.date = date;
		this.venue = venue;
		this.max_No_People = max_No_People;
		this.postalCode = postalCode;
		this.unitNo = unitNo;
		this.fileList = fileList;
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

	public String getPostalCode() {
		return postalCode;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public String getFileList() {
		return fileList;
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

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public void setFileList(String fileList) {
		this.fileList = fileList;
	}
}
