package main.model;

public class Evaluation {

    private static int evaluationCounter = 0;

    private int evaluationId;
    private int userId;
    private int pieceId;
    private int score;

    public Evaluation(int userId, int pieceId) {
        this.userId = userId;
        this.pieceId = pieceId;
        this.evaluationId = evaluationCounter++;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
