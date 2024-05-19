package main.util;

import main.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/streaming_platform";
    private static final String USERNAME = "papi";
    private static final String PASSWORD = "inuyascha45*";

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
                    "    poster VARCHAR(255)," +
                    "    minAge INT," +
                    "    releaseDate TEXT," +
                    "    duration TEXT," +
                    "    recommendationPercentage TEXT," +
                    "    trailer VARCHAR(255)," +
                    "    downloadable BOOLEAN," +
                    "    videoUrl TEXT" +
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
                    "    pieceId INT," +
                    "    listName VARCHAR(255)," +
                    "    FOREIGN KEY (pieceId) REFERENCES Piece(id)," +
                    "    FOREIGN KEY (userId) REFERENCES User(userId)" +
                    ");" +
                    "CREATE TABLE Profile (" +
                    "    profileId INT PRIMARY KEY AUTO_INCREMENT," +
                    "    userId INT," +
                    "    profileName VARCHAR(255)," +
                    "    isChild BOOLEAN," +
                    "    profileImage TEXT," +
                    "    FOREIGN KEY (userId) REFERENCES User(userId)" +
                    ");";

    private static Connection connection;

    private DatabaseManager() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }

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

    public static ResultSet executeQuery(String query, String... parameters) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        for (int i = 0; i < parameters.length; i++) {
            statement.setString(i + 1, parameters[i]);
        }

        return statement.executeQuery();
    }

    public static void executeUpdate(String query, String... parameters) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < parameters.length; i++) {
                statement.setString(i + 1, parameters[i]);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getSubscriptionID(String subscriptionName) {
        int subscriptionID = 0;

        try (ResultSet resultSet = executeQuery("SELECT * FROM subscription WHERE subscriptionName = ?", subscriptionName)) {

            try {
                if (resultSet.next()) {
                    subscriptionID = resultSet.getInt("subscriptionID");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscriptionID;
    }

    public static int getUserID(String login) {
        int userID = 0;

        try (ResultSet resultSet = executeQuery("SELECT * FROM user WHERE login = ?", login)) {

            try {
                if (resultSet.next()) {
                    userID = resultSet.getInt("userID");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userID;
    }

    public static boolean isValidCredentials(String login, String password) {
        boolean isValidUser = false;

        try (ResultSet resultSet = executeQuery("SELECT * FROM user WHERE login = ? AND password = ?", login, password)) {

            try {
                isValidUser = resultSet.next();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isValidUser;
    }

    public static User getUser(String login) {
        try (ResultSet resultSet = executeQuery("SELECT * FROM user WHERE login = ?", login)) {
            try {
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("subscriptionId"));

                    return new User(resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("birthday"), resultSet.getString("rib"), resultSet.getInt("subscriptionId"));
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void changePassword(String login, String newPassword) {
        executeUpdate("UPDATE user SET password = ? WHERE login = ?", newPassword, login);
    }

    public static void createChildProfile(String userLogin) {
        int userId = getUserID(userLogin);
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Profile (userId, profileName, isChild, profileImage) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, userId);
            statement.setString(2, "Child Profile");
            statement.setBoolean(3, true);
            statement.setString(4, "child.png");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasProfiles(int userId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM Profile WHERE userId = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createProfile(int userId, String profileName, String profileImage, boolean isChild) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Profile (userId, profileName, profileImage, isChild) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, userId);
            statement.setString(2, profileName);
            statement.setString(3, profileImage);
            statement.setBoolean(4, isChild);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Profile> getProfiles(int userId) {
        List<Profile> profiles = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM Profile WHERE userId = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String profileName = resultSet.getString("profileName");
                String profileImage = resultSet.getString("profileImage");
                boolean isChild = resultSet.getBoolean("isChild");
                profiles.add(new Profile(userId, profileName, profileImage, isChild));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profiles;
    }

    public static <T extends Piece> List<T> getPiecesForKids(Class<T> pieceClass, String query) {
        List<T> pieces = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String minAge = resultSet.getString("minAge");
                String releaseDate = resultSet.getString("releaseDate");
                String duration = resultSet.getString("duration");
                String director = resultSet.getString("director");
                String poster = resultSet.getString("poster");
                String recommendationPercentage = resultSet.getString("recommendationPercentage");
                String trailer = resultSet.getString("trailer");
                boolean downloadable = resultSet.getBoolean("downloadable");
                String videoUrl = resultSet.getString("videoUrl");

                if (pieceClass == Movies.class || pieceClass == Series.class) {
                    String actorsString = resultSet.getString("actors");
                    ArrayList<String> actors = new ArrayList<>(Arrays.asList(actorsString.split(", ")));
                    if (pieceClass == Movies.class) {
                        pieces.add(pieceClass.cast(new Movies(genre, name, description, minAge, releaseDate, duration, director, poster, actors, recommendationPercentage, trailer, downloadable, videoUrl)));
                    } else {
                        pieces.add(pieceClass.cast(new Series(genre, name, description, minAge, releaseDate, duration, director, poster, actors, recommendationPercentage, trailer, downloadable, videoUrl)));
                    }
                } else if (pieceClass == Documentary.class) {
                    pieces.add(pieceClass.cast(new Documentary(genre, name, description, minAge, releaseDate, duration, poster, director, recommendationPercentage, trailer, downloadable, videoUrl)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pieces;
    }

    public static List<Movies> getMovies(boolean kids) {
        String query;
        if (kids) {
            query = "SELECT * FROM Piece, Movies WHERE Piece.id = Movies.id AND Piece.minAge <= 10";
        } else {
            query = "SELECT * FROM Piece, Movies WHERE Piece.id = Movies.id AND Piece.minAge > 10";
        }
        return getPiecesForKids(Movies.class, query);
    }

    public static List<Series> getSeries(boolean kids) {
        String query;
        if (kids) {
            query = "SELECT * FROM Piece, Series WHERE Piece.id = Series.id AND Piece.minAge <= 10";
        } else {
            query = "SELECT * FROM Piece, Series WHERE Piece.id = Series.id AND Piece.minAge > 10";
        }
        return getPiecesForKids(Series.class, query);
    }

    public static List<Documentary> getDocumentaries(boolean kids) {
        String query;
        if (kids) {
            query = "SELECT * FROM Piece, Documentary WHERE Piece.id = Documentary.id AND Piece.minAge <= 10";
        } else {
            query = "SELECT * FROM Piece, Documentary WHERE Piece.id = Documentary.id AND Piece.minAge > 10";
        }
        return getPiecesForKids(Documentary.class, query);
    }

    public static String getSubscriptionName(int subscriptionId) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT subscriptionName FROM Subscription WHERE subscriptionId = ?")) {
            statement.setInt(1, subscriptionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("subscriptionName");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeSubscription(String login, int subscriptionId) {
        executeUpdate("UPDATE user SET subscriptionId = ? WHERE login = ?", String.valueOf(subscriptionId), login);
    }

    public static List<UserList> getUserLists(int userId) {
        List<UserList> userLists = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM UserList WHERE userId = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int pieceId = resultSet.getInt("pieceId");
                String listName = resultSet.getString("listName");
                userLists.add(new UserList(userId, listName, pieceId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userLists;
    }

    public static void saveListEntry(int userId, String listName, String pieceName) {
        int pieceId = 0;
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM Piece WHERE name = ?")) {
            statement.setString(1, pieceName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pieceId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO UserList (userId, pieceId, listName) VALUES (?, ?, ?)")) {
            statement.setInt(1, userId);
            statement.setInt(2, pieceId);
            statement.setString(3, listName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Piece> getPieces(int userId) {
        List<Piece> pieces = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM Piece WHERE id IN (SELECT pieceId FROM UserList WHERE userId = ?)")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String minAge = resultSet.getString("minAge");
                String releaseDate = resultSet.getString("releaseDate");
                String duration = resultSet.getString("duration");
                String director = resultSet.getString("director");
                String poster = resultSet.getString("poster");
                String recommendationPercentage = resultSet.getString("recommendationPercentage");
                String trailer = resultSet.getString("trailer");
                boolean downloadable = resultSet.getBoolean("downloadable");
                String videoUrl = resultSet.getString("videoUrl");
                pieces.add(new Piece(genre, name, description, minAge, releaseDate, duration, director, poster, recommendationPercentage, trailer, downloadable, videoUrl));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pieces;
    }
}