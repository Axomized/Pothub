package event.leaderboard;

public class UpdatingScore {
    private String userTo;
    private double score;

    public UpdatingScore() {
    }

	public UpdatingScore(String userTo, double score) {
		this.userTo = userTo;
		this.score = score;
	}

	public String getUserTo() {
		return userTo;
	}

	public double getScore() {
		return score;
	}

	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
