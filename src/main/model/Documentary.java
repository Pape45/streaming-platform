package main.model;

import main.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Documentary extends Piece {

    public Documentary(String genre, String name, String description,
            String minAge, String releaseDate, String duration, String poster, String director,
            String recommendationPercentage, String trailer, boolean downloadable) {
        super(genre, name, description, minAge, releaseDate, duration, director, poster, recommendationPercentage, trailer,
                downloadable);
    }

    public void saveDocumentary() {
        this.savePiece();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO documentary (id) VALUES (?)")) {
            statement.setInt(1, getPiece_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
