package database.model;

import java.sql.Timestamp;

public class ForumVoteModel {
	private int postID;
	private String iGN;
	private Timestamp date;

	public ForumVoteModel(int postID, String iGN, Timestamp date) {
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

	public Timestamp getDate() {
		return date;
	}

	public ForumVoteModel() {
		super();
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Timestamp tsm) {
		this.date = tsm;
	}
}
