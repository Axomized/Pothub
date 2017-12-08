package database.model;

public class FoodPreferences {
	String iGN;
	String foodPref;
	
	public FoodPreferences(DatabaseUserModel dUM, String foodPref) {
		this.iGN = dUM.getiGN();
		this.foodPref = foodPref;
	}
	
	public String getiGN() {
		return iGN;
	}
	
	public String getFoodPref() {
		return foodPref;
	}
	
	public void setiGN(String iGN) {
		this.iGN = iGN;
	}
	
	public void setFoodPref(String foodPref) {
		this.foodPref = foodPref;
	}
}
