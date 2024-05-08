package main.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    private int userListCounter = 0;
    private int userListId;

    private int userId;
    private ArrayList<Integer> pieceId = new ArrayList<>();
    private String listName;

    public UserList(int userId, String listName) {
        this.userId = userId;
        this.listName = listName;
        this.userListId = userListCounter++;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getUserListId() {
        return userListId;
    }

    public void setUserListId(int userListId) {
        this.userListId = userListId;
    }

    public void addPieceId(int pieceId) {
        this.pieceId.add(pieceId);
    }

    public List<Integer> getPieceList() {
        return this.pieceId;
    }

}
