package event.leaderboard;

import java.util.ArrayList;
import java.util.Iterator;

public class UserScore {
	private int submitID;
    private String userID;
    private String name;
    private String userTo;
    private double score;
    private String topic;
    public static int submitIDStatic = 1;
    public static ArrayList<UserScore> userScoreArray = new ArrayList<UserScore>();

    public UserScore() {
    }
    
    //UserScore a = new UserScore(UserScore.submitIDStatic, "1", "UserA", "UserB", 8.4, "Best Chili Crab");
	public UserScore(int submitID, String userID, String name, String userTo, double score, String topic) { 
		this.submitID = submitID;
		this.userID = userID;
		this.name = name;
		this.userTo = userTo;
		this.score = score;
		this.topic = topic;
		submitIDStatic++;
		userScoreArray.add(this);
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

	public String getName() {
		return name;
	}

	public String getUserTo() {
		return userTo;
	}

	public double getScore() {
		return score;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public ArrayList<UpdatingScore> getScoreResult(String topic) {
		ArrayList<UpdatingScore> alus = new ArrayList<UpdatingScore>();
		Iterator<UserScore> iter = userScoreArray.iterator();
		while(iter.hasNext()) {
			UserScore us = iter.next();
			
			if(us.getTopic().equals(topic)){
				alus = createUpds(alus, us);
			}
		}
		
		return alus;
	}
	
	private ArrayList<UpdatingScore> createUpds(ArrayList<UpdatingScore>alus, UserScore us){
		Iterator<UpdatingScore> iter = alus.iterator();
		if(alus.isEmpty()) {
			alus.add(new UpdatingScore(us.getUserTo(), us.getScore()));
		}else {
			while(iter.hasNext()) {
				UpdatingScore upds = iter.next();
				
				if(upds.getUserTo().equals(us.getUserTo())) {
					upds.setScore(upds.getScore() + us.getScore());
				}else {
					alus.add(new UpdatingScore(us.getUserTo(), us.getScore()));
				}
			}
		}
		return alus;
	}
	
	public void clearTopic(String topic) {
		Iterator<UserScore> iter = userScoreArray.iterator();
		while (iter.hasNext()) {
			UserScore us = iter.next();
			
			if(us.getTopic().equals(topic)){
				iter.remove();
			}
		}
		organiseSubmitID();
	}
	
	private void organiseSubmitID() {
		int i = 1;
		for(UserScore us: userScoreArray) {
			if(us.getSubmitID() != i) {
				us.setSubmitID(i);
			}
			i++;
		}
	}
}
