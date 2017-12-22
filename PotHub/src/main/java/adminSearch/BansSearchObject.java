package adminSearch;

import java.sql.Date;

public class BansSearchObject implements SearchObject{
	//SQL Between
	private int searchStart = 0;
	
	private Date startDateOpen;
	private Date endDateOpen;
	
	//SQL And
	private int searchEnd = 19;
	
	private Date startDateClose;
	private Date endDateClose;
	
	//SQL Like
	private String iGN;
	private String reason;
	private String admin;
	
	//SQL Is
	private boolean pardoned;
	
	public BansSearchObject(){
		long maxTime = (long)21459168 * (long)1000000;
		
		long currentTime = System.currentTimeMillis();
		this.startDateOpen = new Date(0);
		this.startDateClose = new Date(currentTime);
		this.endDateOpen = new Date(0);
		this.endDateClose = new Date(maxTime);
	}

	@Override
	public String getExecutableSQL() {
		String queryToBuild=
				"SELECT IGN, startDate, endDate, reason, admin, pardoned "
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
		
		try{
		if(pardoned){
			queryToBuild += " AND pardoned = true";
		}
		else{
			queryToBuild += " AND pardoned = false";
		}
		}catch(NullPointerException n){
			
		}
		queryToBuild+=";";
		
		System.out.println(queryToBuild);
		return queryToBuild;
	}

	@Override
	public void setLimits(int start, int end) {
		this.searchStart=start;
		this.searchEnd=end;
	}

	public int getSearchStart() {
		return searchStart;
	}

	public void setSearchStart(int searchStart) {
		this.searchStart = searchStart;
	}

	public Date getStartDateOpen() {
		return startDateOpen;
	}

	public void setStartDateOpen(Date startDateOpen) {
		this.startDateOpen = startDateOpen;
	}

	public Date getEndDateOpen() {
		return endDateOpen;
	}

	public void setEndDateOpen(Date endDateOpen) {
		this.endDateOpen = endDateOpen;
	}

	public int getSearchEnd() {
		return searchEnd;
	}

	public void setSearchEnd(int searchEnd) {
		this.searchEnd = searchEnd;
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

	public boolean isPardoned() {
		return pardoned;
	}

	public void setPardoned(boolean pardoned) {
		this.pardoned = pardoned;
	}

}
