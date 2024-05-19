package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import main.model.Piece;
import main.util.DatabaseManager;

public class CreateListController {

    @FXML
    private TextField listName;
    @FXML
    private Button createButton;
    @FXML
    private Button cancel;
    @FXML
    private GridPane gridPane;

    private List<CheckBox> checkBoxes;

    public void initialize() {
        List<Piece> pieces = DatabaseManager.getPieces(DatabaseManager.getUserID(main.Main.getCurrentUser().getLogin()));
        checkBoxes = new ArrayList<>(); // Initialize checkBoxes here

        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);

            CheckBox pieceCheckBox = new CheckBox(piece.getName());
            checkBoxes.add(pieceCheckBox);
            gridPane.add(pieceCheckBox, 0, i);
        }

        createButton.setOnAction(event -> {
            createList();
        });
    }

    public void createList() {
        String listNameText = listName.getText();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                DatabaseManager.saveListEntry(DatabaseManager.getUserID(main.Main.getCurrentUser().getLogin()), listNameText, checkBox.getText());
            }
        }

        main.Main m = new main.Main();
        m.changeScene("userList");
    }

    public void cancel() {
        main.Main m = new main.Main();
        m.changeScene("userList");
    }
}