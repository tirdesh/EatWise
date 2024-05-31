package view_controller.fx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.implementation.AbstractMacroBased;
import model.implementation.AbstractMacroBased.MacroWrap;
import model.implementation.day.Day;
import model.implementation.day.Food;
import model.implementation.day.Meal;
import model.implementation.goal.Goal;

public class FoodLoggerGUIController extends FoodLoggerGUI {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private FXMLLoader loader;

	/**
	 * Displays the login scene.
	 * 
	 * @param e the event to set
	 */
	public void login(ActionEvent e) {
		switchScene(new LoginController(), e, FXML_LOGIN);
	}

	/**
	 * Displays the user's dash board scene. Should be overridden and set to do
	 * nothing when in the adjust day controller.
	 * 
	 * @param e action event performed
	 */
	public void dashBoard(ActionEvent e) {
		switchScene(new UserDashboardController(), e, FXML_DASHBOARD);
	}

	/**
	 * Displays the user's adjust day scene. Should be overridden and set to do
	 * nothing when in the adjust day controller.
	 * 
	 * @param e action event performed
	 */
	public void adjustDay(ActionEvent e) {
		switchScene(new AdjustDayController(), e, FXML_ADJUSTDAY);
	}

	public void viewLog(ActionEvent e) {
		switchScene(new ViewLogController(), e, FXML_VIEWLOG);
	}

	/**
	 * Displays the user's adjust goal scene. Should be overridden and set to do
	 * nothing when in the adjust goal controller.
	 * 
	 * @param e action event performed
	 */
	public void adjustGoal(ActionEvent e) {
		// TODO
		switchScene(new AdjustGoalController(), e, FXML_ADJUSTGOAL);
	}

	public void nutriMatch(ActionEvent e) {
		// TODO
		switchScene(new NutriMatchController(), e, FXML_NUTRIMATCH);
	}
	public void nutriSort(ActionEvent e) {
		// TODO
		switchScene(new NutriSortController(), e, FXML_NUTRISORT);
	}
	
	public void WaterIntakeTracker(ActionEvent e) {
		// TODO
		switchScene(new WaterIntakeTrackerController(), e, FXML_waterIntakeTracker);
	}
	
	

	public void logout(ActionEvent e) {
		try {
			manager.logoutUser();
			manager.removeUserNameLoggedIn();
			login(e);
		} catch (IOException e1) {
			System.out.println("Could not find file to save data.");
		}
	}

	private <E extends FoodLoggerGUISubScene> void switchScene(E controller, ActionEvent e, String sceneFxml) {
		loader = new FXMLLoader(getClass().getResource(sceneFxml));
		try {
			root = loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		controller = loader.getController();
		controller.displayScene();

		// To switch the scene
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(CSS);
		stage.setScene(scene);
		stage.show();
	}

	// Common methods to be used throughout scenes ------------------------------

	public void populateDayTreeView(TreeView<String> treeView, Day d) {
		// Construct roots of tree
		TreeItem<String> rootItem = new TreeItem<>("Meals");

		// Populate tree with all meal branches and their food leaves
		for (int i = 0; i < Day.NUMBER_OF_MEALS; i++) {
			// Get current meal and create tree item out of it
			Meal m = d.getMeal(i);
			TreeItem<String> mealItem = new TreeItem<>(m.getType() + " " + " (" + m.getCalories() + " calories)");

			// Get meal's array of foods and go through and them to meal item
			Food[] foods = m.getFoodArray();
			for (int j = 0; j < foods.length; j++) {
				Food f = foods[j];
				mealItem.getChildren()
						.add(new TreeItem<String>(f.getName() + " " + " (" + f.getCalories() + " calories)"));
			}

			// Connect meal item to root item
			mealItem.setExpanded(true);
			rootItem.getChildren().add(mealItem);
		}

		// Plant the tree
		rootItem.setExpanded(true);
		treeView.setRoot(rootItem);
	}
	
	 
	

	public void populateGoalProgressAreas(Label caloriesTotalLabel, Label caloriesGoalLabel,
			Label caloriesRemainingLabel, ProgressIndicator calorieProgressIndicator, Pane goalProgressPane,
			ScrollPane goalProgressScrollPane, Goal g, Day d) {

		// Get calorie info form current day and goal
		int dayCalorieAmount = d.getCalories();
		int goalCalorieAmount = g.getCalories();

		// Set the labels
		caloriesTotalLabel.setText(dayCalorieAmount + "");
		caloriesGoalLabel.setText(goalCalorieAmount + "");
		caloriesRemainingLabel.setText((goalCalorieAmount - dayCalorieAmount) + "");

		// Get the progress in a double value to display
		double calorieProgress = goalCalorieAmount == 0 ? 1.0 : ((double) dayCalorieAmount) / goalCalorieAmount;

		// Set the progress indicator
		calorieProgressIndicator.setStyle("-fx-accent: #ebc107");
		calorieProgressIndicator.setProgress(calorieProgress);

		// Set height of pane so all elements can fit
		goalProgressPane.setPrefHeight(950.0);

		// Populate goal progress scroll pane
		for (int i = 1, x = 0, y = 0; i < AbstractMacroBased.NUM_OF_MACROS; i++, y += 40) {
			// Get the macros to display progress of
			MacroWrap dayMacro = d.getMacroInfo(i);
			MacroWrap goalMacro = g.getMacroInfo(i);
			int dayMacroValue = dayMacro.getAmount();
			int goalMacroValue = goalMacro.getAmount();

			// Get the name of macro and set coords
			Label macroNameLabel = new Label(dayMacro.getType());
			macroNameLabel.setLayoutX(x);
			macroNameLabel.setLayoutY(y);
			macroNameLabel.setFont(new Font("Candara", 15));

			// Get number specifics to be next to bar
			Label totalNumLabel = new Label(dayMacroValue + "");
			totalNumLabel.setLayoutX(x + 45 * 2);
			totalNumLabel.setLayoutY(y);
			totalNumLabel.setFont(new Font("Candara", 10));
			Label goalNumLabel = new Label(goalMacroValue + "");
			goalNumLabel.setLayoutX(x + 65 * 2);
			goalNumLabel.setLayoutY(y);
			goalNumLabel.setFont(new Font("Candara", 10));
			Label remNumLabel = new Label((goalMacroValue - dayMacroValue) + "");
			remNumLabel.setLayoutX(x + 100 * 2);
			remNumLabel.setLayoutY(y);
			remNumLabel.setFont(new Font("Candara", 10));

			// Construct progress bar and set settings
			ProgressBar macroProgressBar = new ProgressBar();
			macroProgressBar.setStyle("-fx-accent: #ebc107");
			macroProgressBar.setLayoutX(x);
			macroProgressBar.setLayoutY(y + 20);
			macroProgressBar.setPrefHeight(10.0);
			macroProgressBar.setPrefWidth(241.0);

			// Populate progress bar with info
			double macroAndVitProgress = goalMacroValue == 0 ? 1.0 : ((double) dayMacroValue) / goalMacroValue;
			macroProgressBar.setProgress(macroAndVitProgress);

			// Add them to the flow pane
			goalProgressPane.getChildren().add(macroNameLabel);
			goalProgressPane.getChildren().add(totalNumLabel);
			goalProgressPane.getChildren().add(goalNumLabel);
			goalProgressPane.getChildren().add(remNumLabel);
			goalProgressPane.getChildren().add(macroProgressBar);
		}
		goalProgressScrollPane.setContent(goalProgressPane);
	}

	// --------------------------------------------------------------------------
}
