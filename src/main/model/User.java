package main.model;

public class User {

    private static int userIdCounter = 0;

    private int userId;
    private String login;
    private String password;
    private String birthday;
    private String rib;
    private Subscription subscriptionType;

    public User(String login, String password, String birthday, String rib, Subscription subscriptionType) {
        this.userId = userIdCounter++;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.rib = rib;
        this.subscriptionType = subscriptionType;
    }

    public Subscription getsubscriptionType() {
        return subscriptionType;
    }

    public void setsubscriptionType(Subscription subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
