package main.model;

import main.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {

    private String login;
    private String password;
    private String birthday;
    private String rib;
    private int subscriptionId;

    public User(String login, String password, String birthday, String rib, int subscriptionId) {
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.rib = rib;
        this.subscriptionId = subscriptionId;
    }

    public int getSubscriptionId() {
        return this.subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public void saveUser() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();
            statement = connection.prepareStatement("INSERT INTO user (login, password, birthday, rib, subscriptionID) VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, this.login);
            statement.setString(2, this.password);
            statement.setString(3, this.birthday);
            statement.setString(4, this.rib);
            statement.setInt(5, this.subscriptionId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public int getMaxProfiles() {
        return switch (this.subscriptionId) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            default -> 0;
        };
    }
}
