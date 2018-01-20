package event.leaderboard.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class UserScore {
    private String userFrom;
    private String userTo;
    private BigDecimal score;
    public static Lock lock = new Lock();
    private static Map<String, BigDecimal> userTotalScore = new TreeMap<String, BigDecimal>(); // Current score of people
    private static Map<String, BigDecimal> userTotalNumber = new TreeMap<String, BigDecimal>(); // Number of people submitted
    private static Map<String, LeaderboardDetail> userDetails = new TreeMap<String, LeaderboardDetail>(); //User details that they enter themselves
    public static ArrayList<UserScore> userScoreArray = new ArrayList<UserScore>(); // History

    public UserScore() {
	}

	//UserScore a = new UserScore(UserScore.submitIDStatic, "1", "UserA", "UserB", 8.4, "Best Chili Crab");
	public UserScore(String userFrom, String userTo, BigDecimal score) throws InterruptedException { 
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.score = score;
		
		lock.lockWrite(); // Locking Write
		
		userScoreArray.add(this);
		
		if (userTotalScore.containsKey(userTo) ) {
			BigDecimal sum = userTotalScore.get(userTo).add(this.score);
		    userTotalScore.put(userTo, sum);
		}
		else {
			userTotalScore.put(userTo, this.score);
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

	public String getUserFrom() {
		return userFrom;
	}

	public String getUserTo() {
		return userTo;
	}

	public BigDecimal getScore() {
		return score;
	}
	
	public static ArrayList<UpdatingScore> getUserScore() throws InterruptedException {
		ArrayList<UpdatingScore> alups = new ArrayList<UpdatingScore>();
		
		lock.lockRead();
		if (userTotalScore.isEmpty()){
			return alups;
		}
		for (Entry<String, BigDecimal> entry : userTotalScore.entrySet()) {
			
			for (Map.Entry<String, BigDecimal> entry2 : userTotalNumber.entrySet()) {
				String userTo = entry.getKey();
				if(entry2.getKey() == userTo) {
					if(!entry2.getValue().toString().equals("0")) {
						BigDecimal score = entry.getValue().divide(entry2.getValue(), 2, BigDecimal.ROUND_HALF_UP);
						alups.add(new UpdatingScore(userTo, score));
					}else {
						alups.add(new UpdatingScore(userTo, entry2.getValue()));
					}
					break;
				}
			}
		}
		lock.unlockRead();
		
		return alups;
	}
	
	public static void registerUser(String userTo) throws InterruptedException {
		lock.lockWrite();
		
		if (!userTotalScore.containsKey(userTo)) {
			userTotalScore.put(userTo, new BigDecimal(0));
		}
		if (!userTotalNumber.containsKey(userTo)) {
			userTotalNumber.put(userTo, new BigDecimal(0));
		}
		
		lock.unlockWrite();
	}
	
	public static void displayHistory() throws InterruptedException {
		lock.lockRead(); // Locking Read
		
		for(UserScore us: userScoreArray) {
			System.out.println(us.toString());
		}
		
		lock.unlockRead(); //Unlocking Read
	}
	
	public static LeaderboardDetail getUserFoodDetails(String userTo) throws InterruptedException {
		LeaderboardDetail lBD = new LeaderboardDetail();
		
		lock.lockRead();
		
		if (userDetails.containsKey(userTo)) {
			lBD = userDetails.get(userTo);
		}
		
		lock.unlockRead();
		
		return lBD;
	}
	
	public static boolean insertUserFoodDetails(LeaderboardDetail lBD) throws InterruptedException {
		try {
			String userTo = lBD.getiGN();
			
			lock.lockWrite();
			
			if (userDetails.containsKey(userTo) ) {
				userDetails.replace(userTo, lBD);
			}
			else {
				userDetails.put(userTo, lBD);
			}
			
			lock.unlockWrite();
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String toString() {
		String s = "\nUser From: " + userFrom + "\nUserTo: " + userTo + "\nScore: " + score + "\n";
		return s;
	}
}
