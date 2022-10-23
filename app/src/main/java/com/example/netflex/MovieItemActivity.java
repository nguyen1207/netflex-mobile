package com.example.netflex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netflex.model.Movie;
import com.example.netflex.service.MovieService;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieItemActivity extends AppCompatActivity{
    private ImageView moviePoster;
    private ImageView posterBg;
    private TextView movieTitle;
    private TextView movieRating;
    private TextView movieCountry;
    private TextView movieDirector;
    private TextView movieDesc;
    private TextView movieResDate;
    private Button btnBookingCGV;
    private Button btnBookingBHD;
    private Button btnTrailer;
    SimpleDateFormat sdf;
    private MovieService movieService = new MovieService();

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_item);
        setupView();
        setupViewInfo();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setupView() {
        movieTitle = findViewById(R.id.movie_item_title);
        movieRating = findViewById(R.id.movie_item_rating);
        movieCountry = findViewById(R.id.movie_item_country);
        movieDirector = findViewById(R.id.movie_item_director);
        movieDesc = findViewById(R.id.movie_item_desc);
        moviePoster = findViewById(R.id.movie_poster);
        posterBg = findViewById(R.id.poster_bg);
        btnBookingCGV = findViewById(R.id.btn_booking_cgv);
        btnBookingBHD = findViewById(R.id.btn_booking_bhd);
        btnTrailer = findViewById(R.id.btn_trailer);
        movieResDate = findViewById(R.id.movie_item_date);
    }

    public void setupViewInfo(){
        Intent intent = getIntent();
        Date date = (Date) intent.getSerializableExtra("movie_date");
        sdf = new SimpleDateFormat("MM/dd/yy");
        String strDate = sdf.format(date);

        movieTitle.setText(intent.getStringExtra("movie_name"));
        movieDirector.setText(MessageFormat.format("Director: {0}", intent.getStringExtra("movie_director")));
        movieCountry.setText(MessageFormat.format("Country: {0}", intent.getStringExtra("movie_country")));
        movieDesc.setText(intent.getStringExtra("movie_desc"));
        movieRating.setText(String.valueOf(intent.getFloatExtra("movie_rating", 0)));
        movieResDate.setText(MessageFormat.format("Release Date: {0}", strDate));
        moviePoster.setImageResource(intent.getIntExtra("movie_poster", 0));
        posterBg.setImageResource(intent.getIntExtra("movie_poster", 0));

        btnBookingCGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayBookingCGVWeb();
            }
        });

        btnBookingBHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayBookingBHDWeb();
            }
        });

        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayMovieTrailer(intent.getStringExtra("movie_trailerUrl"));
            }
        });
    }

    public void displayBookingCGVWeb(){
        Intent bookingCGVIntent = new Intent(Intent.ACTION_VIEW);
        bookingCGVIntent.setData(Uri.parse("https://www.cgv.vn/"));
        startActivity(bookingCGVIntent);
    }

    public void displayBookingBHDWeb(){
        Intent bookingBHDIntent = new Intent(Intent.ACTION_VIEW);
        bookingBHDIntent.setData(Uri.parse("https://www.bhdstar.vn/"));
        startActivity(bookingBHDIntent);
    }

    public void displayMovieTrailer(String url){
        Intent trailerIntent = new Intent(Intent.ACTION_VIEW);
        trailerIntent.setData(Uri.parse(url));
        startActivity(trailerIntent);
    }

    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void launchFavouriteActivity(View view) {
        Intent intent = new Intent(this, FavouriteActivity.class);
        List<Movie> favoriteMovies = movieService.getFavoriteMovies();
        int[] favoriteMovieIds = favoriteMovies.stream().map(Movie::getId).mapToInt(Integer::intValue).toArray();

        if(favoriteMovies.size() > 0) {
            intent.putExtra(MainActivity.MOVIE_ID, favoriteMovieIds);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You have no favorite movies", Toast.LENGTH_SHORT).show();
        }
    }
}
