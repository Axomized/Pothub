package database.model;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TemporaryStoreModel {
	String iGN;
	BigDecimal temporaryAmount;
	String temporaryPIN;
	String temporarySalt;
	String temporaryOnBehalf;
	Timestamp temporaryTime;
	
	public TemporaryStoreModel(String iGN, BigDecimal temporaryAmount, String temporaryPIN, String temporarySalt, String temporaryOnBehalf, Timestamp temporaryTime) {
		this.iGN = iGN;
		this.temporaryAmount = temporaryAmount;
		this.temporarySalt = temporarySalt;
		this.temporaryPIN = temporaryPIN;
		this.temporaryOnBehalf = temporaryOnBehalf;
		this.temporaryTime = temporaryTime;
	}

	public TemporaryStoreModel() {
		
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

	public String getTemporarySalt() {
		return temporarySalt;
	}

	public void setTemporarySalt(String temporarySalt) {
		this.temporarySalt = temporarySalt;
	}

	public String getTemporaryOnBehalf() {
		return temporaryOnBehalf;
	}
	
	public Timestamp getTemporaryTime() {
		return temporaryTime;
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

	public void setTemporaryTime(Timestamp temporaryTime) {
		this.temporaryTime = temporaryTime;
	}
	
	public String generatePIN() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(1000000);
		return String.valueOf(num);
	}
	
	public Timestamp getTime5MinsLater() {
		Instant instant = Instant.now().plusSeconds(TimeUnit.MINUTES.toSeconds(5));
		Timestamp timestamp = Timestamp.from(instant);
		return timestamp;
	}
}