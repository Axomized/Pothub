package event.leaderboard.model;

public class LeaderboardDetail {
	String title;
	String desc;
	byte[] foodpic;
	
	public LeaderboardDetail(String title, String desc, byte[] foodpic) {
		this.title = title;
		this.desc = desc;
		this.foodpic = foodpic;
	}

	public LeaderboardDetail() {
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
