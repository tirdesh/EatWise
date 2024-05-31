package model.manager;

import java.util.*;

import model.implementation.day.Food;
import model.util.graph.Graph;

public class FoodGrouping {
    private double threshold = 0.7; // Example threshold value

    // Method to calculate similarity between two foods
	private double calculateSimilarity(Food food1, Food food2) {
	    // Get the maximum possible differences in each nutritional value
	    double maxCaloriesDiff = Math.max(food1.getCalories(), food2.getCalories());
	    double maxProteinDiff = Math.max(food1.getProtein(), food2.getProtein());
	    double maxFatDiff = Math.max(food1.getFat(), food2.getFat());
	    double maxCarbsDiff = Math.max(food1.getCarbs(), food2.getCarbs());

	    // Calculate the Euclidean distance between nutritional values of food1 and food2
	    double calorieDiff = Math.abs(food1.getCalories() - food2.getCalories());
	    double proteinDiff = Math.abs(food1.getProtein() - food2.getProtein());
	    double fatDiff = Math.abs(food1.getFat() - food2.getFat());
	    double carbsDiff = Math.abs(food1.getCarbs() - food2.getCarbs());

	    // Calculate the Euclidean distance
	    double euclideanDistance = Math.sqrt(calorieDiff * calorieDiff + proteinDiff * proteinDiff + 
	                                         fatDiff * fatDiff + carbsDiff * carbsDiff);

	    // Normalize the distance to get a similarity score (closer foods will have higher similarity)
	    double maxDistance = Math.sqrt(maxCaloriesDiff * maxCaloriesDiff + maxProteinDiff * maxProteinDiff +
	                                   maxFatDiff * maxFatDiff + maxCarbsDiff * maxCarbsDiff);
	    double similarity = 1 - (euclideanDistance / maxDistance);

	    return similarity;
	}

    // Method to group similar foods
    public Map<Food, List<Food>> groupSimilarFoods(List<Food> foods) {
        Map<Food, List<Food>> groups = new HashMap<>();

        // Create a graph to represent relationships between foods
        Graph graph = new Graph();

        // Build edges between similar foods based on calculated similarity
        for (int i = 0; i < foods.size(); i++) {
            Food food1 = foods.get(i);
            for (int j = i + 1; j < foods.size(); j++) {
                Food food2 = foods.get(j);
                double similarity = calculateSimilarity(food1, food2);
                if (similarity > threshold) { // Adjust threshold based on desired similarity
                    graph.addEdge(food1, food2);
                }
            }
        }

        // Group similar foods based on the graph structure
        List<List<Food>> connectedComponents = graph.findConnectedComponents();
        for (List<Food> component : connectedComponents) {
            for (Food food : component) {
                groups.put(food, component);
            }
        }
        
        // Print all food groups
        System.out.println("Food Groups:");
        for (Map.Entry<Food, List<Food>> entry : groups.entrySet()) {
            System.out.println("Group:");
            System.out.println("Food: " + entry.getKey().getName());
            System.out.println("Similar Foods:");
            for (Food similarFood : entry.getValue()) {
                System.out.println(similarFood.getName());
            }
            System.out.println("-----");
        }

        return groups;
    }
    
    public List<Food> getSimilarFoods(Food targetFood, List<Food> allFoods) {
        if (targetFood == null) {
            // Print an error message or handle appropriately
            System.out.println("Error: Target food is null.");
            return Collections.emptyList(); // Or return an empty list, indicating that no similar foods were found
        }
        
        Map<Food, List<Food>> groups = groupSimilarFoods(allFoods);

        // Find the group containing the target food
        for (Map.Entry<Food, List<Food>> entry : groups.entrySet()) {
            if (entry.getValue().contains(targetFood)) {
                return entry.getValue();
            }
        }

        // If targetFood is not found in any group, return an empty list
        return Collections.emptyList();
    }

    
}
