package view_controller.fx;

import java.io.FileNotFoundException;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.implementation.day.Food;
import model.manager.FoodGrouping;

public class NutriMatchController extends FoodLoggerGUIController implements FoodLoggerGUISubScene {

    @FXML
    private TextField txtSearch; // The search field
    @FXML
    private Label noSimilarFoodsLabel;
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
    
    @Override
    public void displayScene() {
        // Not needed for this method
    }

    @Override
    public void nutriMatch(ActionEvent e) {
        // Implement this method as previously shown
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        // Get the search query from the text field
		String searchQuery = txtSearch.getText().trim();

		// Validate the search query
		if (!searchQuery.isEmpty()) {

		    Food[] allFoods = manager.getLoggedFoodDatabase().getAllFoods();
		    List<Food> allFoodsList = Arrays.asList(allFoods);

		    // Use FoodGrouping to find similar foods
		    FoodGrouping foodGrouping = new FoodGrouping();
		    // Get similar foods for the search query
		    List<Food> similarFoods = foodGrouping.getSimilarFoods(manager.getLoggedFoodDatabase().getFood(txtSearch.getText()), allFoodsList);
	        if (!similarFoods.isEmpty()) {
		    // Populate the TableView with similar foods
		    tableView.getItems().clear(); // Clear existing items
		    tableView.getItems().addAll(similarFoods);

		    // Set cell value factories for each TableColumn
		    foodNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		    caloriesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCalories()).asObject());
		    proteinColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProtein()).asObject());
		    fatColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFat()).asObject());
		    carbsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCarbs()).asObject());
		    noSimilarFoodsLabel.setText("");
	        }
	        else {
	            String message = "No similar foods found for the search query: " + searchQuery;
	            System.out.println(message);
	            noSimilarFoodsLabel.setText(message);
	        }
		}
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
