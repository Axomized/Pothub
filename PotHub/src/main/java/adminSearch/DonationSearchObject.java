package adminSearch;

import java.math.BigDecimal;
import java.sql.Date;

public class DonationSearchObject implements SearchObject{
	//SQL Between
	private Date donationDateOpen;
	private BigDecimal donationAmountOpen;
	private String onBehalf;
	
	//SQL And
	private Date donationDateClose;
	private BigDecimal donationAmountClose;
	
	//SQL Like
	private String iGN;
	
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
				"SELECT TOP "+MAXRETURNS+" donationID, IGN, donation_date, donation_amount, onBehalf "
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

	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = SearchSanitizer.sanitise(iGN);
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
		this.onBehalf = SearchSanitizer.sanitise(onBehalf);
	}
}
