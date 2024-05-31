package view_controller.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.implementation.day.Food;
import model.implementation.day.Meal;

/**
 * Controller class for the dash board of the FoodLogger app. Displays all info
 * of the user's current day.
 * 
 * @author Mavericks
 *
 */
public class UserDashboardController extends FoodLoggerGUIController implements FoodLoggerGUISubScene, Initializable {

	// The panes of the dashboard
	@FXML
	AnchorPane dashboardScenePane;
	@FXML
	ScrollPane goalProgressScrollPane;
	@FXML
	Pane goalProgressPane;

	// The tree views of the dashboard
	@FXML
	TreeView<String> currentDayTreeView;

	// The progress indicators of the dashboard
	@FXML
	ProgressIndicator calorieProgressIndicator;

	// The labels of the dashboard

	// Goal labels
	@FXML
	Label caloriesTotalLabel;
	@FXML
	Label caloriesGoalLabel;
	@FXML
	Label caloriesRemainingLabel;

	// Food view labels
	@FXML
	Label foodVIewNameLabel;
	@FXML
	Label foodViewCaloriesLabel;
	@FXML
	Label foodViewProtienLabel;
	@FXML
	Label foodViewFatLabel;
	@FXML
	Label foodViewCarbsLabel;
	@FXML
	Label foodViewSatFatLabel;
	@FXML
	Label foodViewFiberLabel;
	@FXML
	Label foodViewSugarLabel;
	@FXML
	Label foodViewSodiumLabel;
	@FXML
	Label foodViewCholesterolLabel;
	@FXML
	Label foodViewCalciumLabel;
	@FXML
	Label foodViewPotassiumLabel;

	// The hide rectangles of the dashboard
	@FXML
	Rectangle viewFoodPaneHideRectangle;

	@Override
	public void dashBoard(ActionEvent e) {
		// Do nothing already on dash board
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateCurrentDayTreeView();
		populateGoalProgressAreas(caloriesTotalLabel, caloriesGoalLabel, caloriesRemainingLabel,
				calorieProgressIndicator, goalProgressPane, goalProgressScrollPane, manager.getUser().getUsersGoal(),
				manager.getUser().getCurrentDay());
	}

	public void selectFoodItemToView() {
		TreeItem<String> item = currentDayTreeView.getSelectionModel().getSelectedItem();
		if (item == null || item.getParent() == null || item.getParent().getParent() == null)
			return;
		String mealType = item.getParent().getValue();
		String foodName = item.getValue();
		mealType = (mealType.substring(0, mealType.indexOf("("))).trim();
		foodName = (foodName.substring(0, foodName.indexOf("("))).trim();
		poulateViewFoodPane(Meal.getMealTypeIndex(mealType), foodName);
		showViewFoodPane();
	}

	@Override
	public void displayScene() {
		initialize(null, null);
	}

	private void populateCurrentDayTreeView() {
		populateDayTreeView(currentDayTreeView, manager.getUser().getCurrentDay());
	}

	private void poulateViewFoodPane(int mealIndex, String foodName) {
		Food f = manager.getUser().getCurrentDay().getMeal(mealIndex).getFood(foodName);
		foodVIewNameLabel.setText(foodName);
		foodViewCaloriesLabel.setText(f.getCalories() + "");
		foodViewProtienLabel.setText(f.getProtein() + "");
		foodViewFatLabel.setText(f.getFat() + "");
		foodViewCarbsLabel.setText(f.getCarbs() + "");
		foodViewSatFatLabel.setText(f.getSaturatedFat() + "");
		foodViewFiberLabel.setText(f.getFiber() + "");
		foodViewSugarLabel.setText(f.getSugar() + "");
		foodViewSodiumLabel.setText(f.getSodium() + "");
		foodViewCholesterolLabel.setText(f.getCholesterol() + "");
		foodViewCalciumLabel.setText(f.getCalcium() + "");
		foodViewPotassiumLabel.setText(f.getPotassium() + "");
	}

	private void showViewFoodPane() {
		viewFoodPaneHideRectangle.setVisible(false);
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
