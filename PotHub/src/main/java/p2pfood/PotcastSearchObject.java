package p2pfood;

import java.sql.Timestamp;

import adminSearch.SearchSanitizer;

public class PotcastSearchObject{
	private String title;
	private String orderBy;
	private boolean ascDesc;
	private int purpose;
	private String ign;
	private Timestamp rightNow;
	
	public PotcastSearchObject(){
		this.setTitle("");
		this.setOrderBy("bidStopTime");
		this.setAscDesc(false);
		this.purpose=0;
		this.rightNow = new Timestamp(System.currentTimeMillis());
	}
	
	public String getExecutableSQL(){
		if(this.purpose==0){
		String toRet ="SELECT TOP 250 iGN, potcastID, title, description, maxBids, bidStopTime,"
			+ "pickupTime, minBid, startingCR, picture FROM Potcast"
			+ " WHERE";
		
		if(this.title.length()>0){
			toRet+=" title LIKE '%"+this.title+"%' AND";
		}
			toRet+=" bidStopTime > '"+rightNow+"'";
		if(this.orderBy.length()>0){
			toRet+=" ORDER BY "+this.orderBy+" ";
			
			if(isAscDesc()){
				toRet+=" ASC;";
			}
			else{
				toRet+=" DESC;";
			}
		}
		
		System.out.println(toRet);
		return toRet;
		}
		
		if(this.purpose==1){
			System.out.println(purpose);
			String toRet ="SELECT TOP 250 iGN, potcastID, title, description, maxBids, bidStopTime,"
					+"pickupTime, minBid, startingCR, picture FROM Potcast";
				
				if(this.title.length()>0){
					toRet+=" WHERE title LIKE '%"+this.title+"%' AND IGN='"+ign+"' AND bidStopTime > '"+rightNow+"'";
				}
				else{
					toRet+=" WHERE IGN='"+ign+"' AND bidStopTime > '"+rightNow+"'";
				}
				if(this.orderBy.length()>0){
					toRet+=" ORDER BY "+this.orderBy+" ";
					
					if(isAscDesc()){
						toRet+=" ASC;";
					}
					else{
						toRet+=" DESC;";
					}
				}
				System.out.println(toRet);
				return toRet;
		}
		
		if(this.purpose==2){
			String toRet ="SELECT TOP 250 a.iGN, a.potcastID, a.title, a.description, a.maxBids, a.bidStopTime,"
			+"a.pickupTime, a.minBid, a.startingCR, a.picture"
			+" FROM Potcast a INNER JOIN PotcastBid b ON a.potcastID = b.potcastID";
			
			if(this.title.length()>0){
				toRet+=" WHERE title LIKE '%"+this.title+"%' AND IGN='"+ign+"' AND bidStopTime > '"+rightNow+"'";
			}
			else{
				toRet+=" WHERE IGN='"+ign+"' AND bidStopTime > '"+rightNow+"'";
			}
			
			toRet+=" GROUP BY a.iGN, a.potcastID, a.title, a.description, a.maxBids, a.bidStopTime,"
			+"a.pickupTime, a.minBid, a.startingCR, a.picture"
			+ " ORDER BY bidStopTime, COUNT(b.potcastID)";
			
			if(isAscDesc()){
				toRet+=" ASC;";
			}
			else{
				toRet+=" DESC;";
			}
			System.out.println(toRet);
			return toRet;
		}
		
		if(this.purpose==3){
			return "SELECT TOP 3 * FROM Potcast WHERE bidStopTime > '"+rightNow+"' ORDER BY bidStopTime DESC";
		}
		
		if(this.purpose==4){
			String toRet ="SELECT TOP 250 a.iGN, a.potcastID, a.title, a.description, a.maxBids, a.bidStopTime,"
			+ "a.pickupTime, a.minBid, a.startingCR, a.picture"
			+ " FROM Potcast a INNER JOIN PotcastBid b ON a.potcastID = b.potcastID"
			+ " WHERE";
			
			if(this.title.length()>0){
				toRet+=" title LIKE '%"+this.title+"%' AND bidStopTime > '"+rightNow+"'";
			}
			else{
				toRet+=" bidStopTime > '"+rightNow+"'";
			}
			toRet+=" GROUP BY a.iGN, a.potcastID, a.title, a.description, a.maxBids, a.bidStopTime,"
			+"a.pickupTime, a.minBid, a.startingCR, a.picture"
			+ " ORDER BY bidStopTime, COUNT(b.potcastID)";
			
			if(isAscDesc()){
				toRet+=" ASC;";
			}
			else{
				toRet+=" DESC;";
			}
			System.out.println(toRet);
			return toRet;
		}
		
		return "";
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String creator) {
		this.title = SearchSanitizer.sanitise(creator);
	}


	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = SearchSanitizer.sanitise(title);
	}

	public boolean isAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(boolean ascDesc) {
		this.ascDesc = ascDesc;
	}

	public int getPurpose() {
		return purpose;
	}

	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}

	public String getIgn() {
		return ign;
	}

	public void setIgn(String ign) {
		this.ign = ign;
	}
	
}