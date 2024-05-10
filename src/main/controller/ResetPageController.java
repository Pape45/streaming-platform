package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.util.DatabaseManager;

import java.io.IOException;

public class ResetPageController {

    public ResetPageController() {
    }

    @FXML
    private TextField email;
    @FXML
    private Label wrongReset;

    public void resetPassword(MouseEvent event) throws IOException {
        checkReset();
    }

    private void checkReset() {
        if (email.getText().isEmpty()) {
            wrongReset.setText("Please fill the email field");
        } else if (!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            wrongReset.setText("Invalid email format");
        } else if (DatabaseManager.getUser(email.getText()) == null) {
            wrongReset.setText("User does not exist");
        } else {
            Main m = new Main();
            Main.setCurrentUser(DatabaseManager.getUser(email.getText()));
            m.changeScene("resetPassword");
        }
    }

    public void userLogin(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("login");
    }

}
