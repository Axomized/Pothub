package adminSearch;

import java.sql.Date;

public class RankSearchObject implements SearchObject{
	
	private int searchStart;
	private int searchEnd;
	
	//SQL Between
	private int permissionLevel;
	private String iGN;
	private Date joinDateOpen;
	private Date joinDateClose;
	
	//SQL And
	
	public RankSearchObject(){
		long maxTime = (long)21459168 * (long)1000000;
		
		permissionLevel = 0;
		searchStart = 0;
		searchEnd = 19;
		joinDateOpen = new Date(0);
		joinDateClose = new Date(maxTime);
	}

	@Override
	public String getExecutableSQL() {
		String queryToBuild=
				"SELECT TOP "+searchEnd+" IGN, joinDate, userPermission  "
				+"FROM DatabaseUser ";
		
		queryToBuild +=
		"WHERE (joinDate BETWEEN '"
				
		+this.joinDateOpen.toString()
		+"' AND '"
		+this.joinDateClose.toString()
		+"') ";
		
		if(iGN!=null && iGN.length()>0){
			queryToBuild += " AND IGN like '%" + iGN + "%'";
		}
		
		queryToBuild += " AND userPermission=" + permissionLevel;
		
		queryToBuild+=";";
		
		System.out.println(queryToBuild);
		return queryToBuild;
	}

	@Override
	public void setLimits(int start, int end) {
		if(start<0||start>Integer.MAX_VALUE||end<0||end>Integer.MAX_VALUE){
			this.searchStart=start;
			this.searchEnd=end;
		}
		this.searchStart=start;
		this.searchEnd=end;
	}

	public int getSearchStart() {
		return searchStart;
	}

	public void setSearchStart(int searchStart) {
		this.searchStart = searchStart;
	}

	public int getSearchEnd() {
		return searchEnd;
	}

	public void setSearchEnd(int searchEnd) {
		this.searchEnd = searchEnd;
	}

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = SearchSanitizer.sanitise(iGN);
	}

	public Date getDonationDateOpen() {
		return joinDateOpen;
	}

	public void setDonationDateOpen(Date donationDateOpen) {
		this.joinDateOpen = donationDateOpen;
	}

	public Date getDonationDateClose() {
		return joinDateClose;
	}

	public void setDonationDateClose(Date donationDateClose) {
		this.joinDateClose = donationDateClose;
	}
}
