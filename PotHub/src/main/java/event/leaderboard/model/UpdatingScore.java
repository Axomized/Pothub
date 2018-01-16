package event.leaderboard.model;

import java.math.BigDecimal;

public class UpdatingScore {
    private String userTo;
    private BigDecimal score;

    public UpdatingScore() {
    }

	public UpdatingScore(String userTo, BigDecimal score) {
		this.userTo = userTo;
		this.score = score;
	}

	public String getUserTo() {
		return userTo;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
}
