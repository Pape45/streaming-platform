package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import main.Main;
import main.util.DatabaseManager;

public class ChooseSubscriptionController {

    @FXML
    private Button continueBtn;
    @FXML
    private MenuButton menuSubscription;

    public void initialize() {

        continueBtn.setDisable(true);
        menuSubscription.getItems().clear();
        MenuItem singleUse = new MenuItem("Single Use");
        singleUse.setOnAction(event -> {
            menuSubscription.setText(singleUse.getText());
            continueBtn.setDisable(false);
        });

        MenuItem twoSimultaneousUse = new MenuItem("Two Simultaneous Use");
        twoSimultaneousUse.setOnAction(event -> {
            menuSubscription.setText(twoSimultaneousUse.getText());
            continueBtn.setDisable(false);
        });

        MenuItem fourSimultaneousUse = new MenuItem("Four Simultaneous Use");
        fourSimultaneousUse.setOnAction(event -> {
            menuSubscription.setText(fourSimultaneousUse.getText());
            continueBtn.setDisable(false);
        });

        menuSubscription.getItems().addAll(singleUse, twoSimultaneousUse, fourSimultaneousUse);
    }

    public void addSubscription() {
        String subscription = menuSubscription.getText();
        int subscriptionId = switch (subscription) {
            case "Single Use" -> 1;
            case "Two Simultaneous Use" -> 2;
            case "Four Simultaneous Use" -> 3;
            default -> 0;
        };

        if (subscriptionId != 0) {
            DatabaseManager.changeSubscription(Main.getCurrentUser().getLogin(), subscriptionId);

            if (subscriptionId == 2 || subscriptionId == 3) {
                DatabaseManager.createChildProfile(Main.getCurrentUser().getLogin());
                Main.setCurrentUser(DatabaseManager.getUser(Main.getCurrentUser().getLogin()));
                Main m = new Main();
                m.changeScene("chooseProfile");
            }

            Main m = new Main();
            m.changeScene("createProfile");

        } else {
            System.out.println("Erreur");
        }
    }

}