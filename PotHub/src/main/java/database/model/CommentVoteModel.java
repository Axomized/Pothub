package database.model;

import java.sql.Date;

public class CommentVoteModel {
	int commentID;
	String iGN;
	Date date;

	public CommentVoteModel(int commentID, DatabaseUserModel dUM, Date date) {
		this.commentID = commentID;
		this.iGN = dUM.getiGN();
		this.date = date;
	}

	public int getCommentID() {
		return commentID;
	}

	public String getiGN() {
		return iGN;
	}

	public Date getDate() {
		return date;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
