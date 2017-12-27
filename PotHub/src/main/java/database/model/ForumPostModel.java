package database.model;

import java.sql.Timestamp;

public class ForumPostModel {
	int postID;
	String thread;
	int upvotes;
	String iGN;
	Timestamp date;
	int picture;
	String description;
	String fileAttachment;
	
	public ForumPostModel(int postID, String thread, int upvotes, String iGN, Timestamp date, int picture, String description, String fileAttachment) {
		this.postID = postID;
		this.thread = thread;
		this.upvotes = upvotes;
		this.iGN = iGN;
		this.date = date;
		this.picture = picture;
		this.description = description;
		this.fileAttachment = fileAttachment;
	}

	public int getPostID() {
		return postID;
	}

	public String getThread() {
		return thread;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getDate() {
		return date;
	}

	public int getPicture() {
		return picture;
	}

	public String getFileAttachment() {
		return fileAttachment;
	}

	public String getDescription() {
		return description;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}

	public void setFileAttachment(String fileAttachment) {
		this.fileAttachment = fileAttachment;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
