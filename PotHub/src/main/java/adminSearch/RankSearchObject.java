package adminSearch;

import java.sql.Date;

public class RankSearchObject implements SearchObject{
	
	private int searchStart;
	private int searchEnd;
	
	//SQL Between
	private int permissionLevel=-1;
	private String iGN;
	private Date joinDateOpen;
	private Date joinDateClose;
	
	//SQL And
	
	public RankSearchObject(){
		long maxTime = (long)21459168 * (long)1000000;

		joinDateOpen = new Date(0);
		joinDateClose = new Date(maxTime);
	}

	@Override
	public String getExecutableSQL() {
		String queryToBuild=
				"SELECT TOP "+MAXRETURNS+" IGN, joinDate, userPermission  "
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

		if(permissionLevel!=-1){
		queryToBuild += " AND userPermission=" + permissionLevel;
		}
		
		queryToBuild+=" ORDER BY IGN;";
		System.out.println(queryToBuild);
		return queryToBuild;
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

	public Date getJoinDateOpen() {
		return joinDateOpen;
	}

	public void setJoinDateOpen(Date donationDateOpen) {
		this.joinDateOpen = donationDateOpen;
	}
	
	public Date getJoinDateClose() {
		return joinDateClose;
	}

	public void setJoinDateClose(Date donationDateOpen) {
		this.joinDateClose = donationDateOpen;
	}
	
	public int getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
}
