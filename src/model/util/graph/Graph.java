package model.util.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.implementation.day.Food;

public class Graph {
    private Map<Food, List<Food>> adjacencyMap;

    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    // Method to add an edge between two foods
    public void addEdge(Food source, Food destination) {
        adjacencyMap.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjacencyMap.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
    }

    // Method to perform depth-first search (DFS)
    private void dfs(Food food, Set<Food> visited, List<Food> component) {
        visited.add(food);
        component.add(food);
        for (Food neighbor : adjacencyMap.getOrDefault(food, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, component);
            }
        }
    }

    // Method to find connected components using DFS
    public List<List<Food>> findConnectedComponents() {
        List<List<Food>> connectedComponents = new ArrayList<>();
        Set<Food> visited = new HashSet<>();
        for (Food food : adjacencyMap.keySet()) {
            if (!visited.contains(food)) {
                List<Food> component = new ArrayList<>();
                dfs(food, visited, component);
                connectedComponents.add(component);
            }
        }
        return connectedComponents;
    }
}
