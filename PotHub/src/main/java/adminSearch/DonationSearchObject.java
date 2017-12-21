package adminSearch;

import java.math.BigDecimal;
import java.sql.Date;

public class DonationSearchObject implements SearchObject{
	
	private int searchStart;
	private int searchEnd;
	
	//SQL Between
	private int donationID;
	private String iGN;
	private Date donationDateOpen;
	private Date donationDateClose;
	private BigDecimal donationAmountOpen;
	private BigDecimal donationAmountClose;
	private String onBehalf;
	
	//SQL Is
	private boolean pardoned;
	
	public DonationSearchObject(){
		long maxTime = (long)21459168 * (long)1000000;
		
		donationDateOpen = new Date(0);
		donationDateClose = new Date(maxTime);
		donationAmountOpen = BigDecimal.ZERO;
		donationAmountClose = BigDecimal.valueOf(2001);
	}

	@Override
	public String getExecutableSQL() {
		String queryToBuild=
				"SELECT donationID, IGN, donation_date, donation_amount, onBehalf "
				+"FROM Donation ";
		
		queryToBuild +=
		"WHERE (donation_date BETWEEN '"
				
		+this.donationDateOpen.toString()
		+"' AND '"
		+this.donationDateClose.toString()
		+"') AND (donation_amount BETWEEN "
		+this.donationAmountOpen
		+" AND "
		+this.donationAmountClose
		+")";
		
		if(iGN!=null && iGN.length()>0){
			queryToBuild += " AND IGN like '%" + iGN + "%'";
		}
		
		if(onBehalf!=null && onBehalf.length()>0 ){
			queryToBuild += " AND admin like '%" + onBehalf + "%'";
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

	public int getSearchEnd() {
		return searchEnd;
	}

	public void setSearchEnd(int searchEnd) {
		this.searchEnd = searchEnd;
	}

	public int getDonationID() {
		return donationID;
	}

	public void setDonationID(int donationID) {
		this.donationID = donationID;
	}

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public Date getDonationDateOpen() {
		return donationDateOpen;
	}

	public void setDonationDateOpen(Date donationDateOpen) {
		this.donationDateOpen = donationDateOpen;
	}

	public Date getDonationDateClose() {
		return donationDateClose;
	}

	public void setDonationDateClose(Date donationDateClose) {
		this.donationDateClose = donationDateClose;
	}

	public BigDecimal getDonationAmountOpen() {
		return donationAmountOpen;
	}

	public void setDonationAmountOpen(BigDecimal donationAmountOpen) {
		this.donationAmountOpen = donationAmountOpen;
	}

	public BigDecimal getDonationAmountClose() {
		return donationAmountClose;
	}

	public void setDonationAmountClose(BigDecimal donationAmountClose) {
		this.donationAmountClose = donationAmountClose;
	}

	public String getOnBehalf() {
		return onBehalf;
	}

	public void setOnBehalf(String onBehalf) {
		this.onBehalf = onBehalf;
	}

	public boolean isPardoned() {
		return pardoned;
	}

	public void setPardoned(boolean pardoned) {
		this.pardoned = pardoned;
	}

}
