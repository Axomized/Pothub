package database.model;

import java.sql.Date;

public class CommentModel {
	int commentID;
	int postID;
	Date date;
	String iGN;
	int comment1;
	String description;

	public CommentModel(int commentID, int postID, Date date, String iGN, int comment1, String description) {
		this.commentID = commentID;
		this.postID = postID;
		this.date = date;
		this.iGN = iGN;
		this.comment1 = comment1;
		this.description = description;
	}

	public int getCommentID() {
		return commentID;
	}

	public int getPostID() {
		return postID;
	}

	public Date getDate() {
		return date;
	}

	public String getiGN() {
		return iGN;
	}

	public int getComment1() {
		return comment1;
	}


	public String getDescription() {
		return description;
	}
	
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setComment1(int comment1) {
		this.comment1 = comment1;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
