package main.model;

import main.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserList {

    private int userId;
    private int pieceId;
    private String listName;

    public UserList(int userId, String listName, int pieceId) {
        this.userId = userId;
        this.listName = listName;
        this.pieceId = pieceId;
    }

    public String getListName() {
        return listName;
    }

    public void saveList() {
        String query = "INSERT INTO UserList (userId, pieceId, listName) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, this.userId);
            statement.setInt(2, this.pieceId);
            statement.setString(3, this.listName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user list");
        }
    }
}
