package view_controller.fx;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.implementation.user.FoodLoggerUser;

public class WaterIntakeTrackerController extends FoodLoggerGUIController implements FoodLoggerGUISubScene {

    @FXML
    private TextField waterAmountTextField;

    @FXML
    private Button addWaterButton;

    @FXML
    private Label waterIntakeLabel;
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    private int totalWaterIntake = 0;

    private FoodLoggerUser foodLoggerUser; // Reference to the FoodLoggerUser
    private void getData() {
        // Create a new XYChart.Series to store data points
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Generate random water intake data for 7 days
        Random random = new Random();
        for (int i = 1; i <= 7; i++) {
            int waterIntake = random.nextInt(2000); // Random intake up to 2000 ml
            series.getData().add(new XYChart.Data<>(Integer.toString(i), waterIntake));
        }

        // Add the series to the bar chart
        barChart.getData().add(series);
    }
    // Setter method to inject FoodLoggerUser instance
    public void setFoodLoggerUser(FoodLoggerUser foodLoggerUser) {
        this.foodLoggerUser = foodLoggerUser;
    }

    // This method gets called when the FXML file is loaded
    public void initialize() {
        // Initialization logic here, if necessary
        // Initialize the chart
        xAxis.setLabel("Day");
        yAxis.setLabel("Water Intake (ml)");
        getData();
    }

    @FXML
    void addWater(ActionEvent event) {
        try {
            int amount = Integer.parseInt(waterAmountTextField.getText());
            totalWaterIntake += amount;
            updateWaterIntakeLabel();
            waterAmountTextField.clear();

            // Call logWaterIntake method of FoodLoggerUser
            if (foodLoggerUser != null) {
                foodLoggerUser.logWaterIntake(amount);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.out.println("Please enter a valid number for water intake.");
        }
    }

    private void updateWaterIntakeLabel() {
        waterIntakeLabel.setText("Total Water Intake: " + totalWaterIntake + " ml");
    }

    @Override
    public void displayScene() {
        // TODO Auto-generated method stub

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
