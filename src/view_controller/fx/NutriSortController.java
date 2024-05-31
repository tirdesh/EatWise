package view_controller.fx;

import java.io.FileNotFoundException;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.implementation.day.Food;
import model.manager.FoodSorter;
import model.util.BST.BinarySearchTree;

public class NutriSortController extends FoodLoggerGUIController implements FoodLoggerGUISubScene {

    @FXML
    private TextField txtSearch; // The search field
    @FXML
    private Label noSimilarFoodsLabel;
    @FXML
    private ComboBox<String> sortComboBox; // The sorting ComboBox

    @FXML
    private Button btnSearch; // The search button

    @FXML
    private TableView<Food> tableView; 
    
    @FXML
    private TableColumn<Food, String> foodNameColumn;

    @FXML
    private TableColumn<Food, Integer> caloriesColumn;

    @FXML
    private TableColumn<Food, Integer> proteinColumn;

    @FXML
    private TableColumn<Food, Integer> fatColumn;

    @FXML
    private TableColumn<Food, Integer> carbsColumn;
    private ObservableList<Food> foodList;
    private FoodSorter foodSorter;
    private BinarySearchTree foodBST; // Binary Search Tree for sorting Food items

    @Override
    public void displayScene() {
        // Not needed for this method
    }

    private void generateBST() {
        // Get all foods from the LoggedFoodDatabase
        Food[] allFoods = manager.getLoggedFoodDatabase().getAllFoods();

        // Convert array to list for easier processing
        List<Food> allFoodsList = List.of(allFoods);

        // Create a new BinarySearchTree and insert all foods into it
        foodBST = new BinarySearchTree();
        for (Food food : allFoodsList) {
            foodBST.insert(food.getName(), food);
        }
    }
    
    @FXML
    private void initialize() {
    	generateBST();
        // Initialize the sorting ComboBox
        sortComboBox.getItems().addAll("Food Name", "Calories", "Protein", "Fat", "Carbs", "Healthy");
        sortComboBox.setValue("Food Name"); // Set default sorting
        
        // Initialize foodList
        foodList = FXCollections.observableArrayList();

        // Initialize FoodSorter
        foodSorter = new FoodSorter(foodBST);

        // Initialize TableView columns
        foodNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        caloriesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCalories()).asObject());
        proteinColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProtein()).asObject());
        fatColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFat()).asObject());
        carbsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCarbs()).asObject());

        // Populate the TableView with all foods initially
        populateTableWithAllFoods();
    }
    
    private void populateTableWithAllFoods() {
        // Clear existing data in the foodList
        foodList.clear();

        // Retrieve all foods from the Binary Search Tree
        List<Food> allFoods = foodBST.getAllValues();

        // Add all foods to the foodList
        foodList.addAll(allFoods);

        // Set the items in the TableView
        tableView.setItems(foodList);
    }

@FXML
private void handleSearchButtonAction(ActionEvent event) {
    // Get the search query from the text field
    String searchQuery = txtSearch.getText().trim().toLowerCase();

    // If the search query is empty, repopulate the table with all foods
    if (searchQuery.isEmpty()) {
        populateTableWithAllFoods();
        return;
    }

    // Filter foods that match the search query
    List<Food> matchingFoods = new ArrayList<>();
    for (Food food : foodList) {
        if (food.getName().toLowerCase().contains(searchQuery)) {
            matchingFoods.add(food);
        }
    }

    // If no foods match the search query, display a message
    if (matchingFoods.isEmpty()) {
        noSimilarFoodsLabel.setVisible(true);
        tableView.getItems().clear(); // Clear table
    } else {
        noSimilarFoodsLabel.setVisible(false); // Hide the message
        // Set the matching foods in the TableView
        tableView.setItems(FXCollections.observableArrayList(matchingFoods));

        // If sorting is applied, reapply sorting to the filtered list
        if (!sortComboBox.getValue().equalsIgnoreCase("food name")) {
            handleSortAction(event);
        }
    }
}

	@FXML
	private void handleSortAction(ActionEvent event) {
	    String selectedCriteria = sortComboBox.getValue();
	
	    // Sort the foodList based on the selected criteria
	    LinkedList<Food> sortedFoods = foodSorter.sort(selectedCriteria.toLowerCase());
	
	    // Update the TableView with sorted foods
	    foodList.clear();
	    foodList.addAll(sortedFoods);
	}

    
	@FXML
    private void handlewaterIntakeTrackererAction(ActionEvent event) {
        try {
            // Load the FXML file for the Water Intake Tracker scene
            Parent waterIntakeTrackerRoot = FXMLLoader.load(getClass().getResource("waterIntakeTracker.fxml"));
            
            // Get the current stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Create a new scene with the loaded FXML root node
            Scene scene = new Scene(waterIntakeTrackerRoot);
            
            // Set the new scene on the current stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("waterIntakeTracker File Not Found");
        }
    }
}
