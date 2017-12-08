package database.model;

public class PotCastBidModel {
	int potCastID;
	String iGN;
	double bidAmount;
	String rating;
	
	public PotCastBidModel(int potCastID, String iGN, double bidAmount) {
		this.potCastID = potCastID;
		this.iGN = iGN;
		this.bidAmount = bidAmount;
	}

	public PotCastBidModel(PotCastModel pCM, DatabaseUserModel dUM, double bidAmount, String rating) {
		this.potCastID = pCM.getPotCastID();
		this.iGN = dUM.getiGN();
		this.bidAmount = bidAmount;
		this.rating = rating;
	}

	public int getPotCastID() {
		return potCastID;
	}

	public String getiGN() {
		return iGN;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public String getRating() {
		return rating;
	}

	public void setPotCastID(int potCastID) {
		this.potCastID = potCastID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
