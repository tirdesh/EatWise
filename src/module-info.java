module FoodLoggerGUI {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens view_controller.fx to javafx.graphics, javafx.fxml;
}
