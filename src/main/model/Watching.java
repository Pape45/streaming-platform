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
}
