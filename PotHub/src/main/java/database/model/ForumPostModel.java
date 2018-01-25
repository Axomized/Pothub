package database.model;

import java.sql.Timestamp;

public class ForumPostModel {
	private int postID;
	private String thread;
	private int upvotes;
	private String iGN;
	private Timestamp date;
	private int picture;
	private String description;
	private String fileAttachment;
	private String ForumNormalText;
	private String ForumURL;

	public ForumPostModel(int postID, String thread, int upvotes, String iGN, Timestamp date, int picture,
			String description, String fileAttachment, String forumNormalText, String forumURL) {
		super();
		this.postID = postID;
		this.thread = thread;
		this.upvotes = upvotes;
		this.iGN = iGN;
		this.date = date;
		this.picture = picture;
		this.description = description;
		this.fileAttachment = fileAttachment;
		this.ForumNormalText = forumNormalText;
		this.ForumURL = forumURL;
		}


	public String getForumNormalText() {
		return ForumNormalText;
	}


	public void setForumNormalText(String forumNormalText) {
		ForumNormalText = forumNormalText;
	}


	public String getForumURL() {
		return ForumURL;
	}


	public void setForumURL(String forumURL) {
		ForumURL = forumURL;
	}


	public ForumPostModel() {
		super();
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
