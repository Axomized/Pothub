package database.model;

import java.math.BigDecimal;

public class TemporaryStoreModel {
	String iGN;
	BigDecimal temporaryAmount;
	String temporaryPIN;
	String temporaryStore;
	
	public TemporaryStoreModel(DatabaseUserModel dUM, BigDecimal temporaryAmount, String temporaryPIN, String temporaryStore) {
		this.iGN = dUM.getiGN();
		this.temporaryAmount = temporaryAmount;
		this.temporaryPIN = temporaryPIN;
		this.temporaryStore = temporaryStore;
	}

	public String getiGN() {
		return iGN;
	}

	public BigDecimal getTemporaryAmount() {
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

	public void setTemporaryAmount(BigDecimal temporaryAmount) {
		this.temporaryAmount = temporaryAmount;
	}

	public void setTemporaryPIN(String temporaryPIN) {
		this.temporaryPIN = temporaryPIN;
	}

	public void setTemporaryStore(String temporaryStore) {
		this.temporaryStore = temporaryStore;
	}
}
