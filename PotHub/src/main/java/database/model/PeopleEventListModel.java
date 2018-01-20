package database.model;

import java.util.ArrayList;
import java.util.Scanner;

public class PeopleEventListModel {
	private int eventID;
	private String invitationPending;
	
	public PeopleEventListModel(int eventID, String invitationPending) {
		this.eventID = eventID;
		this.invitationPending = invitationPending;
	}

	public int getEventID() {
		return eventID;
	}

	public String getInvitationPending() {
		return invitationPending;
	}

	public ArrayList<String> getInvitationPendingArray() {
		ArrayList<String> als = new ArrayList<String>();
		Scanner sc = new Scanner(invitationPending);
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

	public void setInvitationPending(String invitationPending) {
		this.invitationPending = invitationPending;
	}

	public void setInvitationPendingArray(ArrayList<String> invitationPending) {
		String line = "";
		for(String s: invitationPending)
			line += s;
		this.invitationPending = line;
	}
}
