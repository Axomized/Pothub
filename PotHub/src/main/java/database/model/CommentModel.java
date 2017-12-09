package database.model;

import java.sql.Timestamp;

public class CommentModel {
	int commentID;
	int postID;
	Timestamp date;
	String IGN;
	int comment1;

	public CommentModel(int commentID, int postID, Timestamp date, DatabaseUserModel dUM, int comment1) {
		this.commentID = commentID;
		this.postID = postID;
		this.date = date;
		IGN = dUM.getiGN();
		this.comment1 = comment1;
	}

	public int getCommentID() {
		return commentID;
	}

	public int getPostID() {
		return postID;
	}

	public Timestamp getDate() {
		return date;
	}

	public String getIGN() {
		return IGN;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setIGN(String iGN) {
		IGN = iGN;
	}

	public int getComment1() {
		return comment1;
	}

	public void setComment1(int comment1) {
		this.comment1 = comment1;
	}
}
