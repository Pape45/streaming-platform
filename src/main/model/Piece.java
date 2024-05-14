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

    public Piece(String genre, String name, String description, String minAge, String releaseDate, String duration, String director, String poster, String recommendationPercentage, String trailer, boolean downloadable) {
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

    public void setPiece_id(int piece_id) {
        this.piece_id = piece_id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRecommendationPercentage() {
        return recommendationPercentage;
    }

    public void setRecommendationPercentage(String recommendationPercentage) {
        this.recommendationPercentage = recommendationPercentage;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void savePiece() {
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Piece (id, genre, name, description, minAge, releaseDate, duration, poster, recommendationPercentage, trailer, downloadable, director) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
