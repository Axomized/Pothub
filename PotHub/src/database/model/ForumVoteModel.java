package database.model;

import java.sql.Timestamp;

public class ForumVoteModel {
	int postID;
	int upOrDown;
	String iGN;
	Timestamp date;
	
	public ForumVoteModel(ForumPostModel fPM, int upOrDown, DatabaseUserModel dUM, Timestamp date) {
		super();
		this.postID = fPM.getPostID();
		this.upOrDown = upOrDown;
		this.iGN = dUM.getiGN();
		this.date = date;
	}

	public int getPostID() {
		return postID;
	}

	public int getUpOrDown() {
		return upOrDown;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setUpOrDown(int upOrDown) {
		this.upOrDown = upOrDown;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
