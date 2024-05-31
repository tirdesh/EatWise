package view_controller.fx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Controls all Foodlogger GUI functionality.
 * 
 * @author Mavericks
 *
 */
public class LoginController extends FoodLoggerGUIController implements FoodLoggerGUISubScene, Initializable {

	// Login pane
	@FXML
	private Pane loginPane;

	// Login controls
	@FXML
	private TextField loginTextField;
	@FXML
	private Label loginErrorLabel;
	@FXML
	private CheckBox stayLoggedInCheckBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// May use this method later in development
	}

	@Override
	public void displayScene() {
		// Empty since no user data to display
	}

	public void loginButton(ActionEvent e) {
		String userName = loginTextField.getText();
		if (!loginUser(userName)) {
			loginErrorLabel.setText("Could not find Username, please enter different Username and try again.");
			stayLoggedInCheckBox.setSelected(false);
			return;
		}
		checkStayLoggedIn();
		dashBoard(e);
	}

	public void signUpButton(ActionEvent e) {
		String userName = loginTextField.getText();
		try {
			manager.signUpUser(userName);
		} catch (IOException ex) {
			System.out.println("Could not find save files.");
		} catch (IllegalArgumentException ex) {
			loginErrorLabel.setText(ex.getMessage());
			return;
		}
		loginUser(userName);
		checkStayLoggedIn();
		dashBoard(e);
	}

	private boolean loginUser(String userName) {
		if (userName == null || userName.isEmpty())
			return false;
		try {
			return manager.loginUser(userName);
		} catch (FileNotFoundException e) {
			loginTextField.setText("Could not find usernames list.");
			return false;
		}

	}

	private void checkStayLoggedIn() {
		if (stayLoggedInCheckBox.isSelected()) {
			try {
				manager.keepUserNameLoggedIn(manager.getUser());
			} catch (IOException ex) {
				System.out.println("Could not load saved files.");
			}
		}
	}


}
