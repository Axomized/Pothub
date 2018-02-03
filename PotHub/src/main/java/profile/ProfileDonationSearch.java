package profile;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileDonationSearch {
	private String onBehalf;
	private BigDecimal donationAmount;
	private String dateInput;
	private String afterDate;
	private String beforeDate;
	
	public String getOnBehalf() {
		return onBehalf;
	}

	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}

	public BigDecimal getDonationAmount() {
		return donationAmount;
	}

	public void setDonationAmount(BigDecimal donationAmount) {
		this.donationAmount = donationAmount;
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
		LocalDate localDate = LocalDate.now();
		Timestamp timestamp = null;
		if (selectString.equals("Yesterday")) {
			timestamp = Timestamp.valueOf(localDate.atStartOfDay().minusDays(1));
		}
		else if (selectString.equals("Last 7 days")) {
			timestamp = Timestamp.valueOf(localDate.atStartOfDay().minusDays(7));
		}
		else if (selectString.equals("Last 30 days")) {
			timestamp = Timestamp.valueOf(localDate.atStartOfDay().minusDays(30));
		}
		else if (selectString.equals("Last 90 days")) {
			timestamp = Timestamp.valueOf(localDate.atStartOfDay().minusDays(90));
		}
		return timestamp;
	}
	
	private Timestamp getCurrentDateTime() {
		Timestamp timestamp = Timestamp.from(Instant.now());
		return timestamp;
	}
	
	private Timestamp stringToTimestamp(String dateString) {
		LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
		return timestamp;
	}
	
	public String getSearchQuery() {
		String searchQuery = "SELECT DonationDate, DonationAmount, OnBehalf FROM Donation WHERE IGN = ?";
		
		if (donationAmount != null && !(donationAmount.compareTo(BigDecimal.ZERO) == 0)) {
			searchQuery += " AND DonationAmount = '" + donationAmount + "'";
		}
		if (onBehalf != null && !onBehalf.isEmpty()) {
			searchQuery += " AND OnBehalf = '" + onBehalf + "'";
		}
		if (dateInput != null && !dateInput.isEmpty()) {
			if (dateInput.equals("Custom")) {
				if ((afterDate != null && !afterDate.isEmpty()) && (beforeDate != null && !beforeDate.isEmpty())) {
					Timestamp afterDateTmp = stringToTimestamp(afterDate);
					Timestamp beforeDateTmp = stringToTimestamp(beforeDate);
					searchQuery += " AND DonationDate > DATEADD(day, 1, '" + afterDateTmp + "') AND DonationDate < '" + beforeDateTmp + "'";
				}
			}
			else {
				Timestamp timestamp = selectToTimestamp(dateInput);
				if (timestamp != null) {
					Timestamp currentDateTime = getCurrentDateTime();
					searchQuery += " AND DonationDate > '" + timestamp + "' AND DonationDate < '" + currentDateTime + "'";
				}
			}
		}
		
		searchQuery += " ORDER BY DonationID DESC;";
		System.out.println(searchQuery);
		
		return searchQuery;
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException {
		
	}
}
