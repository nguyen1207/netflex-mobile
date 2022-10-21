package com.example.netflex.service;

import com.example.netflex.model.Movie;
import com.example.netflex.repository.MovieRepository;

import java.util.List;

public class MovieService {
    private final MovieRepository movieRepository = MovieRepository.getInstance();

    public List<Movie> getMovies() {
        return movieRepository.getMovies();
    }

    public List<Movie> getFavoriteMovies() {
        return movieRepository.getFavoriteMovies();
    }

    public Movie addToFavorites(int id) {
        return movieRepository.addToFavorites(id);
    }

    public Movie removeFromFavorites(int id) {
        return movieRepository.removeFromFavorites(id);
    }

    public Movie getMovieById(int id) {
        return movieRepository.getMovieById(id);
    }
}
