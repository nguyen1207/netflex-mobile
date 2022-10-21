package com.example.netflex.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Movie implements Comparable<Movie> {
    private final int id;
    private String name;
    private String description;
    private int poster;
    private float score;
    private float price;
    private String director;
    private String country;
    private Date releaseDate;
    private String trailerUrl;
    private boolean isFavorite;

    private static int idCounter = 0;

    private Movie() {
        this.id = ++idCounter;
        this.isFavorite = false;
    }

    @Override
    public int compareTo(Movie movie) {
        return this.score - movie.score > 0 ? 1 : -1;
    }

    @NonNull
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", score=" + score +
                ", price=" + price +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate=" + releaseDate +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }

    public static class MovieBuilder {
        private String name;
        private String description;
        private int poster;
        private float score;
        private float price;
        private String director;
        private String country;
        private Date releaseDate;
        private String trailerUrl;

        public MovieBuilder() {}

        public MovieBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public MovieBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public MovieBuilder setPoster(int poster) {
            this.poster = poster;
            return this;
        }

        public MovieBuilder setScore(float score) {
            this.score = score;
            return this;
        }

        public MovieBuilder setPrice(float price) {
            this.price = price;
            return this;
        }

        public MovieBuilder setDirector(String director) {
            this.director = director;
            return this;
        }

        public MovieBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public MovieBuilder setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder setTrailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.name = this.name;
            movie.description = this.description;
            movie.poster = this.poster;
            movie.score = this.score;
            movie.price = this.price;
            movie.director = this.director;
            movie.country = this.country;
            movie.releaseDate = this.releaseDate;
            movie.trailerUrl = this.trailerUrl;
            return movie;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
