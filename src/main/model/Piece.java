package main.model;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private static int pieceCounter = 0;

    private int id = pieceCounter++;
    private String genre;
    private String name;
    private String description;
    private String minAge;
    private String releaseDate;
    private String duration;
    private ArrayList<String> actors;
    private String recommendationPercentage;
    private String trailer;
    private boolean downloadable;

    public Piece(String genre, String name, String description, String minAge, String releaseDate, String duration,
            String recommendationPercentage, String trailer, boolean downloadable) {
        this.id = pieceCounter++;
        this.genre = genre;
        this.name = name;
        this.description = description;
        this.minAge = minAge;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.recommendationPercentage = recommendationPercentage;
        this.trailer = trailer;
        this.downloadable = downloadable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getActors() {
        return actors;
    }

    public void addActors(String actor) {
        this.actors.add(actor);
    }

    public String getRecommendationPercentage() {
        return recommendationPercentage;
    }

    public void setRecommendationPercentage(String recommendationPercentage) {
        this.recommendationPercentage = recommendationPercentage;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
    }
    
}
