package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import main.Main;
import main.util.DatabaseManager;
import main.model.Profile;

import java.util.List;

public class ChooseProfileController {

    @FXML
    private GridPane profileGrid;
    @FXML
    private Label errorLabel;

    public void logout() {
        Main m = new Main();
        m.changeScene("login");
    }

    public void createProfile() {
        int userId = DatabaseManager.getUserID(Main.getCurrentUser().getLogin());
        List<Profile> profiles = DatabaseManager.getProfiles(userId);
        if (profiles.size() >= Main.getCurrentUser().getMaxProfiles()) {
            errorLabel.setText("Maximum number of profiles based on your subscription.");
        } else {
            Main m = new Main();
            m.changeScene("createProfile");
        }
    }

    public void initialize() {
        int userId = DatabaseManager.getUserID(Main.getCurrentUser().getLogin());
        List<Profile> profiles = DatabaseManager.getProfiles(userId);

        profiles.sort((profile1, profile2) -> {
            if (profile1.isChild() && !profile2.isChild()) {
                return 1;
            } else if (!profile1.isChild() && profile2.isChild()) {
                return -1;
            } else {
                return 0;
            }
        });

        profileGrid.setVgap(30);

        for (int i = 0; i < profiles.size(); i++) {
            Profile profile = profiles.get(i);

            Image image = new Image("file:src/main/img/" + profile.getProfileImage());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            Label label = new Label(profile.getProfileName());
            label.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
            VBox vBox = new VBox(imageView, label);
            vBox.setSpacing(20);
            vBox.setAlignment(Pos.CENTER);

            vBox.setOnMouseClicked(event -> {
                Main.setCurrentProfile(profile);
                Main m = new Main();
                    m.changeScene("homePage");
            });

            profileGrid.add(vBox, i % 2, i / 2);
        }
    }



}