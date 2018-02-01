package database.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class EventModel {
	private int eventID;
	private String eventName;
	private String iGN;
	private int thumbnail;
	private String description;
	private Timestamp date;
	private String postalCode;
	private String venue;
	private boolean autoAccept;
	private int max_No_People;
	private String guest;
	private String fileList;
	private String status;
	
	public EventModel(String eventName, String iGN, int thumbnail, String description, Timestamp date, String postalCode,
			String venue, String guest, String fileList, String status) {
		this.eventName 		= eventName;
		this.iGN			= iGN;
		this.thumbnail 		= thumbnail;
		this.description 	= description;
		this.date 			= date;
		this.postalCode 	= postalCode;
		this.venue 			= venue;
		this.guest			= guest;
		this.fileList 		= fileList;
		this.status 		= status;
	}

	public EventModel(int eventID, String eventName, String iGN, int thumbnail, String description, Timestamp date,
			String postalCode, String venue, int max_No_People, String guest, String status) {
		this.eventID = eventID;
		this.eventName = eventName;
		this.iGN = iGN;
		this.thumbnail = thumbnail;
		this.description = description;
		this.date = date;
		this.postalCode = postalCode;
		this.venue = venue;
		this.max_No_People = max_No_People;
		this.guest = guest;
		this.status = status;
	}

	public EventModel() {}

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

	public Timestamp getDate() {
		return date;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getVenue() {
		return venue;
	}

	public boolean isAutoAccept() {
		return autoAccept;
	}

	public int getMax_No_People() {
		return max_No_People;
	}

	public String getGuest() {
		return guest;
	}

	public String getFileList() {
		return fileList;
	}
	
	public String getStatus() {
		return status;
	}

	public ArrayList<String> getGuestArray() throws UnsupportedEncodingException {
		ArrayList<String> als = new ArrayList<String>();
		if(guest != null) {
			Scanner sc = new Scanner(decodeString(guest));
			sc.useDelimiter("_");
			while(sc.hasNext()) {
				als.add(sc.next());
			}
			sc.close();
		}
		return als;
	}

	public ArrayList<Integer> getFileListArray() {
		try {
			ArrayList<Integer> als = new ArrayList<Integer>();
			Scanner sc = new Scanner(fileList);
			sc.useDelimiter("_");
			while(sc.hasNext()) {
				als.add(Integer.parseInt(sc.next()));
			}
			sc.close();
			return als;
		}catch(NullPointerException e) {
			return new ArrayList<Integer>();
		}
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

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public void setAutoAccept(boolean autoAccept) {
		this.autoAccept = autoAccept;
	}

	public void setMax_No_People(int max_No_People) {
		this.max_No_People = max_No_People;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public void setFileList(String fileList) {
		this.fileList = fileList;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setGuestArray(ArrayList<String> guest) {
		String line = "";
		for(String s: guest)
			line += s;
		this.guest = line;
	}

	public void setFileListArray(ArrayList<String> fileList) {
		String line = "";
		boolean first = true;
		for(String s: fileList) {
			if(first) {
				line = s;
			}else {
				line += "_" + s;
			}
		}
		this.fileList = line;
	}
	
	private String decodeString(String line) throws UnsupportedEncodingException {
		return URLDecoder.decode(line, "UTF-8");
	}
}
