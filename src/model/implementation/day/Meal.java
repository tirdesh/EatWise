package model.implementation.day;

import model.implementation.AbstractFoodCollection;

public class Meal extends AbstractFoodCollection<Food> {

	public static final String MT_BREAKFAST = "Breakfast";
	public static final String MT_LUNCH = "Lunch";
	public static final String MT_DINNER = "Dinner";
	public static final String MT_SNACK = "Snack";

	private static final String FOOD_COLLECTION_NAME = "foods";

	private String type;
	private int foodsThatContainStomachIssues;

	public Meal(String type, int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12, int foodsThatContainStomachIssues) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		setType(type);
		this.foodsThatContainStomachIssues = foodsThatContainStomachIssues;
	}

	public Meal(String type) {
		this(type, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public void add(Food f) {
		addFoodMacrosToTotalMacros(f);
		if (f.causesStomachIssues())
			foodsThatContainStomachIssues++;
		getFoodCollection().addLast(f);
	}

	public Food remove(String foodName) {
		Food f = getFoodCollection().remove(findFoodIndex(foodName));
		removeFoodMacrosFromTotalMacros(f);
		if (f.causesStomachIssues())
			foodsThatContainStomachIssues--;
		return f;
	}

	public Food getFood(int foodIndex) {
		return getFoodCollection().get(foodIndex);
	}

	public Food getFood(String foodName) {
		return getFoodCollection().get(findFoodIndex(foodName));
	}

	private int findFoodIndex(String foodName) {
		int foodIndex = -1;
		for (int i = 0; i < getFoodCollection().size(); i++) {
			if (getFoodCollection().get(i).getName().contains(foodName)) {
				foodIndex = i;
				break;
			}
		}
		if (foodIndex == -1) {
			throw new IllegalArgumentException(
					"A food item with the name of " + foodName + " does not exist in " + type + ".");
		}
		return foodIndex;
	}

	@Override
	public String toString() {
		return (type + ", " + super.toString()).replace("foodCollection", FOOD_COLLECTION_NAME);

	}

	@Override
	public String toFullString() {
		return ("Meal [type=" + type + ", foodsThatContainStomachIssues=" + foodsThatContainStomachIssues + ", "
				+ super.toFullString() + "]").replace("foodCollection", FOOD_COLLECTION_NAME);
	}

	public Food[] getFoodArray() {
		Food[] foods = new Food[getFoodCollection().size()];
		for (int i = 0; i < foods.length; i++)
			foods[i] = getFoodCollection().get(i);
		return foods;
	}

	public static int getMealTypeIndex(String type) {
		switch (type) {
		case "Breakfast":
			return 0;
		case "Lunch":
			return 1;
		case "Dinner":
			return 2;
		case "Snack":
			return 3;
		default:
			return -1;
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
