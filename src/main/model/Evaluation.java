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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
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
