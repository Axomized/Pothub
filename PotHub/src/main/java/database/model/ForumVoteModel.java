package database.model;

import java.sql.Date;

public class ForumVoteModel {
	int postID;
	String iGN;
	Date date;

	public ForumVoteModel(int postID, String iGN, Date date) {
		this.postID = postID;
		this.iGN = iGN;
		this.date = date;
	}

	public int getPostID() {
		return postID;
	}

	public String getiGN() {
		return iGN;
	}

	public Date getDate() {
		return date;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
