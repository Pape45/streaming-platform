package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import main.model.Profile;
import main.util.DatabaseManager;

import java.util.List;

public class FirstTimeCreateProfileController {

    @FXML
    private Button logout;
    @FXML
    private Button createProfile;
    @FXML
    private CheckBox childProfile;
    @FXML
    private TextField profileName;
    @FXML
    private Label errorLabel;

    public void logout() {
        Main m = new Main();
        m.changeScene("login");
    }

    public void createProfile() {
        int userId = DatabaseManager.getUserID(Main.getCurrentUser().getLogin());
        List<Profile> profiles = DatabaseManager.getProfiles(userId);
        String newProfileName = profileName.getText();

        if (newProfileName.isEmpty()) {
            errorLabel.setText("Profile name cannot be empty.");
            return;
        }

        Profile newProfile = new Profile(userId, newProfileName, childProfile.isSelected());
        DatabaseManager.createProfile(newProfile.getUserId(), newProfile.getProfileName(), newProfile.isChild());
        Main m = new Main();
        m.changeScene("chooseProfile");
    }
}