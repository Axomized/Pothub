package database.model;

public class TemporaryStore {
	String iGN;
	double temporaryAmount;
	String temporaryPIN;
	
	public TemporaryStore(DatabaseUserModel dUM, double temporaryAmount, String temporaryPIN) {
		this.iGN = dUM.getiGN();
		this.temporaryAmount = temporaryAmount;
		this.temporaryPIN = temporaryPIN;
	}

	public String getiGN() {
		return iGN;
	}

	public double getTemporaryAmount() {
		return temporaryAmount;
	}

	public String getTemporaryPIN() {
		return temporaryPIN;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setTemporaryAmount(double temporaryAmount) {
		this.temporaryAmount = temporaryAmount;
	}

	public void setTemporaryPIN(String temporaryPIN) {
		this.temporaryPIN = temporaryPIN;
	}
}
