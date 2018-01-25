package database.model;

public class SubscriptionModel {
	private String IGN;
	private String subs;
	
	public String getIGN() {
		return IGN;
	}
	public void setIGN(String iGN) {
		IGN = iGN;
	}
	public SubscriptionModel() {
		super();
	}
	public SubscriptionModel(String iGN, String subs) {
		super();
		IGN = iGN;
		this.subs = subs;
	}
	public String getSubs() {
		return subs;
	}
	public void setSubs(String subs) {
		this.subs = subs;
	}
}
