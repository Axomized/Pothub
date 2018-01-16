package event.leaderboard.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class UserScore {
	private int submitID;
    private String userID;
    private String userFrom;
    private String userTo;
    private BigDecimal score;
    private String topic;
    public static Lock lock = new Lock();
    public static int submitIDStatic = 1;
    private static Map<String, BigDecimal> userTotalScore = new TreeMap<String, BigDecimal>(); // Current score of people
    private static Map<String, BigDecimal> userTotalNumber = new TreeMap<String, BigDecimal>(); // Number of people submitted
    public static ArrayList<UserScore> userScoreArray = new ArrayList<UserScore>(); // History

    //UserScore a = new UserScore(UserScore.submitIDStatic, "1", "UserA", "UserB", 8.4, "Best Chili Crab");
	public UserScore(int submitID, String userID, String userFrom, String userTo, BigDecimal score, String topic) throws InterruptedException { 
		this.submitID = submitID;
		this.userID = userID;
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.score = score;
		this.topic = topic;
		
		lock.lockWrite(); // Locking Write
		
		submitIDStatic++;
		userScoreArray.add(this);
		
		if (userTotalScore.containsKey(userTo) ) {
			BigDecimal sum = userTotalScore.get(userTo).add(score);
		    userTotalScore.put(userTo, sum);
		}
		else {
			userTotalScore.put(userTo, score);
		}
		
		if (userTotalNumber.containsKey(userTo) ) {
			BigDecimal sum = userTotalNumber.get(userTo).add(new BigDecimal("1"));
		    userTotalNumber.put(userTo, sum);
		}
		else {
			userTotalNumber.put(userTo, new BigDecimal("1"));
		}
		
		lock.unlockWrite(); // Unlocking Write
	}

	public int getSubmitID() {
		return submitID;
	}
	
	public void setSubmitID(int submitID) {
		this.submitID = submitID;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public String getUserTo() {
		return userTo;
	}

	public BigDecimal getScore() {
		return score;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public static ArrayList<UpdatingScore> getUserScore() throws InterruptedException {
		ArrayList<UpdatingScore> alups = new ArrayList<UpdatingScore>();
		
		lock.lockRead();
		for (Entry<String, BigDecimal> entry : userTotalScore.entrySet()) {
			
			for (Map.Entry<String, BigDecimal> entry2 : userTotalNumber.entrySet()) {
				String userTo = entry.getKey();
				if(entry2.getKey() == userTo) {
					BigDecimal score = entry.getValue().divide(entry2.getValue(), 2, BigDecimal.ROUND_HALF_UP);
					alups.add(new UpdatingScore(userTo, score));
					break;
				}
			}
		}
		lock.unlockRead();
		
		return alups;
	}
	
	public static void displayHistory() throws InterruptedException {
		lock.lockRead(); // Locking Read
		
		for(UserScore us: userScoreArray) {
			System.out.println(us.toString());
		}
		
		lock.unlockRead(); //Unlocking Read
	}
	
	public String toString() {
		String s = "SubmitID: " + submitID + "\nUser From: " + userFrom + "\nUserTo: " + userTo + "\nScore: " + score + "\nTopic: " + topic + "\n";
		return s;
	}
}
