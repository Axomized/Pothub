package adminSearch;

import java.sql.Date;

public class BansSearchObject implements SearchObject{
	//SQL Between
	private Date startDateOpen;
	private Date endDateOpen;
	
	//SQL And
	private Date startDateClose;
	private Date endDateClose;
	
	//SQL Like
	private String iGN;
	private String reason;
	private String admin;
	
	//SQL Is
	private int pardoned;
	
	public BansSearchObject(){
		long maxTime = (long)21459168 * (long)1000000;
		
		long currentTime = System.currentTimeMillis();
		this.startDateOpen = new Date(0);
		this.startDateClose = new Date(currentTime);
		this.endDateOpen = new Date(0);
		this.endDateClose = new Date(maxTime);
		this.pardoned = 2;
	}

	@Override
	public String getExecutableSQL() {
		String queryToBuild=
				"SELECT TOP "+MAXRETURNS+" IGN, startDate, endDate, reason, admin, pardoned "
				+ "FROM Bans ";
		
		queryToBuild +=
		"WHERE (startDate BETWEEN '"
				
		+this.startDateOpen.toString()
		+"' AND '"
		+this.startDateClose.toString()
		+"') AND (endDate BETWEEN '"
		
		+this.endDateOpen.toString()
		+"' AND '"
		+this.endDateClose.toString()
		+"') ";
		
		if(iGN!=null && iGN.length()>0){
			queryToBuild += " AND IGN like '%" + iGN + "%'";
		}
		
		if(admin!=null && admin.length()>0 ){
			queryToBuild += " AND admin like '%" + admin + "%'";
		}
		
		if(reason!=null && reason.length()>0){
			queryToBuild += " AND reason like '%" + reason + "%'";
		}
		
		if(pardoned==1){
			queryToBuild += " AND pardoned = 'true'";
		}
		else if(pardoned==0){
			queryToBuild += " AND pardoned = 'false'";
		}
		
		queryToBuild+=";";
		
		System.out.println(queryToBuild);
		return queryToBuild;
	}

	public Date getStartDateOpen() {
		return startDateOpen;
	}

	public void setStartDateOpen(Date startDateOpen) {
		this.startDateOpen = startDateOpen;
	}
	
	public Date getStartDateClose() {
		return startDateClose;
	}
	
	public void setStartDateClose(Date startDateClose) {
		this.startDateClose = startDateClose;
	}

	public Date getEndDateClose() {
		return endDateClose;
	}

	public Date getEndDateOpen() {
		return endDateOpen;
	}

	public void setEndDateOpen(Date endDateOpen) {
		this.endDateOpen = endDateOpen;
	}

	public void setEndDateClose(Date endDateClose) {
		this.endDateClose = endDateClose;
	}

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = SearchSanitizer.sanitise(iGN);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = SearchSanitizer.sanitise(reason);
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = SearchSanitizer.sanitise(admin);
	}

	public int isPardoned() {
		return pardoned;
	}

	public void setPardoned(int pardoned) {
		this.pardoned = pardoned;
	}

}
