package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import main.Main;
import main.model.Piece;

import java.io.File;

public class VideoPlayerController {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button return_btn;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;

    private MediaPlayer mediaPlayer;

    public void initialize() {
        Piece piece = Main.getCurrentPiece();
        File videoFile = new File("src/main/video/" + piece.getVideoUrl());
        String videoUrl = videoFile.toURI().toString();
        Media media = new Media(videoUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaPlayer.play();
    }

    public void playVideo() {
        mediaPlayer.play();
    }

    public void pauseVideo() {
        mediaPlayer.pause();
    }

    public void stopVideo() {
        mediaPlayer.stop();
    }

    public void returnToHome() {
        mediaPlayer.stop();
        Main m = new Main();
        m.changeScene("homePage");
    }
}