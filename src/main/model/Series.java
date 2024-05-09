package main.model;

import main.util.DatabaseManager;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Series extends Piece {

    private ArrayList<String> actors;

    public Series(String genre, String name, String description,
            String minAge, String releaseDate, String duration, String director, ArrayList<String> actors,
            String recommendationPercentage, String trailer, boolean downloadable) {
        super(genre, name, description, minAge, releaseDate, duration, director, recommendationPercentage, trailer,
                downloadable);
        this.actors = actors;
    }

    public void saveSeries() {
        this.savePiece();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO series (id, actors) VALUES (?, ?)")) {
            statement.setInt(1, getPiece_id());
            statement.setString(2, actors.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
