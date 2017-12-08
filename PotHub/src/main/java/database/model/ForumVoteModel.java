package database.model;

import java.sql.Timestamp;

public class ForumVoteModel {
	int postID;
	String iGN;
	Timestamp date;

	public ForumVoteModel(ForumPostModel fPM, DatabaseUserModel dUM, Timestamp date) {
		this.postID = fPM.getPostID();
		this.iGN = dUM.getiGN();
		this.date = date;
	}

	public int getPostID() {
		return postID;
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

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
