package model.util.BST;

import model.implementation.day.Food;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BinarySearchTree {
    private Node root;
    private Map<String, Food> dataMap;

    private class Node {
        String key;
        Food value; // Store Food object as the value

        Node left;
        Node right;

        Node(String key, Food value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public BinarySearchTree() {
        this.root = null;
        this.dataMap = new HashMap<>();
    }

    public BinarySearchTree(Map<String, Food> dataMap) {
        this.root = null;
        this.dataMap = dataMap;
        buildBST();
    }

    private void buildBST() {
        for (Map.Entry<String, Food> entry : dataMap.entrySet()) {
            insert(entry.getKey(), entry.getValue());
        }
    }

    public void insert(String key, Food value) {
        root = insertRecursive(root, key, value);
        dataMap.put(key, value);
    }

    private Node insertRecursive(Node node, String key, Food value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insertRecursive(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insertRecursive(node.right, key, value);
        } else {
            // Key already exists, update the value
            node.value = value;
        }

        return node;
    }

    public Food searchByKey(String key) {
        return dataMap.get(key);
    }
    
    public Food searchByValue(String value) {
        for (Map.Entry<String, Food> entry : dataMap.entrySet()) {
            if (entry.getValue().toString().equals(value)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void delete(String key) {
        root = deleteRecursive(root, key);
        dataMap.remove(key);
    }

    private Node deleteRecursive(Node node, String key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = deleteRecursive(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = deleteRecursive(node.right, key);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            node.key = minKey(node.right);
            node.right = deleteRecursive(node.right, node.key);
        }

        return node;
    }

    private String minKey(Node node) {
        String minKey = node.key;
        while (node.left != null) {
            minKey = node.left.key;
            node = node.left;
        }
        return minKey;
    }

    public LinkedList<String> searchByPrefix(String prefix) {
        LinkedList<String> matchingKeys = new LinkedList<>();
        searchByPrefixRecursive(root, prefix, matchingKeys);
        return matchingKeys;
    }

    private void searchByPrefixRecursive(Node node, String prefix, LinkedList<String> matchingKeys) {
        if (node == null) {
            return;
        }

        if (node.key.startsWith(prefix)) {
            matchingKeys.add(node.key);
        }

        searchByPrefixRecursive(node.left, prefix, matchingKeys);
        searchByPrefixRecursive(node.right, prefix, matchingKeys);
    }

    public LinkedList<String> sortByKey() {
        LinkedList<String> keys = new LinkedList<>(dataMap.keySet());
        MergeSort.sort(keys);
        return keys;
    }

    public LinkedList<Food> sortByCalories() {
        LinkedList<Food> foods = new LinkedList<>(dataMap.values());
        foods.sort(Comparator.comparingInt(Food::getCalories).reversed());
        return foods;
    }

    public LinkedList<Food> sortByFat() {
        LinkedList<Food> foods = new LinkedList<>(dataMap.values());
        foods.sort(Comparator.comparingInt(Food::getFat));
        return foods;
    }

    public LinkedList<Food> sortByProtein() {
        LinkedList<Food> foods = new LinkedList<>(dataMap.values());
        foods.sort(Comparator.comparingInt(Food::getProtein).reversed());
        return foods;
    }

    public LinkedList<Food> sortByCarbs() {
        LinkedList<Food> foods = new LinkedList<>(dataMap.values());
        foods.sort(Comparator.comparingInt(Food::getCarbs));
        return foods;
    }
    

    public LinkedList<Food> sortByHealthiness() {
        LinkedList<Food> foods = new LinkedList<>(dataMap.values());
        foods.sort(Comparator.comparingDouble(this::calculateHealthiness).reversed());
        return foods;
    }

    private double calculateHealthiness(Food food) {
        // Define your formula for healthiness based on calories, fat, protein, and carbs
        // You might weigh each nutrient differently based on dietary guidelines or personal preference
        double healthinessScore = (food.getCalories() * 0.2) - (food.getFat() * 0.3) + (food.getProtein() * 0.5) - (food.getCarbs() * 0.1);
        return healthinessScore;
    }
    
    private void inOrderTraversalRecursive(Node node, LinkedList<Food> foods) {
        if (node != null) {
            inOrderTraversalRecursive(node.left, foods);
            foods.add(this.dataMap.get(node.key));
            inOrderTraversalRecursive(node.right, foods);
        }
    }


    public LinkedList<Food> inOrderTraversal() {
        LinkedList<Food> foods = new LinkedList<>();
        inOrderTraversalRecursive(root, foods);
        return foods;
    }

	public List<Food> getAllValues() {
        return new ArrayList<>(inOrderTraversal());
	}


}
