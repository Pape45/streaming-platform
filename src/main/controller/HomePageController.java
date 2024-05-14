package main.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import main.Main;
import main.model.*;
import main.util.DatabaseManager;

import java.util.List;

public class HomePageController {

    @FXML
    private Button logout_btn;
    @FXML
    private Button movies_btn;
    @FXML
    private Button series_btn;
    @FXML
    private Button documentaries_btn;
    @FXML
    private GridPane videoGrid;
    @FXML
    private VBox profileBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button changeProfile_btn;
    @FXML
    private Button changeSubscription_btn;

    public void initialize() {
        movies_btn.setOnAction(event -> updateGridWithMovies());
        series_btn.setOnAction(event -> updateGridWithSeries());
        documentaries_btn.setOnAction(event -> updateGridWithDocumentaries());
        updateGridWithMovies();
        updateProfile();
    }

    public void logout() {
        Main m = new Main();
        m.changeScene("login");
    }

    private void updateGridWithMovies() {
        boolean isChild = Main.getCurrentProfile().isChild();
        List<Movies> movies = DatabaseManager.getMovies(isChild);
        updateGrid(movies);
    }

    private void updateGridWithSeries() {
        boolean isChild = Main.getCurrentProfile().isChild();
        List<Series> series = DatabaseManager.getSeries(isChild);
        updateGrid(series);
    }

    private void updateGridWithDocumentaries() {
        boolean isChild = Main.getCurrentProfile().isChild();
        List<Documentary> documentaries = DatabaseManager.getDocumentaries(isChild);
        updateGrid(documentaries);
    }

    private String getRecommendationColor(String recommendationPercentage) {
        String percentageWithoutSign = recommendationPercentage.replace("%", "");
        double percentage = Double.parseDouble(percentageWithoutSign) / 100.0;
        int r = (int) (255 * (1 - percentage));
        int g = (int) (255 * percentage);
        int b = 0;
        return String.format("#%02x%02x%02x", r, g, b);
    }

    private void updateGrid(List<? extends Piece> pieces) {
        videoGrid.getChildren().clear();
        videoGrid.getRowConstraints().clear();

        int row = 0;
        int column = 0;
        for (Piece piece : pieces) {
            VBox videoBox = new VBox();

            Image image = new Image("file:src/main/img/" + piece.getPoster());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(130);
            imageView.setFitHeight(180);

            Label label = new Label(piece.getName());
            label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white;");
            label.setTranslateY(10);

            Label recommendationLabel = new Label(piece.getRecommendationPercentage());
            recommendationLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: " + getRecommendationColor(piece.getRecommendationPercentage()) + ";");
            recommendationLabel.setTranslateY(12);

            if (piece.isDownloadable()) {
                Image downloadImage = new Image("file:src/main/img/downloadable.png");
                ImageView downloadImageView = new ImageView(downloadImage);
                downloadImageView.setFitWidth(20);
                downloadImageView.setFitHeight(20);
                videoBox.getChildren().add(downloadImageView);
            }

            videoBox.getChildren().addAll(imageView, label, recommendationLabel);
            videoBox.setAlignment(Pos.CENTER);

            videoGrid.add(videoBox, column, row);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(250);
            videoGrid.getRowConstraints().add(rowConstraints);

            column++;
            if (column > 2) {
                column = 0;
                row++;
            }
        }
    }

    private void updateProfile() {
        Profile profile = Main.getCurrentProfile();

        Image profileImage = new Image("file:src/main/img/" + profile.getProfileImage());
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(120);
        profileImageView.setFitHeight(120);

        Label nameLabel = new Label(profile.getProfileName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        nameLabel.setTranslateY(10);

        profileBox.getChildren().clear();
        profileBox.getChildren().addAll(profileImageView, nameLabel);
        profileBox.setAlignment(Pos.CENTER);
        profileBox.setSpacing(10);
    }

    public void changeProfile() {
        Main m = new Main();
        m.changeScene("chooseProfile");
    }

    public void changeSubscription() {
        System.out.println("...");
    }
}