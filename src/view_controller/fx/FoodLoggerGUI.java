package view_controller.fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.manager.FoodLoggerManager;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;

public class FoodLoggerGUI extends Application {

	// The primary class to operate the FoodLogger application
	protected static FoodLoggerManager manager;

	// Paths to style sheets
	protected final String CSS = this.getClass().getResource("LoginDesign.css").toExternalForm();

	// Paths to FXML files
	protected static String PACKAGE = "view_controller/";
	protected static String FXML_LOGIN = "NewLoginUI.fxml";
	protected static String FXML_DASHBOARD = "DashBoard.fxml";
	protected static String FXML_ADJUSTDAY = "AdjustDayScene.fxml";
	protected static String FXML_VIEWLOG = "ViewLogScene.fxml";
	protected static String FXML_ADJUSTGOAL = "AdjustGoalScene.fxml";
	protected static String FXML_NUTRIMATCH = "NutriMatchScene.fxml";
	protected static String FXML_waterIntakeTracker = "WaterIntakeTracker.fxml";
	protected static String FXML_NUTRISORT = "NutriSortScene.fxml";

	// Paths to JPGs
	protected static final String JPG_ICON_FOOD_LOGGER_PATH = "/foodicon.jpg";

	// The base dimension for a cell phone screen resolution
	protected static final int DIM_WIDTH_PHONE = 1850;/*1170;*/
	protected static final int DIM_HEIGHT_PHONE = 2532;/*2532;*/

	// The default stage elements
	protected static final double DIM_WIDTH_STAGE_DEFAULT = DIM_WIDTH_PHONE / 2;
	protected static final double DIM_HEIGHT_STAGE_DEFAULT = DIM_HEIGHT_PHONE / 2.75;

	/*
	 * TODO : 6/14/2023 - Check if a user was already logged in and display first
	 * scene based off that
	 */

	public static void main(String[] args) throws IOException {
		// Login to the manager
		manager = FoodLoggerManager.loginToManager();

		// Launch GUI
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set the basics for the GUI window
		setDefaultStageSettings(primaryStage);

		// Configure and present the stage
		primaryStage.setScene(getIntialScene(manager.checkIfUserStayedLoggedIn()));
		primaryStage.show();

		// Quit program via red X and save data
		primaryStage.setOnCloseRequest(event -> quitProgramAndSave(primaryStage));
	}

	private void setDefaultStageSettings(Stage stage) {
		// Construct the display icon of food logger window
		Image icon = new Image(JPG_ICON_FOOD_LOGGER_PATH);

		// Configure the stage
		stage.setWidth(DIM_WIDTH_STAGE_DEFAULT);
		stage.setHeight(DIM_HEIGHT_STAGE_DEFAULT);
		stage.setResizable(false);

		stage.getIcons().add(icon);
		stage.setTitle("FoodLogger");

	}

	private void quitProgramAndSave(Stage stage) {
		try {
			manager.logoutOfManager();
		} catch (IOException e1) {
			System.out.println("Could not find files to save to.");
		}
		stage.close();
	}

	private Scene getIntialScene(boolean alreadyLoggedIn) {
		// Construct the basics for scene
		Scene scene = null;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(alreadyLoggedIn ? FXML_DASHBOARD : FXML_LOGIN));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);
		scene.getStylesheets().add(CSS);

		// Return the login scene
		return scene;
	}
}
