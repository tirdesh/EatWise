package view_controller.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.implementation.goal.Goal;

public class AdjustGoalController extends FoodLoggerGUIController implements FoodLoggerGUISubScene {

	// Macros
	/** The text field to enter and view a goal's calories */
	@FXML
	TextField caloriesTextField;
	/** The text field to enter and view a goal's protien */
	@FXML
	TextField protienTextField;
	/** The text field to enter and view a goal's fat */
	@FXML
	TextField fatTextField;
	/** The text field to enter and view a goal's carbs */
	@FXML
	TextField carbsTextField;
	/** The text field to enter and view a goal's saturated fat */
	@FXML
	TextField saturatedFatTextField;
	/** The text field to enter and view a goal's trans fat */
	@FXML
	TextField transFatTextField;
	/** The text field to enter and view a goal's fiber */
	@FXML
	TextField fiberTextField;
	/** The text field to enter and view a goal's sugar */
	@FXML
	TextField sugarTextField;
	/** The text field to enter and view a goal's sodium */
	@FXML
	TextField sodiumTextField;
	/** The text field to enter and view a goal's cholesterol */
	@FXML
	TextField cholesterolTextField;
	/** The text field to enter and view a goal's calcium */
	@FXML
	TextField calciumTextField;
	/** The text field to enter and view a goal's potassium */
	@FXML
	TextField potassiumTextField;
	/** The text field to enter and view a goal's vitamin A */
	@FXML
	TextField vitaminATextField;
	/** The text field to enter and view a goal's vitamin C */
	@FXML
	TextField vitaminCTextField;
	/** The text field to enter and view a goal's vitamin D */
	@FXML
	TextField vitaminDTextField;
	/** The text field to enter and view a goal's vitamin E */
	@FXML
	TextField vitaminETextField;
	/** The text field to enter and view a goal's vitamin K */
	@FXML
	TextField vitaminKTextField;
	/** The text field to enter and view a goal's vitamin B1 */
	@FXML
	TextField vitaminB1TextField;
	/** The text field to enter and view a goal's vitamin B2 */
	@FXML
	TextField vitaminB2TextField;
	/** The text field to enter and view a goal's vitamin B3 */
	@FXML
	TextField vitaminB3TextField;
	/** The text field to enter and view a goal's vitamin B5 */
	@FXML
	TextField vitaminB5TextField;
	/** The text field to enter and view a goal's vitamin B6 */
	@FXML
	TextField vitaminB6TextField;
	/** The text field to enter and view a goal's vitamin B7 */
	@FXML
	TextField vitaminB7TextField;
	/** The text field to enter and view a goal's vitamin B9 */
	@FXML
	TextField vitaminB9TextField;
	/** The text field to enter and view a goal's vitamin B12 */
	@FXML
	TextField vitaminB12TextField;

	// ---------------------------------------------------------------------

	// Adjust Goal Labels
	@FXML
	Label adjusttingGoalLabel;
	@FXML
	Label adjustGoalErrorLabel;

	// Objects for displaying current goal specs
	@FXML
	PieChart currentGoalPieChart;
	@FXML
	Label currGoalCalsLabel;

	// Check boxes
	/**
	 * Check box for revealing vitamin info, when this is checked it should reveal
	 * all vitamin info to user. Works with hideVitaminInfoRectangle.
	 */
	@FXML
	CheckBox vitaminInfoCheckBox;
	@FXML
	CheckBox viewGoalSpecificsCheckBox;

	// ---------------------------------------------------------------------

	// Buttons -------------------------------------------------------------

	@FXML
	Button editGoalButton;
	@FXML
	Button manuallyAdjustGoalButton;

	// ---------------------------------------------------------------------

	// Hide rectangles
	@FXML
	Rectangle hideEditGoalButtonArea;
	@FXML
	Rectangle hideGoalSpecs;
	@FXML
	Rectangle hideVitaminInfoRectangle;

	@Override
	public void displayScene() {
		populateCurrentGoalArea();
	}

	public void manuallyAdjustGoalButtonClicked() {
		viewGoalSpecificsCheckBox.setSelected(false);
		hideGoalSpecs.setVisible(false);
		hideEditGoalButtonArea.setVisible(false);
		populateGoalTextFields(manager.getUser().getUsersGoal());
		setMacroTextFieldsEnability(true);
	}

	public void viewGoalSpecificsCheckBoxClicked(ActionEvent e) {
		hideEditGoalButtonArea.setVisible(true);
		boolean status = viewGoalSpecificsCheckBox.isSelected();
		populateGoalTextFields(manager.getUser().getUsersGoal());
		hideGoalSpecs.setVisible(!status);
		hideVitaminInfoRectangle.setVisible(!status);
		setMacroTextFieldsEnability(false);
	}

	public void editGoalButtonClicked(ActionEvent e) {
		if (editGoal()) {
			hideGoalSpecs.setVisible(true);
			hideEditGoalButtonArea.setVisible(true);
			vitaminInfoCheckBox.setSelected(false);
			adjustGoalErrorLabel.setText("");
			displayScene();
		}
	}

	public void vitaminInfoCheckBoxClicked(ActionEvent e) {
		hideVitaminInfoRectangle.setVisible(vitaminInfoCheckBox.isSelected() ? false : true);
	}

	private void populateCurrentGoalArea() {
		// Get calories and basic macros for current goal viewing
		Goal goal = manager.getUser().getUsersGoal();
		int calories = goal.getCalories();
		int protien = goal.getProtein();
		int fat = goal.getFat();
		int carbs = goal.getCarbs();

		// Display calories for viewing
		currGoalCalsLabel.setText("Calories: " + calories);

		// Populate pie chart with macro breakdown from calories
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Protien", protien), new PieChart.Data("Fat", fat),
				new PieChart.Data("Carbs", carbs));

		currentGoalPieChart.setData(pieChartData);

	}

	private boolean editGoal() {
		// Try to parse all text fields into food item fields
		try {
			// Get all info from text fields
			int cals = Integer.parseInt(caloriesTextField.getText());
			int pro = Integer.parseInt(protienTextField.getText());
			int fat = Integer.parseInt(fatTextField.getText());
			int carbs = Integer.parseInt(carbsTextField.getText());
			int satFat = Integer.parseInt(saturatedFatTextField.getText());
			int traFat = Integer.parseInt(transFatTextField.getText());
			int fib = Integer.parseInt(fiberTextField.getText());
			int sug = Integer.parseInt(sugarTextField.getText());
			int sod = Integer.parseInt(sodiumTextField.getText());
			int cho = Integer.parseInt(cholesterolTextField.getText());
			int calc = Integer.parseInt(calciumTextField.getText());
			int pot = Integer.parseInt(potassiumTextField.getText());
			int vitA = Integer.parseInt(vitaminATextField.getText());
			int vitC = Integer.parseInt(vitaminCTextField.getText());
			int vitD = Integer.parseInt(vitaminDTextField.getText());
			int vitE = Integer.parseInt(vitaminETextField.getText());
			int vitK = Integer.parseInt(vitaminKTextField.getText());
			int vitB1 = Integer.parseInt(vitaminB1TextField.getText());
			int vitB2 = Integer.parseInt(vitaminB2TextField.getText());
			int vitB3 = Integer.parseInt(vitaminB3TextField.getText());
			int vitB5 = Integer.parseInt(vitaminB5TextField.getText());
			int vitB6 = Integer.parseInt(vitaminB6TextField.getText());
			int vitB7 = Integer.parseInt(vitaminB7TextField.getText());
			int vitB9 = Integer.parseInt(vitaminB9TextField.getText());
			int vitB12 = Integer.parseInt(vitaminB12TextField.getText());

			// Convert that info into a food object
			Goal g = new Goal("Manual", cals, pro, fat, carbs, satFat, traFat, fib, sug, sod, cho, calc, pot, vitA,
					vitC, vitD, vitE, vitK, vitB1, vitB2, vitB3, vitB5, vitB6, vitB7, vitB9, vitB12, true);

			manager.getUser().resetUsersGoal(g);
			return true;
		}
		// If there were any parsing errors they are caught
		catch (Exception e) {
			/*
			 * Inform the user there was a problem parsing and prompt them to check all
			 * entered info
			 */
			adjustGoalErrorLabel.setText("Could not adjust goal make sure all fields are full.");
			manuallyAdjustGoalButtonClicked();
			return false;
		}

	}

	private void populateGoalTextFields(Goal g) {
		// Fill text fields with f's info
		caloriesTextField.setText("" + g.getCalories());
		protienTextField.setText("" + g.getProtein());
		fatTextField.setText("" + g.getFat());
		carbsTextField.setText("" + g.getCarbs());
		saturatedFatTextField.setText("" + g.getSaturatedFat());
		transFatTextField.setText("" + g.getTransFat());
		fiberTextField.setText("" + g.getFiber());
		sugarTextField.setText("" + g.getSugar());
		sodiumTextField.setText("" + g.getSodium());
		cholesterolTextField.setText("" + g.getCholesterol());
		calciumTextField.setText("" + g.getCalcium());
		potassiumTextField.setText("" + g.getPotassium());
		vitaminATextField.setText("" + g.getVitaminA());
		vitaminCTextField.setText("" + g.getVitaminC());
		vitaminDTextField.setText("" + g.getVitaminD());
		vitaminETextField.setText("" + g.getVitaminE());
		vitaminKTextField.setText("" + g.getVitaminK());
		vitaminB1TextField.setText("" + g.getVitaminB1());
		vitaminB2TextField.setText("" + g.getVitaminB2());
		vitaminB3TextField.setText("" + g.getVitaminB3());
		vitaminB5TextField.setText("" + g.getVitaminB5());
		vitaminB6TextField.setText("" + g.getVitaminB6());
		vitaminB7TextField.setText("" + g.getVitaminB7());
		vitaminB9TextField.setText("" + g.getVitaminB9());
		vitaminB12TextField.setText("" + g.getVitaminB12());
	}

	/**
	 * Sets the editability of the text fields based on enable.
	 * 
	 * @param enable the editability state to set the text fields in
	 */
	private void setMacroTextFieldsEnability(boolean enable) {
		// Set the editability of the text fields
		caloriesTextField.setEditable(enable);
		protienTextField.setEditable(enable);
		fatTextField.setEditable(enable);
		carbsTextField.setEditable(enable);
		saturatedFatTextField.setEditable(enable);
		transFatTextField.setEditable(enable);
		fiberTextField.setEditable(enable);
		sugarTextField.setEditable(enable);
		sodiumTextField.setEditable(enable);
		cholesterolTextField.setEditable(enable);
		calciumTextField.setEditable(enable);
		potassiumTextField.setEditable(enable);
		vitaminATextField.setEditable(enable);
		vitaminCTextField.setEditable(enable);
		vitaminDTextField.setEditable(enable);
		vitaminETextField.setEditable(enable);
		vitaminKTextField.setEditable(enable);
		vitaminB1TextField.setEditable(enable);
		vitaminB2TextField.setEditable(enable);
		vitaminB3TextField.setEditable(enable);
		vitaminB5TextField.setEditable(enable);
		vitaminB6TextField.setEditable(enable);
		vitaminB7TextField.setEditable(enable);
		vitaminB9TextField.setEditable(enable);
		vitaminB12TextField.setEditable(enable);
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
