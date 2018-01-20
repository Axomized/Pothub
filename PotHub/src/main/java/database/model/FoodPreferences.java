package database.model;

public class FoodPreferences {
	private String iGN;
	private String foodPref;
	
	public FoodPreferences(String iGN, String foodPref) {
		this.iGN = iGN;
		this.foodPref = foodPref;
	}
	
	public FoodPreferences(String foodPref) {
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
