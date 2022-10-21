package com.example.netflex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflex.adapter.MovieListAdapter;
import com.example.netflex.model.Movie;
import com.example.netflex.service.MovieService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MovieService movieService = new MovieService();
    private RecyclerView movieListView;
    private MovieListAdapter movieListAdapter;
    public static final String MOVIE_ID = "com.example.netflex.MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = findViewById(R.id.movie_list_view);
        movieListAdapter = new MovieListAdapter(this, movieService.getMovies());
        movieListView.setAdapter(movieListAdapter);
        movieListView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void launchFavouriteActivity(View view) {
        Intent intent = new Intent(this, FavouriteActivity.class);
        List<Movie> favoriteMovies = movieService.getFavoriteMovies();
        int[] favoriteMovieIds = favoriteMovies.stream().map(Movie::getId).mapToInt(Integer::intValue).toArray();

        if(favoriteMovies.size() > 0) {
            intent.putExtra(MOVIE_ID, favoriteMovieIds);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You have no favorite movies", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkFavourite(View view) {
        ConstraintLayout parent = (ConstraintLayout) view.getParent();
        int movieId = Integer.parseInt(parent.getTag().toString());

        Movie movie = movieService.getMovieById(movieId);
        if(movie.isFavorite()) {
            movieService.removeFromFavorites(movieId);
            ((ImageButton) view).setImageResource(R.drawable.heart_gradient);
            Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show();
        } else {
            movieService.addToFavorites(movieId);
            ((ImageButton) view).setImageResource(R.drawable.heart_red);
            Toast.makeText(this, "Added to favourites", Toast.LENGTH_SHORT).show();
        }
    }
}