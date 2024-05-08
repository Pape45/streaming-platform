package main.model;

public class Documentary extends Piece {

    public Documentary(String genre, String name, String description,
            String minAge, String releaseDate, String duration,
            String recommendationPercentage, String trailer, boolean downloadable) {
        super(genre, name, description, minAge, releaseDate, duration, recommendationPercentage, trailer,
                downloadable);
    }
}
