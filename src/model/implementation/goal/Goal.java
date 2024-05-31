package model.implementation.goal;

import model.implementation.AbstractMacroBased;
import model.implementation.day.Day;

public class Goal extends AbstractMacroBased {

	public static final String REGULAR_GOAL_STRING = "Regular";
	public static final String ATHELTIC_GOAL_STRING = "Athletic";
	public static final String STRENGTH_GOAL_STRING = "Strength";
	public static final String CUSTOM_GOAL_STRING = "Custom";

	public static final String ACTIVITY_LEVEL_LOW = "Low";
	public static final String ACTIVITY_LEVEL_MODERATE = "Moderate";
	public static final String ACTIVITY_LEVEL_HIGH = "Low";
	public static final String ACTIVITY_LEVEL_EXTREME = "Low";

	private String type;

	private boolean onlyCheckBasicMacros;

	public Goal(String type, int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12, boolean onlyCheckBasicMacros) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		setType(type);
	}

	public Goal(String type, int calories, int protein, int fat, int carbs) {
		this(REGULAR_GOAL_STRING, calories, protein, fat, carbs, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, true);
	}

	public Goal(int calories, int protein, int fat, int carbs) {
		this(REGULAR_GOAL_STRING, calories, protein, fat, carbs);
	}

	public boolean goalsMeet(Day day) {
		if (getCalories() != 0) {
			if (onlyCheckBasicMacros) {
				return day.getCalories() >= getCalories() && day.getProtein() >= getProtein()
						&& day.getFat() >= getFat() && day.getCarbs() >= getCarbs();
			} else {
				return day.getCalories() >= getCalories() && day.getProtein() >= getProtein()
						&& day.getFat() >= getFat() && day.getCarbs() >= getCarbs()
						&& day.getSaturatedFat() >= getSaturatedFat() && day.getTransFat() >= getTransFat()
						&& day.getFiber() >= getFiber() && day.getSugar() >= getSugar()
						&& day.getSodium() >= getSodium() && day.getCholesterol() >= getCholesterol()
						&& day.getCalcium() >= getCalcium() && day.getPotassium() >= getPotassium()
						&& day.getVitaminA() >= getVitaminA() && day.getVitaminC() >= getVitaminC()
						&& day.getVitaminD() >= getVitaminD() && day.getVitaminE() >= getVitaminE()
						&& day.getVitaminK() >= getVitaminK() && day.getVitaminB1() >= getVitaminB1()
						&& day.getVitaminB2() >= getVitaminB2() && day.getVitaminB3() >= getVitaminB3()
						&& day.getVitaminB5() >= getVitaminB5() && day.getVitaminB6() >= getVitaminB6()
						&& day.getVitaminB7() >= getVitaminB7() && day.getVitaminB9() >= getVitaminB9()
						&& day.getVitaminB12() >= getVitaminB12();
			}
		}
		return false;
	}

	public String toFullString() {
		return "Goal [type=" + type + ", " + super.toFullString() + ", onlyCheckBasicMacros=" + onlyCheckBasicMacros
				+ "]";
	}

	public void setCalculatedGoalType(String typeToCalculate, double bW, double height, String activityLevel) {
		// TODO
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOnlyCheckBasicMacros() {
		return onlyCheckBasicMacros;
	}

	public void setOnlyCheckBasicMacros(boolean onlyCheckBasicMacros) {
		this.onlyCheckBasicMacros = onlyCheckBasicMacros;
	}

}
