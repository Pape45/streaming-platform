package main.util;

import main.model.Profile;
import main.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/streaming_platform";
    private static final String USERNAME = "papi";
    private static final String PASSWORD = "inuyascha45*";

    private static Connection connection;

    private DatabaseManager() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
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

    public void createChildProfile(int userId) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Profile (userId, profileName, isChild) VALUES (?, ?, ?)")) {
            statement.setInt(1, userId);
            statement.setString(2, "Child Profile");
            statement.setBoolean(3, true);
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

    public static void createProfile(int userId, String profileName, boolean isChild) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Profile (userId, profileName, isChild) VALUES (?, ?, ?)")) {
            statement.setInt(1, userId);
            statement.setString(2, profileName);
            statement.setBoolean(3, isChild);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProfile(int userId, String profileName) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM Profile WHERE userId = ? AND profileName = ?")) {
            statement.setInt(1, userId);
            statement.setString(2, profileName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProfile(int userId, String profileName, String newProfileName, boolean isChild) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Profile SET profileName = ?, isChild = ? WHERE userId = ? AND profileName = ?")) {
            statement.setString(1, newProfileName);
            statement.setBoolean(2, isChild);
            statement.setInt(3, userId);
            statement.setString(4, profileName);
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
                boolean isChild = resultSet.getBoolean("isChild");
                profiles.add(new Profile(userId, profileName, isChild));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profiles;
    }

}