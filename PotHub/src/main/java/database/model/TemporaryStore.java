package database.model;

public class TemporaryStore {
	String iGN;
	double temporaryAmount;
	String temporaryPIN;
	String temporaryStore;
	
	public TemporaryStore(DatabaseUserModel dUM, double temporaryAmount, String temporaryPIN, String temporaryStore) {
		this.iGN = dUM.getiGN();
		this.temporaryAmount = temporaryAmount;
		this.temporaryPIN = temporaryPIN;
		this.temporaryStore = temporaryStore;
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

	public String getTemporaryStore() {
		return temporaryStore;
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

	public void setTemporaryStore(String temporaryStore) {
		this.temporaryStore = temporaryStore;
	}
}
