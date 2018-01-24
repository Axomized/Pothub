package database.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DonationModel {
	private int donationID;
	private String iGN;
	private Timestamp donationDate;
	private BigDecimal donationAmount;
	private String onBehalf;
	
	public DonationModel() {
		
	}

	public DonationModel(int donationID, String iGN, Timestamp donationDate, BigDecimal donationAmount, String onBehalf) {
		this.donationID = donationID;
		this.iGN = iGN;
		this.donationDate = donationDate;
		this.donationAmount = donationAmount;
		this.onBehalf = onBehalf;
	}
	
	public DonationModel(Timestamp donationDate, BigDecimal donationAmount, String onBehalf) {
		this.donationDate = donationDate;
		this.donationAmount = donationAmount;
		this.onBehalf = onBehalf;
	}

	public int getDonationID() {
		return donationID;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getDonationDate() {
		return donationDate;
	}

	public BigDecimal getDonationAmount() {
		return donationAmount;
	}

	public void setDonationID(int donationID) {
		this.donationID = donationID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDonationDate(Timestamp donationDate) {
		this.donationDate = donationDate;
	}

	public void setDonationAmount(BigDecimal donationAmount) {
		this.donationAmount = donationAmount;
	}

	public String getOnBehalf() {
		return onBehalf;
	}

	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}
	
	public String converTimestamp(Timestamp timestamp) {
		LocalDateTime datetime = timestamp.toLocalDateTime();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		String dateString = datetime.format(format);
		return dateString;
	}
}
