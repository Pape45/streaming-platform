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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
