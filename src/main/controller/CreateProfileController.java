package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import main.Main;
import main.model.Profile;
import main.util.DatabaseManager;

import java.util.List;

public class CreateProfileController {

    @FXML
    private Button logout;
    @FXML
    private Button createProfile;
    @FXML
    private CheckBox childProfile;
    @FXML
    private TextField profileName;
    @FXML
    private Button cancel;
    @FXML
    private Label errorLabel;

    public void initialize() {
        int userId = DatabaseManager.getUserID(Main.getCurrentUser().getLogin());
        List<Profile> profiles = DatabaseManager.getProfiles(userId);
        boolean childExists = profiles.stream().anyMatch(Profile::isChild);
        childProfile.setVisible(!childExists);

        if (childExists) {
            createProfile.setLayoutY(createProfile.getLayoutY() - 50);
            errorLabel.setLayoutY(errorLabel.getLayoutY() - 50);
        }
    }

    public void logout() {
        Main m = new Main();
        m.changeScene("login");
    }

    public void cancel() {
        Main m = new Main();
        m.changeScene("chooseProfile");
    }

    public void createProfile() {
        int userId = DatabaseManager.getUserID(Main.getCurrentUser().getLogin());
        List<Profile> profiles = DatabaseManager.getProfiles(userId);
        String newProfileName = profileName.getText();

        if (newProfileName.isEmpty()) {
            errorLabel.setText("Profile name cannot be empty.");
            return;
        }

        boolean nameExists = profiles.stream().anyMatch(p -> p.getProfileName().equals(newProfileName));
        if (nameExists) {
            errorLabel.setText("Profile name already exists.");
        } else {
            Profile newProfile = new Profile(userId, newProfileName, childProfile.isSelected());
            DatabaseManager.createProfile(newProfile.getUserId(), newProfile.getProfileName(), newProfile.isChild());
            Main m = new Main();
            m.changeScene("chooseProfile");
        }
    }
}