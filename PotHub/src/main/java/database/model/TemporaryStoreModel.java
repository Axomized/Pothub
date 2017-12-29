package database.model;

import java.math.BigDecimal;

public class TemporaryStoreModel {
	String iGN;
	BigDecimal temporaryAmount;
	String temporaryPIN;
	String temporaryOnBehalf;
	
	public TemporaryStoreModel(String iGN, BigDecimal temporaryAmount, String temporaryPIN, String TemporaryOnBehalf) {
		this.iGN = iGN;
		this.temporaryAmount = temporaryAmount;
		this.temporaryPIN = temporaryPIN;
		this.temporaryOnBehalf = TemporaryOnBehalf;
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

	public String getTemporaryOnBehalf() {
		return temporaryOnBehalf;
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

	public void setTemporaryOnBehalf(String temporaryOnBehalf) {
		this.temporaryOnBehalf = temporaryOnBehalf;
	}
}