package database.model;

import java.util.ArrayList;
import java.util.Scanner;

public class PeopleEventListModel {
	int eventID;
	String invitationPending;
	String invitationConfirm;
	
	public PeopleEventListModel(int eventID, String invitationPending, String invitationConfirm) {
		this.eventID = eventID;
		this.invitationPending = invitationPending;
		this.invitationConfirm = invitationConfirm;
	}

	public int getEventID() {
		return eventID;
	}

	public String getInvitationPending() {
		return invitationPending;
	}

	public String getInvitationConfirm() {
		return invitationConfirm;
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

	public ArrayList<String> getInvitationConfirmArray() {
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

	public void setInvitationPending(String invitationPending) {
		this.invitationPending = invitationPending;
	}

	public void setInvitationConfirm(String invitationConfirm) {
		this.invitationConfirm = invitationConfirm;
	}

	public void setInvitationPendingArray(ArrayList<String> invitationPending) {
		String line = "";
		for(String s: invitationPending)
			line += s;
		this.invitationPending = line;
	}

	public void setInvitationConfirmArray(ArrayList<String> invitationConfirm) {
		String line = "";
		for(String s: invitationConfirm)
			line += s;
		this.invitationPending = line;
	}
}
