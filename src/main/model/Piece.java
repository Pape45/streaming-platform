package main.model;

import main.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Piece {

    private static int pieceCounter = 0;

    private int piece_id;
    private String genre;
    private String name;
    private String description;
    private String minAge;
    private String releaseDate;
    private String duration;
    private String director;
    private String poster;
    private String recommendationPercentage;
    private String trailer;
    private boolean downloadable;
    private String videoUrl;

    public Piece(String genre, String name, String description, String minAge, String releaseDate, String duration, String director, String poster, String recommendationPercentage, String trailer, boolean downloadable, String videoUrl) {
        this.piece_id = pieceCounter++;
        this.genre = genre;
        this.name = name;
        this.description = description;
        this.minAge = minAge;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.director = director;
        this.poster = poster;
        this.recommendationPercentage = recommendationPercentage;
        this.trailer = trailer;
        this.downloadable = downloadable;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPiece_id() {
        return piece_id;
    }

    public String getRecommendationPercentage() {
        return recommendationPercentage;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public String getPoster() {
        return poster;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void savePiece() {
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Piece (id, genre, name, description, minAge, releaseDate, duration, poster, recommendationPercentage, trailer, downloadable, director, videoUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, piece_id);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, minAge);
            preparedStatement.setString(6, releaseDate);
            preparedStatement.setString(7, duration);
            preparedStatement.setString(8, poster);
            preparedStatement.setString(9, recommendationPercentage);
            preparedStatement.setString(10, trailer);
            preparedStatement.setBoolean(11, downloadable);
            preparedStatement.setString(12, director);
            preparedStatement.setString(13, videoUrl);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
