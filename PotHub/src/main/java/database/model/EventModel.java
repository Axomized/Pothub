package database.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class EventModel {
	int eventID;
	String eventName;
	String iGN;
	int thumbnail;
	String description;
	Date date;
	String postalCode;
	String venue;
	int max_No_People;
	String guest;
	String fileList;
	
	public EventModel(int eventID, String eventName, DatabaseUserModel dUM, FileTableModel fTM, String description, Date date, String postalCode, String venue, int max_No_People,
			String guest, String fileList) {
		this.eventID = eventID;
		this.eventName = eventName;
		this.iGN = dUM.getiGN();
		this.thumbnail = fTM.getFileID();
		this.description = description;
		this.date = date;
		this.postalCode = postalCode;
		this.venue = venue;
		this.max_No_People = max_No_People;
		this.guest = guest;
		this.fileList = fileList;
	}

	public int getEventID() {
		return eventID;
	}
	
	public String getEventName() {
		return eventName;
	}

	public String getiGN() {
		return iGN;
	}

	public int getThumbnail() {
		return thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getVenue() {
		return venue;
	}

	public int getMax_No_People() {
		return max_No_People;
	}

	public ArrayList<String> getGuest() {
		ArrayList<String> als = new ArrayList<String>();
		Scanner sc = new Scanner(guest);
		sc.useDelimiter("_");
		while(sc.hasNext()) {
			als.add(sc.next());
		}
		sc.close();
		return als;
	}

	public ArrayList<String> getFileList() {
		ArrayList<String> als = new ArrayList<String>();
		Scanner sc = new Scanner(fileList);
		sc.useDelimiter("_");
		while(sc.hasNext()) {
			als.add(sc.next());
		}
		sc.close();
		return als;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setThumbnail(int thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public void setMax_No_People(int max_No_People) {
		this.max_No_People = max_No_People;
	}

	public void setGuest(ArrayList<String> guest) {
		String line = "";
		for(String s: guest)
			line += s;
		this.guest = line;
	}

	public void setFileList(ArrayList<String> fileList) {
		String line = "";
		for(String s: fileList)
			line += s;
		this.fileList = line;
	}
}
