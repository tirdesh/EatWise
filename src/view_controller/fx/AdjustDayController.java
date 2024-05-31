package view_controller.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.implementation.day.Day;
import model.implementation.day.Food;
import model.implementation.day.Meal;

/**
 * Controller class for the adjust day FoodLogger GUI Scene. Is a sub scene from
 * FoddLoggerGUI tab menu. Handles everything that user interacts with while in
 * the scene. The @(FXML) tag is used to import all GUI elements from
 * AdjustDayScene.fxml file to this controller.
 * 
 * @author Mavericks
 *
 */
public class AdjustDayController extends FoodLoggerGUIController implements FoodLoggerGUISubScene, Initializable {

	/**
	 * The pane of the whole adjust day scene in case it needs to be edited during
	 * computations. Is an anchor pane.
	 */
	@FXML
	AnchorPane adjustDayPane;

	// All Adjust Day Text fields ------------------------------------------

	// Food name text Field
	/** The text field to enter the name of the food. */
	@FXML
	TextField addFoodNameTextField;

	// Serving specifics
	/** The text field to enter and view a food's number of servings */
	@FXML
	TextField numberOfServingsTextField;
	/** The text field to enter and view a food's serving size */
	@FXML
	TextField servingSizeTextField;

	// Macros
	/** The text field to enter and view a food's calories */
	@FXML
	TextField caloriesTextField;
	/** The text field to enter and view a food's protien */
	@FXML
	TextField protienTextField;
	/** The text field to enter and view a food's fat */
	@FXML
	TextField fatTextField;
	/** The text field to enter and view a food's carbs */
	@FXML
	TextField carbsTextField;
	/** The text field to enter and view a food's saturated fat */
	@FXML
	TextField saturatedFatTextField;
	/** The text field to enter and view a food's trans fat */
	@FXML
	TextField transFatTextField;
	/** The text field to enter and view a food's fiber */
	@FXML
	TextField fiberTextField;
	/** The text field to enter and view a food's sugar */
	@FXML
	TextField sugarTextField;
	/** The text field to enter and view a food's sodium */
	@FXML
	TextField sodiumTextField;
	/** The text field to enter and view a food's cholesterol */
	@FXML
	TextField cholesterolTextField;
	/** The text field to enter and view a food's calcium */
	@FXML
	TextField calciumTextField;
	/** The text field to enter and view a food's potassium */
	@FXML
	TextField potassiumTextField;
	/** The text field to enter and view a food's vitamin A */
	@FXML
	TextField vitaminATextField;
	/** The text field to enter and view a food's vitamin C */
	@FXML
	TextField vitaminCTextField;
	/** The text field to enter and view a food's vitamin D */
	@FXML
	TextField vitaminDTextField;
	/** The text field to enter and view a food's vitamin E */
	@FXML
	TextField vitaminETextField;
	/** The text field to enter and view a food's vitamin K */
	@FXML
	TextField vitaminKTextField;
	/** The text field to enter and view a food's vitamin B1 */
	@FXML
	TextField vitaminB1TextField;
	/** The text field to enter and view a food's vitamin B2 */
	@FXML
	TextField vitaminB2TextField;
	/** The text field to enter and view a food's vitamin B3 */
	@FXML
	TextField vitaminB3TextField;
	/** The text field to enter and view a food's vitamin B5 */
	@FXML
	TextField vitaminB5TextField;
	/** The text field to enter and view a food's vitamin B6 */
	@FXML
	TextField vitaminB6TextField;
	/** The text field to enter and view a food's vitamin B7 */
	@FXML
	TextField vitaminB7TextField;
	/** The text field to enter and view a food's vitamin B9 */
	@FXML
	TextField vitaminB9TextField;
	/** The text field to enter and view a food's vitamin B12 */
	@FXML
	TextField vitaminB12TextField;

	// ---------------------------------------------------------------------

	// All Adjust day labels -----------------------------------------------

	// Meal labels
	/** The adjustment label to hint the user to adjust a meal */
	@FXML
	Label mealAdjustmentHintLabel;
	/** The meal type label to show which meal is being viewed */
	@FXML
	Label mealTypeLabel;
	/** The meals macro counts label of the meal being viewed */
	@FXML
	Label mealMacroCountLabel;

	// Food labels
	/**
	 * The label for the name of the food being viewed, or a prompt label for the
	 * user to enter a food name if a food item is being added
	 */
	@FXML
	Label foodNameLabel;

	// Add Food labels
	/** A hint label prompting the user to end the food's info */
	@FXML
	Label enterFoodInfoHintLabel;
	/**
	 * A error label informing the user that something went wrong when adding food
	 * to meal and to try again
	 */
	@FXML
	Label enterFoodInfoErrorLabel;
	/**
	 * A hint label informing the user that they can enter any measurement unit at
	 * the end of the serving size text field and it will be converted to grams when
	 * added.
	 */
	@FXML
	Label servingSizeConversionHintLabel;

	// ---------------------------------------------------------------------

	// Hide rectangles -----------------------------------------------------

	/**
	 * A rectangle that hides all food info on the lower half of the scene so as to
	 * look blank. Reveals food info when actively viewing or adding a food item in
	 * a meal.
	 */
	@FXML
	Rectangle hideAllFoodInfoRectangle;
	/**
	 * A rectangle that can be toggled on and off to hide vitamin info, or to not
	 * worry about adding vitamin info. All vitamin text fields behind this
	 * rectangle should be set to zero. Works with vitaminInfoCheckBox.
	 */
	@FXML
	Rectangle hideVitaminInfoRectangle;

	// Check boxes
	/**
	 * Check box for revealing vitamin info, when this is checked it should reveal
	 * all vitamin info to user. Works with hideVitaminInfoRectangle.
	 */
	@FXML
	CheckBox vitaminInfoCheckBox;

	// ---------------------------------------------------------------------

	// Context button -----------------------------------------------------

	/**
	 * The button to either removes or adds a food item to a meal, when viewing a
	 * food item this button changes to removing a food item currently being viewed
	 * in a meal. When on the add food option in the view foods list this button
	 * changes to an add food button to add the entered food into the currently
	 * viewing meal.
	 */
	@FXML
	Button foodContextButton;

	// ---------------------------------------------------------------------

	// List views ----------------------------------------------------------

	/**
	 * The list of items in the currently viewing meal. Also always has an "Add
	 * Food" option to add a food item to a meal.
	 */
	
	@FXML
    Button Water_Intake_Tracker;
	
	@FXML
	ListView<String> foodListView;
	/**
	 * The list of previously logged foods that pops up when a user is typing a food
	 * name when adding a food item, updates every time a key is type into the food
	 * name text field.
	 */
	@FXML
	ListView<String> previouslyLoggedFoodsListView;

	// ---------------------------------------------------------------------

	// AdjustDayController specific fields ----------------------------------

	/**
	 * The index of the currently viewing meal, initially set to -1 to indicate that
	 * the scene was just entered and nothing has been interacted with.
	 */
	private int mealIndex = -1;

	/** The boolean flag to see if a previously logged food is being added */
	private boolean addingPrevLoggedFood = false;

	// ---------------------------------------------------------------------

	// Inherited methods ---------------------------------------------------

	@Override
	public void displayScene() {
		mealAdjustmentHintLabel.setText("(Select the meal you would like to add)");
		addFoodNameTextField.setVisible(false);
		previouslyLoggedFoodsListView.setVisible(false);
	}

	@Override
	public void adjustDay(ActionEvent e) {
		// Do nothing already on adjust day scene
	}

	// ---------------------------------------------------------------------

	// List View action methods --------------------------------------------

	/**
	 * Initializes the list views, and handles when a user has clicked on an item in
	 * the list view.
	 * 
	 * @param uRL            the uRL to pass
	 * @param resourceBundle the resource bundle to pass
	 */
	@Override
	public void initialize(URL uRL, ResourceBundle resourceBundle) {
		/*
		 * Handles when a user clicks on and item in the list of foods belonging to a
		 * meal
		 */
		foodListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			/**
			 * Method for when a food item is clicked on in the food list view. Lists the
			 * food item's macros, or readies user for entering new food item information.
			 * 
			 * @param arg0 The observable value by the user
			 * @param arg1 The second value being observed by user
			 * @param arg2 The third value being observed by user
			 * 
			 */
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// Get the food name that was clicked on
				String currentFoodName = foodListView.getSelectionModel().getSelectedItem();

				// If the food name was null go ahead and return
				if (currentFoodName == null)
					return;
				/*
				 * If the food name was "Add Food" the user has decided to add a food item to
				 * currently viewing meal so ready the GUI scene for adding a food item.
				 */
				if (currentFoodName.equals("Add food"))
					readyForAddFoodViewing();
				/*
				 * If not "Add Food" then the user has decided to view the food's macros so
				 * ready the GUI scene for viewing a food item's macros.
				 */
				else
					readyForFoodViewing(currentFoodName);
			}
		});

		previouslyLoggedFoodsListView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					/**
					 * Method for when a food name from the previously logged foods is clicked on in
					 * the previously logged foods list view. Fills current add food info with that
					 * clicked foods macros and name.
					 * 
					 * @param arg0 The observable value by the user
					 * @param arg1 The second value being observed by user
					 * @param arg2 The third value being observed by user
					 * 
					 */
					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						// Get the food name that was clicked on
						String currentFoodName = previouslyLoggedFoodsListView.getSelectionModel().getSelectedItem();

						// Fill out all of food that is being added info to the clicked food
						addingPrevLoggedFood = true;
						addFoodNameTextField.setText(currentFoodName);
						displayFoodInfoInTextFields(
								manager.getLoggedFoodDatabase().checkIfFoodIsInDatabase(currentFoodName));

					}

				});
	}

	// List Views helper methods ---------------

	/**
	 * Readies the GUI scene for food adding to a meal.
	 */
	private void readyForAddFoodViewing() {
		// Populate food info such as to prepare user to add food
		populateAddFoodInfo();

		// Set editability of text fields to all since adding
		setMacroTextFieldsEnability(true);
	}

	/**
	 * Readies the GUI scene for viewing a food item's information.
	 * 
	 * @param foodName the name of the food to view
	 */
	private void readyForFoodViewing(String foodName) {
		// Populate food info
		populateFoodInfo(foodName);

		// Set editability of text fields to none since just viewing
		setMacroTextFieldsEnability(false);
	}

	// -----------------------------------------

	// ---------------------------------------------------------------------

	// Meal viewing action methods -----------------------------------------

	/**
	 * Method for when the breakfast icon is clicked, sets the meal index to that of
	 * breakfast.
	 * 
	 * @param e the action event to handle
	 */
	public void adjustBreakfast(ActionEvent e) {
		// Set the meal to breakfast
		mealIndex = Day.BREAKFAST_INDEX;

		// Ready scene for meal viewing
		readyForMealViewing();
	}

	/**
	 * Method for when the lunch icon is clicked, sets the meal index to that of
	 * lunch.
	 * 
	 * @param e the action event to handle
	 */
	public void adjustLunch(ActionEvent e) {
		// Set the meal to lunch
		mealIndex = Day.LUNCH_INDEX;

		// Ready the scene for meal viewing
		readyForMealViewing();
	}

	/**
	 * Method for when the dinner icon is clicked, sets the meal index to that of
	 * dinner.
	 * 
	 * @param e the action event to handle
	 */

	public void adjustDinner(ActionEvent e) {
		// Set the meal to dinner
		mealIndex = Day.DINNER_INDEX;

		// Ready the scene for meal viewing
		readyForMealViewing();
	}

	/**
	 * Method for when the snack icon is clicked, sets the meal index to that of
	 * snack.
	 * 
	 * @param e the action event to handle
	 */
	public void adjustSnack(ActionEvent e) {
		// Set the meal to snack
		mealIndex = Day.SNACK_INDEX;

		// Ready the scene for meal viewing
		readyForMealViewing();
	}

	// Meal Viewing helper methods ---------------

	/**
	 * Readies the scene for viewing the current selected meal.
	 */
	private void readyForMealViewing() {
		populateMealInfo();
		hideFoodSpecifics();
	}

	// -----------------------------------------

	// ---------------------------------------------------------------------

	// Visibility action methods -------------------------------------------

	/**
	 * Method for when the view vitamin info check box is clicked, reveals vitamin
	 * info when checked.
	 * 
	 * @param e the action event to handle
	 */
	public void viewVitaminInfo(ActionEvent e) {
		// Reveal or not reveal vitamin info based on if check box is checked
		hideVitaminInfoRectangle.setVisible(vitaminInfoCheckBox.isSelected() ? false : true);
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
	
	

	/**
	 * Method for when the user is typing in the food name of a food being added,
	 * shows previous foods that contain the string being typed in, dynamically
	 * updates the list as the user types.
	 * 
	 * @param e the key event to handle
	 */
	public void displayPreviouslyLoggedFoods(KeyEvent e) {
		// Get substring to check with other food names in previously logged foods
		String subStringToCheck = addFoodNameTextField.getText();

		// Return if the string being type in is null or empty
		if (subStringToCheck == null || subStringToCheck.isEmpty()) {
			return;
		}

		/*
		 * Ready the previously logged foods list view once check are done to display
		 * food names that contain the currently type in string.
		 */
		previouslyLoggedFoodsListView.setVisible(true);
		previouslyLoggedFoodsListView.getItems().clear();
		previouslyLoggedFoodsListView.getItems()
				.addAll(manager.getLoggedFoodDatabase().foodNamesThatContainSubstring(subStringToCheck));
		addingPrevLoggedFood = false;
	}

	/**
	 * Hides the previously logged foods context list when user clicks text field
	 * again.
	 * 
	 * @param e the action event to handle
	 */
	public void hidePreviouslyLoggedFoods(MouseEvent e) {
		// Set the previouslyLoggedFoodsListView to be not visible
		previouslyLoggedFoodsListView.setVisible(false);
	}

	// ---------------------------------------------------------------------

	// Food context button action methods ----------------------------------

	/**
	 * Method for when the food context button is clicked, button changes what it
	 * does depending on the state of the scene meaning it either removes or adds a
	 * food item depending if the user is view a food item or adding a food item.
	 */
	
	
	
	
	public void foodContextButtonClicked() {
		// If the button is in the add state then get text field info and add food
		if (foodContextButton.getText().contains("Add"))
			addFoodToMeal();
		// If the button is in the remove state then remove currently viewing food
		else if (foodContextButton.getText().contains("Remove"))
			removeFoodFromMeal();
	}

	// Food context button helper methods ------

	/**
	 * Gets all the info from text fields and constructs a food object with that
	 * info and adds it to the user's currently viewing meal. If there is a problem
	 * parsing the user is informed of this and is allowed to adjust food info to
	 * pass parsing.
	 */
	private void addFoodToMeal() {
		// Try to parse all text fields into food item fields
		try {
			// Get all info from text fields
			String foodName = addFoodNameTextField.getText();
			float servSize = Float.parseFloat(servingSizeTextField.getText());
			float numOfServs = Float.parseFloat(numberOfServingsTextField.getText());
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
			Food f = new Food(foodName, servSize, (addingPrevLoggedFood ? (float) 1.0 : numOfServs), cals, pro, fat,
					carbs, satFat, traFat, fib, sug, sod, cho, calc, pot, vitA, vitC, vitD, vitE, vitK, vitB1, vitB2,
					vitB3, vitB5, vitB6, vitB7, vitB9, vitB12, false);
			if (addingPrevLoggedFood) {
				f.setNumberOfServings(numOfServs);
				addingPrevLoggedFood = false;
			}

			// Add that food object to the user's selected meal
			manager.addFoodToUsersCurrentDayIndexMeal(f, mealIndex);

			// Ready the scene for meal viewing so as to update with the newly added food
			readyForMealViewing();
		}
		// If there were any parsing errors they are caught
		catch (Exception e) {
			/*
			 * Inform the user there was a problem parsing and prompt them to check all
			 * entered info
			 */
			enterFoodInfoErrorLabel.setText("Could not add food to meal, make sure all fields are full");
		}
	}

	/**
	 * Removes the currently view food from the currently viewing meal.
	 */
	private void removeFoodFromMeal() {
		// Remove the currently viewed for from the selected meal
		manager.removeFoodFromUserCurrentDayIndexMeal(foodNameLabel.getText(), mealIndex);

		// Ready the scene for meal view so as to update with the removed food
		readyForMealViewing();
	}

	// -----------------------------------------

	// ---------------------------------------------------------------------

	// Various helper methods for class --------

	/**
	 * Populates lower half of scene with all food info of the selected food.
	 * 
	 * @param foodName the food to be viewed
	 */
	private void populateFoodInfo(String foodName) {
		// Get the meal to reference back to
		Meal m = manager.getUser().getCurrentDay().getMeal(mealIndex);

		// Set the check box text
		vitaminInfoCheckBox.setText("View Vitamin Info");

		// Set the button text
		foodContextButton.setText("Remove from " + m.getType());

		// Set the labels' texts
		servingSizeConversionHintLabel.setText("");
		foodNameLabel.setText(foodName);
		enterFoodInfoHintLabel.setText("");

		// Set text fields' text
		displayFoodInfoInTextFields(m.getFood(foodName));

		// Set visibilities
		hideAllFoodInfoRectangle.setVisible(false);
		addFoodNameTextField.setVisible(false);
		previouslyLoggedFoodsListView.setVisible(false);
	}

	/**
	 * Populates lower half of scene with all info for adding a food to the selected
	 * meal.
	 */
	private void populateAddFoodInfo() {
		// Get the meal to reference back to
		Meal m = manager.getUser().getCurrentDay().getMeal(mealIndex);

		// Set the check box text
		vitaminInfoCheckBox.setText("Add Vitamin Info");

		// Set the button text
		foodContextButton.setText("Add to " + m.getType());

		// Set the labels' texts
		servingSizeConversionHintLabel.setText(
				"Enter serving serving size with any\nmeasurment unit at the end, will be \nconverted to grams when added.");
		enterFoodInfoHintLabel.setText("Enter food macros");
		foodNameLabel.setText("Food Name");

		// Set the text fields' texts
		setMacroTextFieldsToString("0");
		numberOfServingsTextField.setText("1.0");
		servingSizeTextField.setText("1.0");
		addFoodNameTextField.setText("");

		// Set visibilities
		addFoodNameTextField.setVisible(true);
		hideAllFoodInfoRectangle.setVisible(false);
	}

	/**
	 * Sets the editability of the text fields based on enable.
	 * 
	 * @param enable the editability state to set the text fields in
	 */
	private void setMacroTextFieldsEnability(boolean enable) {
		// Set the editability of the text fields
		numberOfServingsTextField.setEditable(enable);
		servingSizeTextField.setEditable(enable);
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

	/**
	 * Sets the text fields to display the selected food item's info.
	 * 
	 * @param f the food to the display info of
	 */
	private void displayFoodInfoInTextFields(Food f) {
		// Fill text fields with f's info
		numberOfServingsTextField.setText("" + f.getNumberOfServings());
		servingSizeTextField.setText("" + f.getServingSize());
		caloriesTextField.setText("" + f.getCalories());
		protienTextField.setText("" + f.getProtein());
		fatTextField.setText("" + f.getFat());
		carbsTextField.setText("" + f.getCarbs());
		saturatedFatTextField.setText("" + f.getSaturatedFat());
		transFatTextField.setText("" + f.getTransFat());
		fiberTextField.setText("" + f.getFiber());
		sugarTextField.setText("" + f.getSugar());
		sodiumTextField.setText("" + f.getSodium());
		cholesterolTextField.setText("" + f.getCholesterol());
		calciumTextField.setText("" + f.getCalcium());
		potassiumTextField.setText("" + f.getPotassium());
		vitaminATextField.setText("" + f.getVitaminA());
		vitaminCTextField.setText("" + f.getVitaminC());
		vitaminDTextField.setText("" + f.getVitaminD());
		vitaminETextField.setText("" + f.getVitaminE());
		vitaminKTextField.setText("" + f.getVitaminK());
		vitaminB1TextField.setText("" + f.getVitaminB1());
		vitaminB2TextField.setText("" + f.getVitaminB2());
		vitaminB3TextField.setText("" + f.getVitaminB3());
		vitaminB5TextField.setText("" + f.getVitaminB5());
		vitaminB6TextField.setText("" + f.getVitaminB6());
		vitaminB7TextField.setText("" + f.getVitaminB7());
		vitaminB9TextField.setText("" + f.getVitaminB9());
		vitaminB12TextField.setText("" + f.getVitaminB12());
	}

	/**
	 * Sets all text fields text to the specified String s.
	 * 
	 * @param s the string to set text fields to
	 */
	private void setMacroTextFieldsToString(String s) {
		// Fill text fields with s
		numberOfServingsTextField.setText(s);
		servingSizeTextField.setText(s);
		caloriesTextField.setText(s);
		protienTextField.setText(s);
		fatTextField.setText(s);
		carbsTextField.setText(s);
		saturatedFatTextField.setText(s);
		transFatTextField.setText(s);
		fiberTextField.setText(s);
		sugarTextField.setText(s);
		sodiumTextField.setText(s);
		cholesterolTextField.setText(s);
		calciumTextField.setText(s);
		potassiumTextField.setText(s);
		vitaminATextField.setText(s);
		vitaminCTextField.setText(s);
		vitaminDTextField.setText(s);
		vitaminETextField.setText(s);
		vitaminKTextField.setText(s);
		vitaminB1TextField.setText(s);
		vitaminB2TextField.setText(s);
		vitaminB3TextField.setText(s);
		vitaminB5TextField.setText(s);
		vitaminB6TextField.setText(s);
		vitaminB7TextField.setText(s);
		vitaminB9TextField.setText(s);
		vitaminB12TextField.setText(s);
	}

	/**
	 * Populates the upper half of scene with the info of the currently selected
	 * meal.
	 */
	private void populateMealInfo() {
		// Get the current meal being adjusted to display info
		Meal m = manager.getUser().getCurrentDay().getMeal(mealIndex);

		// Set basic labels
		mealAdjustmentHintLabel.setText("");
		mealTypeLabel.setText(m.getType());

		// Get the macro info for meal
		StringBuilder sB = new StringBuilder("Calories: ");
		sB.append(m.getCalories());
		sB.append(" Protein: ");
		sB.append(m.getProtein());
		sB.append("g Fat: ");
		sB.append(m.getFat());
		sB.append("g Carbs: ");
		sB.append(m.getCarbs());
		sB.append("g");

		// Display meal info
		mealMacroCountLabel.setText(sB.toString());

		// Display food items in list view
		populateFoodListView();
	}

	/**
	 * Populates the food list view of all the foods that are in the currently
	 * selected meal.
	 */
	private void populateFoodListView() {
		// Refresh the food list view
		foodListView.getItems().clear();

		// If no meal is selected then do nothing except refresh
		if (mealIndex == -1)
			return;

		// Get the array of foods in the currently selected meal
		Food[] foods = manager.getUser().getCurrentDay().getMealsFoods(mealIndex);

		// Populate food list view with these food names
		for (int i = 0; i < foods.length; i++)
			foodListView.getItems().add(foods[i].getName());

		// Always have the option to add a food item to a meal
		foodListView.getItems().add("Add food");
	}

	/**
	 * Hides the food info from view if they are not viewing or adding a food item.
	 */
	private void hideFoodSpecifics() {
		// Set the labels to be empty
		servingSizeConversionHintLabel.setText("");
		foodNameLabel.setText("");
		enterFoodInfoErrorLabel.setText("");

		// Set visabilities so as to hide food info
		hideAllFoodInfoRectangle.setVisible(true);
		previouslyLoggedFoodsListView.setVisible(false);
		addFoodNameTextField.setVisible(false);
		hideAllFoodInfoRectangle.setVisible(true);
	}
	
	
	
	
	

	// -----------------------------------------
}
