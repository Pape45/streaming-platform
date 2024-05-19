package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.model.User;
import main.util.DatabaseManager;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterController {

    public RegisterController() {
    }

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField rib;
    @FXML
    private Button register_btn;
    @FXML
    private Label wrongRegister;
    @FXML
    private Button login_page;

    public void userRegister(MouseEvent event) throws IOException {
        checkRegister();
    }

    private void checkRegister() {
        if (email.getText().isEmpty() || password.getText().isEmpty() || birthday.getValue() == null || rib.getText().isEmpty()) {
            wrongRegister.setText("Please fill all the fields");
        } else if (!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            wrongRegister.setText("Invalid email format");
        } else if (birthday.getValue().isAfter(LocalDate.now().minusYears(13))) {
            wrongRegister.setText("You must be at least 13 years old");
        } else if (DatabaseManager.getUser(email.getText()) != null) {
            wrongRegister.setText("User already exists");
        } else {
            Main m = new Main();
            int subscriptionID = DatabaseManager.getSubscriptionID("Single Use");
            User user = new User(email.getText(), password.getText(), birthday.getValue().toString(), rib.getText(), subscriptionID);
            user.saveUser();
            Main.setCurrentUser(user);
            m.changeScene("chooseSubscription");
        }
    }

    public void userLogin(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login");
    }


}
