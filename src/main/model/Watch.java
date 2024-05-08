package main.model;

public class Watch {

    private static int watchCounter = 0;
    private int watchId;

    private int userId;
    private int pieceId;
    
    public Watch(int userId, int pieceId) {
        this.userId = userId;
        this.pieceId = pieceId;
        this.watchId = watchCounter++;
    }

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPieceId() {
        return pieceId;
    }

    public void setPieceId(int pieceId) {
        this.pieceId = pieceId;
    }
}
