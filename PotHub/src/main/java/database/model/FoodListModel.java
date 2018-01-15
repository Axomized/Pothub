package database.model;

public class FoodListModel {
	String food;
	String foodType;
	
	public FoodListModel() {
		
	}

	public FoodListModel(String food, String foodType) {
		this.food = food;
		this.foodType = foodType;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
}
