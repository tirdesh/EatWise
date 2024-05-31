package model.implementation.food_log;

import model.implementation.day.Food;
import java.util.Map;
import java.util.HashMap;
import model.util.list.List;
import model.util.list.ArrayList;

public class LoggedFoodDatabase {

    private final Map<String, Food> database = new HashMap<>();

    public void saveFoodToDatabase(Food food) {
        database.put(food.getName(), food);
    }

    public Food checkIfFoodIsInDatabase(String foodName) {
        return database.get(foodName);
    }

    public Food[] foodsThatContainSubstring(String subString) {
        List<Food> matchingFoods = new ArrayList<>();
        for (Food food : database.values()) {
            if (food.getName().toLowerCase().contains(subString.toLowerCase())) {
                matchingFoods.addLast(food);
            }
        }
        Food[] array = new Food[matchingFoods.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = matchingFoods.get(i);
        }
        return array;
    }

    public String[] foodNamesThatContainSubstring(String subString) {
        List<String> matchingFoodNames = new ArrayList<>();
        for (Map.Entry<String, Food> entry : database.entrySet()) {
            String foodName = entry.getKey();
            if (foodName.toLowerCase().contains(subString.toLowerCase())) {
                matchingFoodNames.addLast(foodName);
            }
        }
        String[] array = new String[matchingFoodNames.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = matchingFoodNames.get(i);
        }
        return array;
    }

    @Override
    public String toString() {
        return "LoggedFoodDatabase\n" + databaseToString(false);
    }

    public String toFullString() {
        return "LoggedFoodDatabase [\n" + databaseToString(true) + "\n]";
    }

    private String databaseToString(boolean toFullString) {
        StringBuilder builder = new StringBuilder();
        for (Food food : database.values()) {
            builder.append(toFullString ? food.toFullString() : food.toString()).append("\n");
        }
        return builder.toString();
    }

    public Food[] getAllFoods() {
        return database.values().toArray(new Food[0]);
    }
    public Food getFood(String foodName) {
        return database.get(foodName);
    }

}
