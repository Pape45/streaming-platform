package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.util.DatabaseManager;

import java.io.IOException;


public class ResetPasswordController {

    public ResetPasswordController() {
    }

    @FXML
    private PasswordField passwordEntered;
    @FXML
    private PasswordField passwordConfirmed;
    @FXML
    private Label changeResult;

    public void resetPassword() {
        if (passwordEntered.getText().length() < 8) {
            changeResult.setText("Password must be at least 8 characters long");
        } else if (!passwordEntered.getText().equals(passwordConfirmed.getText())) {
            changeResult.setText("Passwords do not match");
        } else {
            DatabaseManager.changePassword(Main.getCurrentUser().getLogin() ,passwordEntered.getText());
            Main m = new Main();
            m.changeScene("resetSuccess");
        }
    }

    public void userLogin(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login");
    }

}
