package database.model;

import java.math.BigDecimal;

public class PotcastBidModel {
	int potcastID;
	String iGN;
	BigDecimal bidAmount;
	String rating;
	
	public PotcastBidModel(PotcastModel pCM, DatabaseUserModel dUM, BigDecimal bidAmount, String rating) {
		this.potcastID = pCM.getPotcastID();
		this.iGN = dUM.getiGN();
		this.bidAmount = bidAmount;
		this.rating = rating;
	}

	public int getPotcastID() {
		return potcastID;
	}

	public String getiGN() {
		return iGN;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public String getRating() {
		return rating;
	}

	public void setPotcastID(int potcastID) {
		this.potcastID = potcastID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
