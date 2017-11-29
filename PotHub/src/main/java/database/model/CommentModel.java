package database.model;

import java.sql.Timestamp;

public class CommentModel {
	int commentID;
	int postID;
	Timestamp date;
	String IGN;
	
	public CommentModel(int commentID, ForumPostModel fPM, Timestamp date, DatabaseUserModel dUM) {
		super();
		this.commentID = commentID;
		this.postID = fPM.getPostID();
		this.date = date;
		IGN = dUM.getiGN();
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
}
