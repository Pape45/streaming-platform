package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import main.Main;
import main.util.DatabaseManager;

public class ChangeSubscriptionController {

    @FXML
    private Label actualSubscription;
    @FXML
    private MenuButton subscriptionChoice;

    public void initialize() {
        actualSubscription.setText(DatabaseManager.getSubscriptionName(Main.getCurrentUser().getSubscriptionId()));
        actualSubscription.setStyle("-fx-font-weight: bold;");
        subscriptionChoice.getItems().clear();

        MenuItem singleUse = new MenuItem("Single Use");
        singleUse.setOnAction(event -> subscriptionChoice.setText(singleUse.getText()));
        MenuItem twoSimultaneousUse = new MenuItem("Two Simultaneous Use");
        twoSimultaneousUse.setOnAction(event -> subscriptionChoice.setText(twoSimultaneousUse.getText()));
        MenuItem fourSimultaneousUse = new MenuItem("Four Simultaneous Use");
        fourSimultaneousUse.setOnAction(event -> subscriptionChoice.setText(fourSimultaneousUse.getText()));

        subscriptionChoice.getItems().addAll(singleUse, twoSimultaneousUse, fourSimultaneousUse);
    }

    public void changeSubscription() {
        String subscription = subscriptionChoice.getText();
        int subscriptionId = switch (subscription) {
            case "Single Use" -> 1;
            case "Two Simultaneous Use" -> 2;
            case "Four Simultaneous Use" -> 3;
            default -> 0;
        };

        int currentSubscriptionId = Main.getCurrentUser().getSubscriptionId();
        DatabaseManager.changeSubscription(Main.getCurrentUser().getLogin(), subscriptionId);

        if (currentSubscriptionId == 1 && (subscriptionId == 2 || subscriptionId == 3)) {
            DatabaseManager.createChildProfile(Main.getCurrentUser().getLogin());
            Main m = new Main();
            m.changeScene("chooseProfile");
        } else {
            Main m = new Main();
            m.changeScene("homePage");
        }
    }

    public void cancel() {
        Main m = new Main();
        m.changeScene("homePage");
    }

}
