package main.model;

public class Profile {

    private String profileImage;
    private int userId;
    private String profileName;
    private boolean isChild;

    public Profile(int userId, String profileName, String profileImage, boolean isChild) {
        this.userId = userId;
        this.profileName = profileName;
        this.profileImage = profileImage;
        this.isChild = isChild;
    }

    public int getUserId() {
        return userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public boolean isChild() {
        return isChild;
    }

    public String getProfileImage() {
        return this.profileImage;
    }
}
