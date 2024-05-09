package main.model;

import main.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Subscription {

    private String subscriptionName;

    public Subscription(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public void saveSubscription() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();
            statement = connection.prepareStatement("INSERT INTO subscription (subscriptionName) VALUES (?)");
            statement.setString(1, this.subscriptionName);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving subscription: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public String getSubscriptionName() {
        return this.subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }
}
