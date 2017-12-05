package event.leaderboard;

public class LeaderboardMain {
	
	public static void main(String[] args) {
		//Best Chili Crab
		//UserA, 1 
		//UserB, 2
		//UserC, 3
		
		new UserScore(UserScore.submitIDStatic, "1", "UserA", "UserB", 8.4, "Best Chili Crab");
		new UserScore(UserScore.submitIDStatic, "2", "UserB", "UserC", 3.9, "Best Chili Crab");
		new UserScore(UserScore.submitIDStatic, "2", "UserB", "UserA", 4.4, "Best Chili Crab");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserB", 7.9, "Best Chili");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserA", 5.6, "Best Chili Crab");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserB", 7.9, "Best Chili");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserB", 7.9, "Best Chili");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserB", 7.9, "Best Chili");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserA", 5.6, "Best Chili Crab");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserA", 5.6, "Best Chili Crab");
		new UserScore(UserScore.submitIDStatic, "3", "UserC", "UserA", 5.6, "Best Chili Crab");
		
		for(UpdatingScore upds:new UserScore().getScoreResult("Best Chili Crab")) {
			System.out.println("UserTo: " + upds.getUserTo() + "\nScore: " + upds.getScore() + "\n");
		}
		
		//for(UserScore us: UserScore.userScoreArray) {
		//	System.out.println("SubmitID: " + us.getSubmitID() + "\nUserID: " + us.getUserID() + "\nName: " + us.getName() + "\nTo: " + us.getUserTo() + "\nScore: " + us.getScore() + "\nTopic: " + us.getTopic() + "\n");
		//}
	}
}
