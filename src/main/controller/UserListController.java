package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.model.UserList;
import main.util.DatabaseManager;

import java.util.List;

public class UserListController {

    @FXML
    private Button home;
    @FXML
    private Label profileName;
    @FXML
    private ImageView profileImage;
    @FXML
    private GridPane gridPane;

    public void initialize() {
        profileName.setText(main.Main.getCurrentProfile().getProfileName());
        String profileImagePath = main.Main.getCurrentProfile().getProfileImage();
        Image image = new Image("file:src/main/img/" + profileImagePath);
        profileImage.setImage(image);

        List<UserList> userLists = DatabaseManager.getUserLists(DatabaseManager.getUserID(main.Main.getCurrentProfile().getProfileName()));

        for (int i = 0; i < userLists.size(); i++) {
            UserList userList = userLists.get(i);

            Button listButton = new Button(userList.getListName());

            listButton.setOnAction(event -> {
                main.Main.setCurrentList(userList);
                main.Main m = new main.Main();
                m.changeScene("listDetailsPage");
            });

            gridPane.add(listButton, 0, i);
        }
    }

    public void returnHome() {
        main.Main m = new main.Main();
        m.changeScene("homePage");
    }

    public void createList() {
        main.Main m = new main.Main();
        m.changeScene("createListPage");
    }


}

