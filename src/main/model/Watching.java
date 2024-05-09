package main.model;

public class Watching {

    private int userId;
    private int pieceId;
    private int position;
    
    public Watching(int userId, int pieceId, int position) {
        this.userId = userId;
        this.pieceId = pieceId;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
