package database.model;

import java.sql.Timestamp;

public class DonationModel {
	int donationID;
	String iGN;
	Timestamp donation_Date;
	int donation_Amount;
	String onBehalf;

	public DonationModel(int donationID, DatabaseUserModel dUM, Timestamp donation_Date, int donation_Amount, String onBehalf) {
		this.donationID = donationID;
		this.iGN = dUM.getiGN();
		this.donation_Date = donation_Date;
		this.donation_Amount = donation_Amount;
		this.onBehalf = onBehalf;
	}

	public int getDonationID() {
		return donationID;
	}

	public String getiGN() {
		return iGN;
	}

	public Timestamp getDonation_Date() {
		return donation_Date;
	}

	public int getDonation_Amount() {
		return donation_Amount;
	}

	public void setDonationID(int donationID) {
		this.donationID = donationID;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setDonation_Date(Timestamp donation_Date) {
		this.donation_Date = donation_Date;
	}

	public void setDonation_Amount(int donation_Amount) {
		this.donation_Amount = donation_Amount;
	}

	public String getOnBehalf() {
		return onBehalf;
	}

	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}
}
