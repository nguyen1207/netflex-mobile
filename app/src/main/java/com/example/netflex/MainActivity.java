package com.example.netflex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.netflex.adapter.MovieListAdapter;
import com.example.netflex.service.MovieService;

public class MainActivity extends AppCompatActivity {
    private MovieService movieService = new MovieService();
    private RecyclerView movieListView;
    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = findViewById(R.id.movie_list_view);
        movieListAdapter = new MovieListAdapter(this, movieService.getMovies());
        movieListView.setAdapter(movieListAdapter);
        movieListView.setLayoutManager(new LinearLayoutManager(this));
    }
}