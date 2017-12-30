package adminSearch;

import java.sql.Date;

public class ReportSearchObject implements SearchObject{

	//SQL Like
	private String iGNSend;
	private String iGNReceive;
	private String evidenceType;
	
	//SQL Between
	private Date dateInOpen;
	private Date dateInClose;
	
	//SQL And
	private int guiltyOrNot;
	
	public ReportSearchObject(){
		long maxTime = (long)21459168 * (long)1000000;

		dateInOpen = new Date(0);
		dateInClose = new Date(maxTime);
		
		guiltyOrNot = -1;
	}

	@Override
	public String getExecutableSQL() {
		String queryToBuild=
				"SELECT TOP "+MAXRETURNS+" reportID, IGNSend, IGNReceive, evidenceType, Date, Evidence, reason, guiltyOrNot "
				+ "FROM Report ";
		
		queryToBuild +=
		"WHERE (date BETWEEN '"
				
		+this.dateInOpen.toString()
		+"' AND '"
		+this.dateInClose.toString()
		+"') ";
		
		if(iGNSend!=null && iGNSend.length()>0){
			queryToBuild += " AND IGNSend like '%" + iGNSend + "%'";
		}
		
		if(iGNReceive!=null && iGNReceive.length()>0){
			queryToBuild += " AND IGNReceive like '%" + iGNReceive + "%'";
		}

		if(evidenceType!=null && evidenceType.length()>0){
			queryToBuild += " AND evidenceType = '" + evidenceType + "'";
		}
		
		if(guiltyOrNot!=-1){
			queryToBuild += " AND guiltyOrNot = " + guiltyOrNot;
		}
		
		queryToBuild+=";";
		
		return queryToBuild;
	}

	public String getiGNSend() {
		return iGNSend;
	}

	public void setiGNSend(String iGNSend) {
		this.iGNSend = SearchSanitizer.sanitise(iGNSend);
	}

	public String getiGNReceive() {
		return iGNReceive;
	}

	public void setiGNReceive(String iGNReceive) {
		this.iGNReceive = SearchSanitizer.sanitise(iGNReceive);
	}

	public Date getDateInClose() {
		return dateInClose;
	}

	public void setDateInClose(Date dateInClose) {
		this.dateInClose = dateInClose;
	}
	
	public Date getDateInOpen() {
		return dateInClose;
	}

	public void setDateInOpen(Date dateInOpen) {
		this.dateInOpen = dateInOpen;
	}
	
	public int getGuiltyOrNot(){
		return this.guiltyOrNot;
	}
	
	public void setGuiltyOrNot(int guiltyOrNot){
		this.guiltyOrNot = guiltyOrNot;
	}
	
	public String getEvidenceType(){
		return this.evidenceType;
	}
	
	public void setEvidenceType(String evidenceType){
		this.evidenceType = evidenceType;
	}
}
