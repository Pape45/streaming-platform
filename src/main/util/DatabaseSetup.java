package main.util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {

    private static final String SETUP_QUERY =
            "DROP DATABASE IF EXISTS streaming_platform;" +
                    "CREATE DATABASE streaming_platform;" +
                    "USE streaming_platform;" +
                    "CREATE TABLE Subscription (" +
                    "    subscriptionId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    subscriptionName VARCHAR(255)" +
                    ");" +
                    "CREATE TABLE User (" +
                    "    userId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    login VARCHAR(255) NOT NULL," +
                    "    password VARCHAR(255) NOT NULL," +
                    "    birthday TEXT," +
                    "    rib VARCHAR(255)," +
                    "    subscriptionId INT," +
                    "    FOREIGN KEY (subscriptionId) REFERENCES Subscription(subscriptionId)" +
                    ");" +
                    "CREATE TABLE Piece (" +
                    "    id INT PRIMARY KEY," +
                    "    genre VARCHAR(255)," +
                    "    name VARCHAR(255)," +
                    "    description TEXT," +
                    "    director VARCHAR(255)," +
                    "    minAge INT," +
                    "    releaseDate TEXT," +
                    "    duration TEXT," +
                    "    recommendationPercentage TEXT," +
                    "    trailer VARCHAR(255)," +
                    "    downloadable BOOLEAN" +
                    ");" +
                    "CREATE TABLE Watching (" +
                    "    watchId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    userId INT," +
                    "    pieceId INT," +
                    "    position INT," +
                    "    FOREIGN KEY (userId) REFERENCES User(userId)," +
                    "    FOREIGN KEY (pieceId) REFERENCES Piece(id)" +
                    ");" +
                    "CREATE TABLE Series (" +
                    "    id INT PRIMARY KEY," +
                    "    actors VARCHAR(255)," +
                    "    FOREIGN KEY (id) REFERENCES Piece(id)" +
                    ");" +
                    "CREATE TABLE Movies (" +
                    "    id INT PRIMARY KEY," +
                    "    actors VARCHAR(255)," +
                    "    FOREIGN KEY (id) REFERENCES Piece(id)" +
                    ");" +
                    "CREATE TABLE Documentary (" +
                    "    id INT PRIMARY KEY," +
                    "    FOREIGN KEY (id) REFERENCES Piece(id)" +
                    ");" +
                    "CREATE TABLE Season (" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT," +
                    "    numberOfEpisodes INT," +
                    "    seriesId INT," +
                    "    FOREIGN KEY (seriesId) REFERENCES Series(id)" +
                    ");" +
                    "CREATE TABLE Episode (" +
                    "    episodeId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    episodeName VARCHAR(255)," +
                    "    episodeDuration INT," +
                    "    seasonId INT," +
                    "    FOREIGN KEY (seasonId) REFERENCES Season(id)" +
                    ");" +
                    "CREATE TABLE Evaluation (" +
                    "    evaluationId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    userId INT," +
                    "    pieceId INT," +
                    "    score INT," +
                    "    FOREIGN KEY (userId) REFERENCES User(userId)," +
                    "    FOREIGN KEY (pieceId) REFERENCES Piece(id)" +
                    ");" +
                    "CREATE TABLE UserList (" +
                    "    userListId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    userId INT," +
                    "    listName VARCHAR(255)," +
                    "    FOREIGN KEY (userId) REFERENCES User(userId)" +
                    ");" +
                    "CREATE TABLE Profile (" +
                    "    profileId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    userId INT," +
                    "    profileName VARCHAR(255)," +
                    "    isChild BOOLEAN," +
                    "    FOREIGN KEY (userId) REFERENCES User(userId)" +
                    ");";

    public static void setupDatabase() {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {

            String[] queries = SETUP_QUERY.split(";");

            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error setting up database: " + e.getMessage(), e);
        }
    }
}