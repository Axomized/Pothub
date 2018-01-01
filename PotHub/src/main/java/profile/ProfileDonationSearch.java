package profile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileDonationSearch {
	private String dateInput;
	private String afterDate;
	private String beforeDate;
	private BigDecimal donation_Amount;
	private String onBehalf;

	public String getDateInput() {
		return dateInput;
	}

	public void setDateInput(String dateInput) {
		this.dateInput = dateInput;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public String getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
	}

	public BigDecimal getDonation_Amount() {
		return donation_Amount;
	}

	public void setDonation_Amount(BigDecimal donation_Amount) {
		this.donation_Amount = donation_Amount;
	}

	public String getOnBehalf() {
		return onBehalf;
	}

	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}
	
	public Timestamp selectToTimestamp(String selectString) {
		Calendar calendar = Calendar.getInstance();
		Timestamp timestamp = null;
		if (selectString.equals("Yesterday")) {
			calendar.add(Calendar.DATE, -1);
			timestamp = new Timestamp(calendar.getTime().getTime());
		}
		else if (selectString.equals("Last week")) {
			calendar.add(Calendar.DATE, -7);
			timestamp = new Timestamp(calendar.getTime().getTime());
		}
		else if (selectString.equals("Last month")) {
			calendar.add(Calendar.DATE, -30);
			timestamp = new Timestamp(calendar.getTime().getTime());
		}
		return timestamp;
	}
	
	public Timestamp stringToTimestamp(String dateString) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
	
	public String getSearchQuery(String name) throws ParseException {
		String searchQuery = "SELECT Donation_Date, Donation_Amount, OnBehalf FROM Donation WHERE IGN = ?";
		
		if (!dateInput.isEmpty() && dateInput != null) {
			if (dateInput.equals("Custom")) {
				if ((!afterDate.isEmpty() && afterDate != null) && (!beforeDate.isEmpty() && beforeDate != null)) {
					Timestamp afterDateTmp = stringToTimestamp(afterDate);
					Timestamp beforeDateTmp = stringToTimestamp(beforeDate);
					searchQuery += " AND Donation_Date > " + afterDateTmp + " AND Donation_Date < " + beforeDateTmp;
				}
			}
			else {
				Timestamp timestamp = selectToTimestamp(dateInput);
				if (timestamp != null) {
					searchQuery += " AND Donation_Date > " + timestamp;
				}
				else {
					System.out.println("ProfileDonationSearch - (Else: Line 98)");
				}
			}
		}
		else if (!(donation_Amount.compareTo(BigDecimal.ZERO) == 0)) {
			searchQuery += " AND Donation_Amount = " + donation_Amount;
		}
		else if (!onBehalf.isEmpty() && onBehalf != null) {
			searchQuery += " AND OnBehalf = " + onBehalf;
		}
		searchQuery += ";";
		
		return searchQuery;
	}

	public static void main(String[] args) {
		
	}

}
