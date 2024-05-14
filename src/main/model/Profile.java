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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

}
