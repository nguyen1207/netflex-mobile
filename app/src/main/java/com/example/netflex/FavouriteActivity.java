package com.example.netflex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflex.adapter.MovieListAdapter;
import com.example.netflex.model.Movie;
import com.example.netflex.service.MovieService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FavouriteActivity extends AppCompatActivity {
    private MovieService movieService = new MovieService();
    private RecyclerView movieListView;
    private MovieListAdapter movieListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Intent intent = getIntent();
        int[] movieIds = intent.getIntArrayExtra(MainActivity.MOVIE_ID);

        List<Movie> movies = movieService.getMovies();
        List<Movie> favoriteMovies = movies.stream().filter(movie -> Arrays.stream(movieIds).anyMatch(id -> id == movie.getId())).collect(Collectors.toList());

        movieListView = findViewById(R.id.movie_list_view);
        movieListAdapter = new MovieListAdapter(this, favoriteMovies);
        movieListView.setAdapter(movieListAdapter);
        movieListView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void checkFavourite(View view) {}
}