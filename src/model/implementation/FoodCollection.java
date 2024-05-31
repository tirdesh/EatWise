package model.implementation;

import model.implementation.day.Food;

public interface FoodCollection {
    void addFoodMacrosToTotalMacros(Food food);
    void removeFoodMacrosFromTotalMacros(Food food);
}