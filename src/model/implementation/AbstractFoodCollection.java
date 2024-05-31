package model.implementation;

import model.implementation.day.Food;
import model.util.list.ArrayList;
import model.util.list.List;

public class AbstractFoodCollection<T extends MacroBased> extends AbstractMacroBased implements FoodCollection {

	private List<T> foodCollection;

	public AbstractFoodCollection(int calories, int protein, int fat, int carbs, int saturatedFat, int transFat,
			int fiber, int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		foodCollection = new ArrayList<T>();
	}

	@Override
	public String toString() {
		return super.toString() + ", foodCollection=\n" + foodCollectionToString(false);
	}

	@Override
	public String toFullString() {
		return super.toFullString() + ", foodCollection=\n" + foodCollectionToString(true);
	}


	private String foodCollectionToString(boolean toFullStirng) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < foodCollection.size(); i++)
			str.append((toFullStirng ? foodCollection.get(i).toFullString() : foodCollection.get(i).toString()) + "\n");
		return str.toString();
	}

	@Override
    public void addFoodMacrosToTotalMacros(Food f) {
        updateMacroTotals(f, true);
    }

    @Override
    public void removeFoodMacrosFromTotalMacros(Food f) {
        updateMacroTotals(f, false);
    }

    private void updateMacroTotals(Food food, boolean add) {
        int sign = add ? 1 : -1;
        setCalories(getCalories() + sign * food.getCalories());
        setProtein(getProtein() + sign * food.getProtein());
        setFat(getFat() + sign * food.getFat());
        setCarbs(getCarbs() + sign * food.getCarbs());
        setSaturatedFat(getSaturatedFat() + sign * food.getSaturatedFat());
        setTransFat(getTransFat() + sign * food.getTransFat());
        setFiber(getFiber() + sign * food.getFiber());
        setSugar(getSugar() + sign * food.getSugar());
        setSodium(getSodium() + sign * food.getSodium());
        setCholesterol(getCholesterol() + sign * food.getCholesterol());
        setCalcium(getCalcium() + sign * food.getCalcium());
        setPotassium(getPotassium() + sign * food.getPotassium());
        setVitaminA(getVitaminA() + sign * food.getVitaminA());
        setVitaminC(getVitaminC() + sign * food.getVitaminC());
        setVitaminD(getVitaminD() + sign * food.getVitaminD());
        setVitaminE(getVitaminE() + sign * food.getVitaminE());
        setVitaminK(getVitaminK() + sign * food.getVitaminK());
        setVitaminB1(getVitaminB1() + sign * food.getVitaminB1());
        setVitaminB2(getVitaminB2() + sign * food.getVitaminB2());
        setVitaminB3(getVitaminB3() + sign * food.getVitaminB3());
        setVitaminB5(getVitaminB5() + sign * food.getVitaminB5());
        setVitaminB6(getVitaminB6() + sign * food.getVitaminB6());
        setVitaminB7(getVitaminB7() + sign * food.getVitaminB7());
        setVitaminB9(getVitaminB9() + sign * food.getVitaminB9());
        setVitaminB12(getVitaminB12() + sign * food.getVitaminB12());
    }


	public int size() {
		return foodCollection.size();
	}

	public List<T> getFoodCollection() {
		return foodCollection;
	}

}
