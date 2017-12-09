package database.model;

public class PotcastBidModel {
	int potcastID;
	String iGN;
	double bidAmount;
	String rating;
	
	public PotcastBidModel(PotcastModel pCM, DatabaseUserModel dUM, double bidAmount, String rating) {
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

	public double getBidAmount() {
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

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
