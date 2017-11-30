package database.model;

import java.sql.Timestamp;

public class ForumPostModel {
	int postID;
	String thread;
	int upvotes;
	String iGN;
	Timestamp date;
	int fileAttachment;

	public ForumPostModel(int postID, String thread, int upvotes, DatabaseUserModel dUM, Timestamp date,
			FileTableModel fTM) {
		super();
		this.postID = postID;
		this.thread = thread;
		this.upvotes = upvotes;
		this.iGN = dUM.getiGN();
		this.date = date;
		this.fileAttachment = fTM.getFileID();
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

	public int getFileAttachment() {
		return fileAttachment;
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

	public void setFileAttachment(int fileAttachment) {
		this.fileAttachment = fileAttachment;
	}
}
