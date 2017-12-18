package database.model;

import java.sql.Date;

public class PotcastModel {
	int potcastID;
	String title;
	String description;
	int maxBids;
	Date bidStopTime;
	Date pickupTime;
	int minBid;
	int startingCR;
	int picture;
	
	public PotcastModel(int potcastID, String title, String description, int maxBids, Date bidStopTime,
			Date pickupTime, int minBid, int startingCR, int picture) {
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
}
