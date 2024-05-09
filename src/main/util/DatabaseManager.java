package main.util;

import java.sql.*;

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

    public static boolean isValidUser(String login, String password) {
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
}