package database.model;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;

import database.Database;

public class PotcastModel {
	String iGN;
	int potcastID;
	String title;
	String description;
	int maxBids;
	Date bidStopTime;
	Date pickupTime;
	int minBid;
	int startingCR;
	int picture;
	
	public PotcastModel(String iGN, int potcastID, String title, String description, int maxBids, Date bidStopTime,
			Date pickupTime, int minBid, int startingCR, int picture) {
		this.iGN=iGN;
		this.potcastID = potcastID;
		this.title = title;
		this.description = description;
		this.maxBids = maxBids;
		this.bidStopTime = bidStopTime;
		this.pickupTime = pickupTime;
		this.minBid = minBid;
		this.startingCR = startingCR;
		this.picture = picture;
	}
	
	public String toString() {
		try {
		Database db = new Database(0);
			return "PotcastID" + potcastID + "Title " + title + "Description " + description + "Max Bids " + maxBids + "Min Bids " + minBid + "Bid stop time "+bidStopTime+" Pickup Time "+pickupTime+" startingCR"+startingCR+" PictureID "+picture+" PictureName "+db.getImageByImageID(picture);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public int getPotcastID() {
		return potcastID;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getMaxBids() {
		return maxBids;
	}

	public Date getBidStopTime() {
		return bidStopTime;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public int getMinBid() {
		return minBid;
	}

	public int getStartingCR() {
		return startingCR;
	}

	public int getPicture() {
		return picture;
	}

	public void setPotcastID(int potcastID) {
		this.potcastID = potcastID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMaxBids(int maxBids) {
		this.maxBids = maxBids;
	}

	public void setBidStopTime(Date bidStopTime) {
		this.bidStopTime = bidStopTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public void setMinBid(int minBid) {
		this.minBid = minBid;
	}

	public void setStartingCR(int startingCR) {
		this.startingCR = startingCR;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}
}
