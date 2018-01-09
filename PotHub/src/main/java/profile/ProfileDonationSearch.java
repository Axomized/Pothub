package profile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProfileDonationSearch {
	private String onBehalf;
	private BigDecimal donation_Amount;
	private String dateInput;
	private String afterDate;
	private String beforeDate;
	
	public String getOnBehalf() {
		return onBehalf;
	}

	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}

	public BigDecimal getDonation_Amount() {
		return donation_Amount;
	}

	public void setDonation_Amount(BigDecimal donation_Amount) {
		this.donation_Amount = donation_Amount;
	}

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

	private Timestamp selectToTimestamp(String selectString) {
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timestamp = null;
		if (selectString.equals("Yesterday")) {
			timestamp = Timestamp.valueOf(localDateTime.minusDays(1));
		}
		else if (selectString.equals("Last 7 days")) {
			timestamp = Timestamp.valueOf(localDateTime.minusDays(7));
		}
		else if (selectString.equals("Last 30 days")) {
			timestamp = Timestamp.valueOf(localDateTime.minusDays(30));
		}
		else if (selectString.equals("Last 90 days")) {
			timestamp = Timestamp.valueOf(localDateTime.minusDays(90));
		}
		return timestamp;
	}
	
	private Timestamp stringToTimestamp(String dateString) {
		LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
		return timestamp;
	}
	
	public String getSearchQuery() {
		String searchQuery = "SELECT Donation_Date, Donation_Amount, OnBehalf FROM Donation WHERE IGN = ?";
		
		if (donation_Amount != null && !(donation_Amount.compareTo(BigDecimal.ZERO) == 0)) {
			searchQuery += " AND Donation_Amount = '" + donation_Amount + "'";
		}
		if (onBehalf != null && !onBehalf.isEmpty()) {
			searchQuery += " AND OnBehalf = '" + onBehalf + "'";
		}
		if (dateInput != null && !dateInput.isEmpty()) {
			if (dateInput.equals("Custom")) {
				if ((afterDate != null && !afterDate.isEmpty()) && (beforeDate != null && !beforeDate.isEmpty())) {
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
		
		searchQuery += ";";
		System.out.println(searchQuery);
		
		return searchQuery;
	}

	public static void main(String[] args) {
		
	}

}
