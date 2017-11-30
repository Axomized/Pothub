package database.model;

import java.sql.Timestamp;

public class CommentVoteModel {
	int commentID;
	int upOrDown;
	String iGN;
	Timestamp date;

	public CommentVoteModel(CommentModel cM, int upOrDown, DatabaseUserModel dUM, Timestamp date) {
		super();
		this.commentID = cM.getCommentID();
		this.upOrDown = upOrDown;
		this.iGN = dUM.getiGN();
		this.date = date;
	}

	public int getCommentID() {
		return commentID;
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

	public void setCommentID(int commentID) {
		this.commentID = commentID;
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
