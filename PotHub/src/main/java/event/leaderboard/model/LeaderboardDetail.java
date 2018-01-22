package event.leaderboard.model;

import java.math.BigDecimal;

public class LeaderboardDetail {
	private String iGN;
	private String title;
	private String desc;
	private BigDecimal totalScore;
	private int score;
	
	public LeaderboardDetail(String iGN, String title, String desc) {
		super();
		this.iGN = iGN;
		this.title = title;
		this.desc = desc;
	}

	public LeaderboardDetail(String iGN, String title, String desc, BigDecimal totalScore) {
		this.iGN = iGN;
		this.title = title;
		this.desc = desc;
		this.totalScore = totalScore;
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

	public BigDecimal getTotalScore() {
		return totalScore;
	}

	public int getScore() {
		return score;
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

	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
