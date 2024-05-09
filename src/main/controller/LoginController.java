package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import main.util.DatabaseManager;

import javafx.scene.input.MouseEvent; // Correct import
import java.io.IOException;

public class LoginController {

    public LoginController() {
    }

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button reset_btn;
    @FXML
    private Label wrongLogin;

    public void userLogin(MouseEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() {
        Main m = new Main();

        if (DatabaseManager.isValidCredentials(login.getText(), password.getText())) {
            Main.setCurrentUser(DatabaseManager.getUser(login.getText()));
            if (DatabaseManager.hasProfiles(DatabaseManager.getUserID(login.getText()))) {
                m.changeScene("chooseProfile");
            } else {
                m.changeScene("firstTimeCreateProfile");
            }
        } else {
            wrongLogin.setText("Wrong login or password");
        }
    }

    public void userRegister(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("register");
    }

    public void userReset(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("resetPage");
    }
}