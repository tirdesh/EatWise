package model.manager;

import java.util.LinkedList;

import model.implementation.day.Food;
import model.util.BST.BinarySearchTree;

public class FoodSorter {
    private BinarySearchTree bst;

    public FoodSorter(BinarySearchTree bst) {
        this.bst = bst;
    }

    public LinkedList<Food> sort(String criteria) {
        switch (criteria.toLowerCase()) {
            case "calories":
                return bst.sortByCalories();
            case "fat":
                return bst.sortByFat();
            case "protein":
                return bst.sortByProtein();
            case "carbs":
                return bst.sortByCarbs();
            case "healthy":
                return bst.sortByHealthiness();
            default:
                // Default to sorting by key if criteria is not recognized
                LinkedList<String> keys = bst.sortByKey();
                LinkedList<Food> sortedFoods = new LinkedList<>();
                for (String key : keys) {
                    sortedFoods.add(bst.searchByKey(key));
                }
                return sortedFoods;
        }
    }
}
