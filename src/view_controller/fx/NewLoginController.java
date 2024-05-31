package view_controller.fx;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewLoginController extends FoodLoggerGUIController implements FoodLoggerGUISubScene, Initializable {

    @FXML
    private Button Login_Button;

    @FXML
    private AnchorPane Login_Form;

    @FXML
    private TextField Login_Name;

    @FXML
    private PasswordField Login_Password;

    @FXML
    private Button SU_CreateButton;

    @FXML
    private TextField SU_Email;

    @FXML
    private PasswordField SU_Password;

    @FXML
    private TextField SU_PhoneNumber;

    @FXML
    private TextField SU_UserName;

    @FXML
    private AnchorPane Side_Form;

    @FXML
    private Button SignIn_Button;

    @FXML
    private Button SignUp_Button;

    @FXML
    private AnchorPane SignUp_Form;
    @FXML
    private Label loginErrorLabel;
    @FXML
    private TextField loginTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ensure the SignUp_Form is not visible initially
        SignUp_Form.setVisible(false);
        SU_CreateButton.setVisible(true);
        SignIn_Button.setVisible(false);
    }
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,15}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PHONE_NUMBER_REGEX = "^\\d{10}$";


    public void loginButton(ActionEvent e) {
        String userName = Login_Name.getText();
        String password= Login_Password.getText();
        if (!userName.matches(USERNAME_REGEX)) {
            showAlert("Username must be 3-15 characters long and can only contain letters, digits, and underscores.");
            return;
        }
        if (!password.matches(PASSWORD_REGEX)) {
            showAlert("Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one digit, and one special character.");
            return;
        }
        
        if (!loginUser(userName)) {
            loginErrorLabel.setText("Could not find Username, please enter different Username and try again.");
            return;
        }
		dashBoard(e);
    }

    public void signUpButton(ActionEvent e) {
        String userName = SU_UserName.getText();
//        String userName = SU_UserName.getText().trim();
        String password = SU_Password.getText();
        String email = SU_Email.getText().trim();
        String phoneNumber = SU_PhoneNumber.getText().trim();

        if (!userName.matches(USERNAME_REGEX)) {
            showAlert("Username must be 3-15 characters long and can only contain letters, digits, and underscores.");
            return;
        }

        if (!password.matches(PASSWORD_REGEX)) {
            showAlert("Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one digit, and one special character.");
            return;
        }

        if (!email.matches(EMAIL_REGEX)) {
            showAlert("Invalid email address.");
            return;
        }

        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            showAlert("Phone number must be a 10-digit number.");
            return;
        }

        try {
            manager.signUpUser(userName);
        } catch (IOException ex) {
            System.out.println("Could not find save files.");
        } catch (IllegalArgumentException ex) {
            loginErrorLabel.setText(ex.getMessage());
            return;
        }
        loginUser(userName);
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

    public void slider(ActionEvent e) {
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.5), Side_Form);

        if (e.getSource() == SU_CreateButton) {
            Login_Form.setVisible(false);

            slide.setToY(450);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5 - slide.getDuration().toSeconds() / 2));
            pause.setOnFinished(ev -> {
                SignUp_Form.setVisible(true);
            });
            pause.play();

            slide.setOnFinished(ev -> {
                SignIn_Button.setVisible(true);
                SU_CreateButton.setVisible(false);
            });
        } else if (e.getSource() == SignIn_Button) {
            SignUp_Form.setVisible(false);

            slide.setToY(0);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5 - slide.getDuration().toSeconds() / 2));
            pause.setOnFinished(ev -> {
                Login_Form.setVisible(true);
            });
            pause.play();

            slide.setOnFinished(ev -> {
                SignIn_Button.setVisible(false);
                SU_CreateButton.setVisible(true);
            });
        }

        slide.play();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void displayScene() {
        // TODO Auto-generated method stub

    }
}
