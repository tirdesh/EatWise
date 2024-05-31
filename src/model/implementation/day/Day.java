package model.implementation.day;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.implementation.AbstractFoodCollection;

public class Day extends AbstractFoodCollection<Meal> {

	public static final int BREAKFAST_INDEX = 0;
	public static final int LUNCH_INDEX = 1;
	public static final int DINNER_INDEX = 2;
	public static final int SNACK_INDEX = 3;
	public static final int NUMBER_OF_MEALS = 4;

	private static final String FOOD_COLLECTION_NAME = "meals";

	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	private Date date;
	private String dateString;

	public Day(Date date, int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		getFoodCollection().addLast(new Meal(Meal.MT_BREAKFAST));
		getFoodCollection().addLast(new Meal(Meal.MT_LUNCH));
		getFoodCollection().addLast(new Meal(Meal.MT_DINNER));
		getFoodCollection().addLast(new Meal(Meal.MT_SNACK));
		this.date = date;
		dateString = SDF.format(date);
	}

	public Day(Date date) {
		this(date, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public Day() {
		this(new Date());
	}

	@Override
	public String toString() {
		return (dateString + ", " + super.toString()).replace("foodCollection", FOOD_COLLECTION_NAME);
	}

	@Override
	public String toFullString() {
		return ("Day [\ndate=" + dateString + ", " + super.toFullString() + "]").replace("foodCollection",
				FOOD_COLLECTION_NAME);
	}

	public void addIndexFood(Food food, int index) {
		addFoodMacrosToTotalMacros(food);
		getFoodCollection().get(index).add(food);
	}

	public void addBreakFastFood(Food food) {
		addIndexFood(food, BREAKFAST_INDEX);
	}

	public void addLunchFood(Food food) {
		addIndexFood(food, LUNCH_INDEX);
	}

	public void addDinnerFood(Food food) {
		addIndexFood(food, DINNER_INDEX);
	}

	public void addSnackFood(Food food) {
		addIndexFood(food, SNACK_INDEX);
	}

	public void removeFoodFromIndexMeal(String foodName, int index) {
		removeFoodMacrosFromTotalMacros(getFoodCollection().get(index).remove(foodName));
	}

	public void removeBreakfastMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, BREAKFAST_INDEX);
	}

	public void removeLunchMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, LUNCH_INDEX);
	}

	public void removeDinnerMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, DINNER_INDEX);
	}

	public void removeSnackMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, SNACK_INDEX);
	}

	public Meal getMeal(int index) {
		return getFoodCollection().get(index);
	}

	public Food[] getMealsFoods(int mealIndex) {
		return getFoodCollection().get(mealIndex).getFoodArray();
	}

	public Date getDate() {
		return date;
	}

	public String getDateString() {
		return dateString;
	}
}
