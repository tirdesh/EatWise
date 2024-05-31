package model.implementation.day;

import model.implementation.AbstractMacroBased;


public class Food extends AbstractMacroBased {

	private String name;
	private float servingSize;
	private float numberOfServings;

	private boolean causesStomachIssues;

	public Food(String name, float servingSize, float numberOfServings, int calories, int protein, int fat, int carbs,
			int saturatedFat, int transFat, int fiber, int sugar, int sodium, int cholesterol, int calcium,
			int potassium, int vitaminA, int vitaminC, int vitaminD, int vitaminE, int vitaminK, int vitaminB1,
			int vitaminB2, int vitaminB3, int vitaminB5, int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12,
			boolean causesStomachIssues) {
		super((int) (calories * numberOfServings), (int) (protein * numberOfServings), (int) (fat * numberOfServings),
				(int) (carbs * numberOfServings), (int) (saturatedFat * numberOfServings),
				(int) (transFat * numberOfServings), (int) (fiber * numberOfServings), (int) (sugar * numberOfServings),
				(int) (sodium * numberOfServings), (int) (cholesterol * numberOfServings),
				(int) (calcium * numberOfServings), (int) (potassium * numberOfServings),
				(int) (vitaminA * numberOfServings), (int) (vitaminC * numberOfServings),
				(int) (vitaminD * numberOfServings), (int) (vitaminE * numberOfServings),
				(int) (vitaminK * numberOfServings), (int) (vitaminB1 * numberOfServings),
				(int) (vitaminB2 * numberOfServings), (int) (vitaminB3 * numberOfServings),
				(int) (vitaminB5 * numberOfServings), (int) (vitaminB6 * numberOfServings),
				(int) (vitaminB7 * numberOfServings), (int) (vitaminB9 * numberOfServings),
				(int) (vitaminB12 * numberOfServings));
		setName(name);
		setServingSize(servingSize);
		setNumberOfServings(numberOfServings);
		setCausesStomachIssues(causesStomachIssues);
	}

	public Food(String name, float servingSize, float numberOfServings, int calories, int protein, int fat, int carbs) {
		this(name, servingSize, numberOfServings, calories, protein, fat, carbs, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, false);
	}

	public Food(String name, float numberOfServings, int calories, int protein, int fat, int carbs) {
		this(name, 0, numberOfServings, calories, protein, fat, carbs);
	}


	public Food(String name, int calories, int protein, int fat, int carbs) {
		this(name, 1, calories, protein, fat, carbs);
	}

	@Override
	public String toString() {
		return name + ", numberOfServings=" + numberOfServings + ", " + super.toString();
	}

	@Override
	public String toFullString() {
		return "Food [name=" + name + ", servingSize=" + servingSize + ", numberOfServings=" + numberOfServings + ", "
				+ super.toFullString() + ", causesStomachIssues=" + causesStomachIssues + "]";
	}


	public String getName() {
		return name.toLowerCase();
	}


	public void setName(String name) {
		checkString(name.toLowerCase());
		this.name = name.toLowerCase();
	}



	public float getServingSize() {
		return servingSize;
	}


	public void setServingSize(float servingSize) {
		checkFloat(servingSize);
		this.servingSize = servingSize;
	}


	public float getNumberOfServings() {
		return numberOfServings;
	}


	public void setNumberOfServings(float numberOfServings) {
		checkFloat(numberOfServings);
		this.numberOfServings = numberOfServings;
	}


	public boolean causesStomachIssues() {
		return causesStomachIssues;
	}


	public void setCausesStomachIssues(boolean causesStomachIssues) {
		this.causesStomachIssues = causesStomachIssues;
	}


	private void checkString(String str) {
		if (str == null || str == "") {
			String type = str == null ? "null" : "empty";
			throw new IllegalArgumentException("The name of the food cannot be " + type + ".");
		}
		checkIfStringContains(str, "foodCollection");
	}


	private void checkIfStringContains(String stringToCheck, String stringToCheckAgainst) {
		if (stringToCheck.contains(stringToCheckAgainst)) {
			throw new IllegalArgumentException("The name of the food cannot contain " + stringToCheckAgainst
					+ ". (Due to this String's usage in the back end implementation)");
		}
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Food other = (Food) obj;
	    return name.equalsIgnoreCase(other.name);
	}
}
