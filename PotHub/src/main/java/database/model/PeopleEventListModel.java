package database.model;

import java.util.ArrayList;
import java.util.Scanner;

public class PeopleEventListModel {
	int eventID;
	String invitationPending;
	String invitationConfirm;
	
	public PeopleEventListModel(EventModel eM, String invitationPending, String invitationConfirm) {
		this.eventID = eM.getEventID();
		this.invitationPending = invitationPending;
		this.invitationConfirm = invitationConfirm;
	}

	public int getEventID() {
		return eventID;
	}

	public ArrayList<String> getInvitationPending() {
		ArrayList<String> als = new ArrayList<String>();
		Scanner sc = new Scanner(invitationPending);
		sc.useDelimiter("_");
		while(sc.hasNext()) {
			als.add(sc.next());
		}
		sc.close();
		return als;
	}

	public ArrayList<String> getInvitationConfirm() {
		ArrayList<String> als = new ArrayList<String>();
		Scanner sc = new Scanner(invitationConfirm);
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

	public void setInvitationPending(ArrayList<String> invitationPending) {
		String line = "";
		for(String s: invitationPending)
			line += s;
		this.invitationPending = line;
	}

	public void setInvitationConfirm(ArrayList<String> invitationConfirm) {
		String line = "";
		for(String s: invitationConfirm)
			line += s;
		this.invitationPending = line;
	}
}
