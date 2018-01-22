package p2pfood;

public class RankCalculator {
	public static int getRankChange(int current, int ratingBase10){
		double position = (current-2500.0)/2500.0;

		if(position<0){
			position=position*-1;
		}
		position = 1- position;
		
		int ratingDifference = (ratingBase10-5)*20;
		
		return (int) (ratingDifference*position);
	}
}