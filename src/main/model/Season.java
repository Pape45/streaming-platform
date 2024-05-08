package main.model;

public class Season {

    private static int seasonCounter = 0;
    private int id;
    private int numberOfEpisodes;
    private int seriesId;
    
    public Season(int numberOfEpisodes, int seriesId) {
        this.numberOfEpisodes = numberOfEpisodes;
        this.seriesId = seriesId;
        this.id = seasonCounter++;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
