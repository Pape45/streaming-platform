package main.model;

public class Episode {

    private static int episodeCounter = 0;

    private int episodeId;
    private String episodeName;
    private int episodeDuration;
    private int seasonId;

    public Episode(String episodeName, int episodeDuration, int seasonId) {
        this.episodeName = episodeName;
        this.episodeDuration = episodeDuration;
        this.seasonId = seasonId;
        this.episodeId = episodeCounter++;
    }
}
