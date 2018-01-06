package logs;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogsSearch {
	private String iGN;
	private String logType;
	private String suspicion;
	private String dateInput;
	private String afterDate;
	private String beforeDate;
	private boolean userSearch = false;

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getSuspicion() {
		return suspicion;
	}

	public void setSuspicion(String suspicion) {
		this.suspicion = suspicion;
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
	
	private boolean checkIfSearch(String iGN, String logType, String suspicion, String dateInput) {
		if ((iGN != null && !iGN.isEmpty()) || (logType != null && !logType.isEmpty()) || (suspicion != null && !suspicion.isEmpty()) 
				|| (dateInput != null && !dateInput.isEmpty())) {
			userSearch = true;
		}
		return userSearch;
	}
	
	public String getSearchQuery() {
		String searchQuery = "SELECT IGN, LogDate, IPAddress, LogType, LogActivity, IsSuspicious FROM Logs";
		
		if (checkIfSearch(iGN, logType, suspicion, dateInput)) {
			searchQuery += " WHERE";
			
			if (iGN != null && !iGN.isEmpty()) {
				searchQuery += " IGN = '" + iGN + "' AND";
			}
			if ((logType != null && !logType.isEmpty()) && !logType.equals("All")) {
				searchQuery += " LogType = '" + logType + "' AND";
			}
			if ((suspicion != null && !suspicion.isEmpty()) && !suspicion.equals("All")) {
				if (suspicion.equals("Yes")) {
					searchQuery += " IsSuspicious = '1' AND";
				}
				else if (suspicion.equals("No")) {
					searchQuery += " IsSuspicious = '0' AND";
				}
			}
			if (dateInput != null && !dateInput.isEmpty()) {
				if (dateInput.equals("Custom")) {
					if ((afterDate != null && !afterDate.isEmpty()) && (beforeDate != null && !beforeDate.isEmpty())) {
						Timestamp afterDateTmp = stringToTimestamp(afterDate);
						Timestamp beforeDateTmp = stringToTimestamp(beforeDate);
						searchQuery += " LogDate > DATEADD(day, 1, '" + afterDateTmp + "') AND LogDate < '" + beforeDateTmp + "' AND"; 
					}
				}
				else {
					Timestamp timestamp = selectToTimestamp(dateInput);
					if (timestamp != null) {
						searchQuery += " LogDate > '" + timestamp + "' AND";
					}
				}
			}
		}
		
		if (searchQuery.substring(searchQuery.length() -4).equals(" AND")) {
			searchQuery = searchQuery.substring(0, searchQuery.length() - 4);
		}
		else if (searchQuery.substring(searchQuery.length() -6).equals(" WHERE")) {
			searchQuery = searchQuery.substring(0, searchQuery.length() - 6);
		}
		
		searchQuery += ";";
		System.out.println(searchQuery);
		
		return searchQuery;
	}

	public static void main(String[] args) {
		
	}

}
