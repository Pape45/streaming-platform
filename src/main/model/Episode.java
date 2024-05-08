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

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public int getEpisodeDuration() {
        return episodeDuration;
    }

    public void setEpisodeDuration(int episodeDuration) {
        this.episodeDuration = episodeDuration;
    }
}
