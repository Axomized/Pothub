package database.model;

import java.sql.Timestamp;

public class CommentVoteModel {
	int commentID;
	String iGN;
	Timestamp date;

	public CommentVoteModel(CommentModel cM, DatabaseUserModel dUM, Timestamp date) {
		this.commentID = cM.getCommentID();
		this.iGN = dUM.getiGN();
		this.date = date;
	}

	public int getCommentID() {
		return commentID;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
