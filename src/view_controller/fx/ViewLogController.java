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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.implementation.day.Day;
import model.implementation.food_log.FoodLog;
import model.implementation.food_log.Month;
import model.implementation.food_log.Year;
import model.implementation.goal.Goal;
import java.util.Map;

public class ViewLogController extends FoodLoggerGUIController implements FoodLoggerGUISubScene, Initializable {

	// Panes of view log
	@FXML
	ScrollPane goalProgressScrollPane;
	@FXML
	Pane goalProgressPane;

	// View log tree views
	@FXML
	TreeView<String> loggedDaysTreeView;
	@FXML
	TreeView<String> dayTreeView;

	// Objects of view log's line chart
	@FXML
	LineChart<String, Number> whichDaysGoalWasMetLineChart;
	@FXML
	NumberAxis goalMetYAxis;
	@FXML
	CategoryAxis daysXAxis;

	// Objects of view log's logged days vs goal met area
	@FXML
	ProgressBar daysLoggedVsGoalMetProgressBar;
	@FXML
	Label daysLoggedLabel;
	@FXML
	Label daysWhereGoalsWhereMetLabel1;
	@FXML
	Label daysWhereGoalsWhereMetLabel2;

	// The progress indicators of the viewLog
	@FXML
	ProgressIndicator calorieProgressIndicator;

	// Goal labels
	@FXML
	Label caloriesTotalLabel;
	@FXML
	Label caloriesGoalLabel;
	@FXML
	Label caloriesRemainingLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// May make method later in development

	}

	@Override
	public void viewLog(ActionEvent e) {
		// Do nothing already on view log scene
	}

	@Override
	public void displayScene() {
		populateWhichDaysGoalWasMetLineChart();
		populateLoggedDaysVsGoalMetArea();
		populateLoggedDaysTreeView();
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
	public void selectDayTreeItemToView() {
		// Clear the goal plane before view
		goalProgressPane.getChildren().clear();

		// Get the item clicked
		TreeItem<String> item = loggedDaysTreeView.getSelectionModel().getSelectedItem();

		// Make sure item click was a leaf node
		if (item == null || item.getParent() == null || item.getParent().getParent() == null
				|| item.getParent().getParent().getParent() == null)
			return;

		// Construct the date string to get
		StringBuilder date = new StringBuilder(item.getParent().getParent().getValue());
		date.append("-");
		String monthNumString = Month.convertMonthNameToNum(item.getParent().getValue()) + "";
		if (monthNumString.length() == 1)
			monthNumString = "0" + monthNumString;
		date.append(monthNumString);
		date.append("-");
		date.append(item.getValue());

		// Get the logged day
		Day loggedDay = manager.getUser().getFoodLog().getLoggedDay(date.toString());

		// Populate the day tree view based on what was selected
		populateDayTreeView(dayTreeView, loggedDay);

		goalProgressPane.getChildren().clear();
		
		// Populate the goal area with day and goal macros
		populateGoalProgressAreas(caloriesTotalLabel, caloriesGoalLabel, caloriesRemainingLabel,
				calorieProgressIndicator, goalProgressPane, goalProgressScrollPane, manager.getUser().getUsersGoal(),
				loggedDay);
	}

	private void populateWhichDaysGoalWasMetLineChart() {
		// Instantiate the series
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		// The logMap and user's goal to pull data from
		Map<String, Day> logMap = manager.getUser().getFoodLog().getLog();
		Goal g = manager.getUser().getUsersGoal();

		// Populate data for line chart
		for (Map.Entry<String, Day> entry : logMap.entrySet()) {
			String key = entry.getKey();
			Day val = entry.getValue();
			series.getData().add(new XYChart.Data<String, Number>(key, g.goalsMeet(val) ? 1 : 0));
		}

		// Add series to line chart
		whichDaysGoalWasMetLineChart.setStyle("-fx-accent: #ebc107");
		whichDaysGoalWasMetLineChart.getData().add(series);
	}

	private void populateLoggedDaysVsGoalMetArea() {
		FoodLog log = manager.getUser().getFoodLog();
		int loggedDays = log.getDaysCounter();
		int daysWhereGoalsWhereMet = log.getDaysWereGoalsWereMet();

		daysWhereGoalsWhereMetLabel1.setText(daysWhereGoalsWhereMet + "");
		daysWhereGoalsWhereMetLabel2.setText(daysWhereGoalsWhereMet + "");
		daysLoggedLabel.setText(loggedDays + "");

		double progress = loggedDays == 0 ? 0.0 : ((double) daysWhereGoalsWhereMet) / ((double) loggedDays);

		daysLoggedVsGoalMetProgressBar.setStyle("-fx-accent: #ebc107");
		daysLoggedVsGoalMetProgressBar.setProgress(progress);
	}

	private void populateLoggedDaysTreeView() {
        // Construct roots of tree
        TreeItem<String> rootItem = new TreeItem<>("Days Logged");

        // Get years map
        Map<Integer, Year> years = manager.getUser().getFoodLog().getYears();

        // Recursively populate tree view
        for (Map.Entry<Integer, Year> yearEntry : years.entrySet()) {
            Integer year = yearEntry.getKey();
            Year yearData = yearEntry.getValue();
            TreeItem<String> yearItem = populateYearTreeItem(year, yearData);
            rootItem.getChildren().add(yearItem);
        }

        // Plant the tree
        rootItem.setExpanded(true);
        loggedDaysTreeView.setRoot(rootItem);
    }

    private TreeItem<String> populateYearTreeItem(int year, Year yearData) {
        TreeItem<String> yearItem = new TreeItem<>(String.valueOf(year));
        Map<Integer, Month> months = yearData.getMonths();
        for (Map.Entry<Integer, Month> monthEntry : months.entrySet()) {
            Integer monthNum = monthEntry.getKey();
            Month monthData = monthEntry.getValue();
            TreeItem<String> monthItem = populateMonthTreeItem(monthNum, monthData);
            yearItem.getChildren().add(monthItem);
        }
        return yearItem;
    }

    private TreeItem<String> populateMonthTreeItem(int monthNum, Month monthData) {
        String monthName = Month.convertMonthNumToName(monthNum);
        TreeItem<String> monthItem = new TreeItem<>(monthName);
        Map<String, Day> days = monthData.getDays();
        for (Map.Entry<String, Day> dayEntry : days.entrySet()) {
            String day = dayEntry.getKey();
            Day dayData = dayEntry.getValue();
            TreeItem<String> dayItem = new TreeItem<>(day);
            // Add day-specific data if needed
            monthItem.getChildren().add(dayItem);
        }
        return monthItem;
    }

}
