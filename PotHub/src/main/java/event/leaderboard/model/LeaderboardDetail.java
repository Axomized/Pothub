package event.leaderboard.model;

public class LeaderboardDetail {
	private String iGN;
	private String title;
	private String desc;
	private byte[] foodpic;
	
	public LeaderboardDetail(String iGN, String title, String desc, byte[] foodpic) {
		this.iGN = iGN;
		this.title = title;
		this.desc = desc;
		this.foodpic = foodpic;
	}

	public LeaderboardDetail() {
	}
	
	public String getiGN() {
		return iGN;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}

	public byte[] getFoodpic() {
		return foodpic;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setFoodpic(byte[] foodpic) {
		this.foodpic = foodpic;
	}
}
