package model.implementation;

public abstract class AbstractMacroBased implements MacroBased {

	public static final int NUM_OF_MACROS = 25;

	private int calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
			potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
			vitaminB6, vitaminB7, vitaminB9, vitaminB12;

	public AbstractMacroBased(int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12) {
		setCalories(calories);
		setProtein(protein);
		setFat(fat);
		setCarbs(carbs);
		setSaturatedFat(saturatedFat);
		setTransFat(transFat);
		setFiber(fiber);
		setSugar(sugar);
		setSodium(sodium);
		setCholesterol(cholesterol);
		setCalcium(calcium);
		setPotassium(potassium);
		setVitaminA(vitaminA);
		setVitaminC(vitaminC);
		setVitaminD(vitaminD);
		setVitaminE(vitaminE);
		setVitaminK(vitaminK);
		setVitaminB1(vitaminB1);
		setVitaminB2(vitaminB2);
		setVitaminB3(vitaminB3);
		setVitaminB5(vitaminB5);
		setVitaminB6(vitaminB6);
		setVitaminB7(vitaminB7);
		setVitaminB9(vitaminB9);
		setVitaminB12(vitaminB12);
	}

	public MacroWrap getMacroInfo(int index) {
		String[] macroNames = { "Calories", "Protein", "Fat", "Carbs", "Saturated Fat", "Trans Fat", "Fiber", "Sugar",
				"Sodium", "Cholesterol", "Calcium", "Potassium", "Vitamin A", "Vitamin C", "Vitamin D", "Vitamin E",
				"Vitamin K", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6", "Vitamin B7",
				"Vitamin B9", "Vitamin B12" };
		int[] macroValues = { getCalories(), getProtein(), getFat(), getCarbs(), getSaturatedFat(), getTransFat(),
				getFiber(), getSugar(), getSodium(), getCholesterol(), getCalcium(), getPotassium(), getVitaminA(),
				getVitaminC(), getVitaminD(), getVitaminE(), getVitaminK(), getVitaminB1(), getVitaminB2(),
				getVitaminB3(), getVitaminB5(), getVitaminB6(), getVitaminB7(), getVitaminB9(), getVitaminB12() };

		if (index < 0 || index >= macroNames.length) {
			throw new IllegalArgumentException(
					"Index " + index + " is out of range for macros. Range = 0 - " + (macroNames.length - 1));
		}

		return new MacroWrap(macroNames[index], macroValues[index]);
	}

	@Override
	public String toString() {
		return "calories=" + calories + ", protein=" + protein + "g, fat=" + fat + "g, carbs=" + carbs + "g";
	}

	@Override
	public String toFullString() {
		return "calories=" + calories + ", protein=" + protein + ", fat=" + fat + ", carbs=" + carbs + ", saturatedFat="
				+ saturatedFat + ", transFat=" + transFat + ", fiber=" + fiber + ", sugar=" + sugar + ", sodium="
				+ sodium + ", cholesterol=" + cholesterol + ", calcium=" + calcium + ", potassium=" + potassium
				+ ", vitaminA=" + vitaminA + ", vitaminC=" + vitaminC + ", vitaminD=" + vitaminD + ", vitaminE="
				+ vitaminE + ", vitaminK=" + vitaminK + ", vitaminB1=" + vitaminB1 + ", vitaminB2=" + vitaminB2
				+ ", vitaminB3=" + vitaminB3 + ", vitaminB5=" + vitaminB5 + ", vitaminB6=" + vitaminB6 + ", vitaminB7="
				+ vitaminB7 + ", vitaminB9=" + vitaminB9 + ", vitaminB12=" + vitaminB12;
	}

	@Override
	public int getCalories() {
		return calories;
	}

	@Override
	public void setCalories(int calories) {
		checkInteger(calories);
		this.calories = calories;
	}

	@Override
	public int getProtein() {
		return protein;
	}

	@Override
	public void setProtein(int protein) {
		checkInteger(protein);
		this.protein = protein;
	}

	@Override
	public int getFat() {
		return fat;
	}

	@Override
	public void setFat(int fat) {
		checkInteger(fat);
		this.fat = fat;
	}

	@Override
	public int getCarbs() {
		return carbs;
	}

	@Override
	public void setCarbs(int carbs) {
		checkInteger(carbs);
		this.carbs = carbs;
	}

	@Override
	public int getSaturatedFat() {
		return saturatedFat;
	}

	@Override
	public void setSaturatedFat(int saturatedFat) {
		checkInteger(saturatedFat);
		this.saturatedFat = saturatedFat;
	}

	@Override
	public int getTransFat() {
		return transFat;
	}

	@Override
	public void setTransFat(int transFat) {
		checkInteger(transFat);
		this.transFat = transFat;
	}

	@Override
	public int getFiber() {
		return fiber;
	}

	@Override
	public void setFiber(int fiber) {
		checkInteger(fiber);
		this.fiber = fiber;
	}

	@Override
	public int getSugar() {
		return sugar;
	}

	@Override
	public void setSugar(int sugar) {
		checkInteger(sugar);
		this.sugar = sugar;
	}

	@Override
	public int getSodium() {
		return sodium;
	}

	@Override
	public void setSodium(int sodium) {
		checkInteger(sodium);
		this.sodium = sodium;
	}

	@Override
	public int getCholesterol() {
		return cholesterol;
	}

	@Override
	public void setCholesterol(int cholesterol) {
		checkInteger(cholesterol);
		this.cholesterol = cholesterol;
	}

	@Override
	public int getCalcium() {
		return calcium;
	}

	@Override
	public void setCalcium(int calcium) {
		checkInteger(calcium);
		this.calcium = calcium;
	}

	@Override
	public int getPotassium() {
		return potassium;
	}

	@Override
	public void setPotassium(int potassium) {
		checkInteger(potassium);
		this.potassium = potassium;
	}

	@Override
	public int getVitaminA() {
		return vitaminA;
	}

	@Override
	public void setVitaminA(int vitaminA) {
		checkInteger(vitaminA);
		this.vitaminA = vitaminA;
	}

	@Override
	public int getVitaminC() {
		return vitaminC;
	}

	@Override
	public void setVitaminC(int vitaminC) {
		checkInteger(vitaminC);
		this.vitaminC = vitaminC;
	}

	@Override
	public int getVitaminD() {
		return vitaminD;
	}

	@Override
	public void setVitaminD(int vitaminD) {
		checkInteger(vitaminD);
		this.vitaminD = vitaminD;
	}

	@Override
	public int getVitaminE() {
		return vitaminE;
	}

	@Override
	public void setVitaminE(int vitaminE) {
		checkInteger(vitaminE);
		this.vitaminE = vitaminE;
	}

	@Override
	public int getVitaminK() {
		return vitaminK;
	}

	@Override
	public void setVitaminK(int vitaminK) {
		checkInteger(vitaminK);
		this.vitaminK = vitaminK;
	}

	@Override
	public int getVitaminB1() {
		return vitaminB1;
	}

	@Override
	public void setVitaminB1(int vitaminB1) {
		checkInteger(vitaminB1);
		this.vitaminB1 = vitaminB1;
	}

	@Override
	public int getVitaminB2() {
		return vitaminB2;
	}

	@Override
	public void setVitaminB2(int vitaminB2) {
		checkInteger(vitaminB2);
		this.vitaminB2 = vitaminB2;
	}

	@Override
	public int getVitaminB3() {
		return vitaminB3;
	}

	@Override
	public void setVitaminB3(int vitaminB3) {
		checkInteger(vitaminB3);
		this.vitaminB3 = vitaminB3;
	}

	@Override
	public int getVitaminB5() {
		return vitaminB5;
	}

	@Override
	public void setVitaminB5(int vitaminB5) {
		checkInteger(vitaminB5);
		this.vitaminB5 = vitaminB5;
	}

	@Override
	public int getVitaminB6() {
		return vitaminB6;
	}

	@Override
	public void setVitaminB6(int vitaminB6) {
		checkInteger(vitaminB6);
		this.vitaminB6 = vitaminB6;
	}

	@Override
	public int getVitaminB7() {
		return vitaminB7;
	}

	@Override
	public void setVitaminB7(int vitaminB7) {
		checkInteger(vitaminB7);
		this.vitaminB7 = vitaminB7;
	}

	@Override
	public int getVitaminB9() {
		return vitaminB9;
	}

	@Override
	public void setVitaminB9(int vitaminB9) {
		checkInteger(vitaminB9);
		this.vitaminB9 = vitaminB9;
	}

	@Override
	public int getVitaminB12() {
		return vitaminB12;
	}

	@Override
	public void setVitaminB12(int vitaminB12) {
		checkInteger(vitaminB12);
		this.vitaminB12 = vitaminB12;
	}

	private void checkInteger(int num) {
		// System.out.println(num);
		if (num < 0) {
			System.out.println(this);
			throw new IllegalArgumentException("Cannot assign a negative value for a macro for macro item.");			
		}
	}

	public void checkFloat(float num) {
		if (num < 0)
			throw new IllegalArgumentException("Cannot assign a negative value for a macro for macro item.");
	}

	public class MacroWrap {
	    private String type;
	    private int amount;

	    public MacroWrap(String type, int amount) {
	        this.type = type;
	        this.amount = amount;
	    }

	    public String getType() {
	        return type;
	    }

	    public int getAmount() {
	        return amount;
	    }
	}
}
