package database.model;

import java.sql.Timestamp;

public class PotcastModel {
	int potcastID;
	String description;
	int maxBids;
	Timestamp bidStopTime;
	Timestamp pickupTime;
	int minBid;
	String address;
	String postalCode;
	int startingCR;
	int picture;
	
	public PotcastModel(int potcastID, String description, int maxBids, Timestamp bidStopTime, Timestamp pickupTime,
			int minBid, String address, String postalCode, int startingCR, FileTableModel fTM) {
		this.potcastID = potcastID;
		this.description = description;
		this.maxBids = maxBids;
		this.bidStopTime = bidStopTime;
		this.pickupTime = pickupTime;
		this.minBid = minBid;
		this.address = address;
		this.postalCode = postalCode;
		this.startingCR = startingCR;
		this.picture = fTM.getFileID();
	}

	public int getPotcastID() {
		return potcastID;
	}

	public String getDescription() {
		return description;
	}

	public int getMaxBids() {
		return maxBids;
	}

	public Timestamp getBidStopTime() {
		return bidStopTime;
	}

	public Timestamp getPickupTime() {
		return pickupTime;
	}

	public int getMinBid() {
		return minBid;
	}

	public String getAddress() {
		return address;
	}

	public String getPostalCode() {
		return postalCode;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMaxBids(int maxBids) {
		this.maxBids = maxBids;
	}

	public void setBidStopTime(Timestamp bidStopTime) {
		this.bidStopTime = bidStopTime;
	}

	public void setPickupTime(Timestamp pickupTime) {
		this.pickupTime = pickupTime;
	}

	public void setMinBid(int minBid) {
		this.minBid = minBid;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setStartingCR(int startingCR) {
		this.startingCR = startingCR;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}
}
