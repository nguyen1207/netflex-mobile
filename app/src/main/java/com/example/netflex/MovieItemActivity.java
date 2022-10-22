package com.example.netflex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieItemActivity extends AppCompatActivity {
    private ImageView moviePoster;
    private ImageView posterBg;
    private TextView movieTitle;
    private TextView movieRating;
    private TextView movieCountry;
    private TextView movieDirector;
    private TextView movieDesc;
    private TextView movieResDate;
    private Button btnBooking;
    private WebView movieTrailer;
    SimpleDateFormat sdf;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_item);

        Intent intent = getIntent();

        movieTitle = findViewById(R.id.movie_item_title);
        movieRating = findViewById(R.id.movie_item_rating);
        movieCountry = findViewById(R.id.movie_item_country);
        movieDirector = findViewById(R.id.movie_item_director);
        movieDesc = findViewById(R.id.movie_item_desc);
        moviePoster = findViewById(R.id.movie_poster);
        posterBg = findViewById(R.id.poster_bg);
        btnBooking = findViewById(R.id.btn_booking);
        movieTrailer = findViewById(R.id.wv_trailer);
        movieResDate = findViewById(R.id.movie_item_date);

        Date date = (Date) intent.getSerializableExtra("movie_date");
        sdf = new SimpleDateFormat("MM/dd/yy");
        String strDate = sdf.format(date);

        movieTitle.setText(intent.getStringExtra("movie_name"));
        movieDirector.setText(MessageFormat.format("Director: {0}", intent.getStringExtra("movie_director")));
        movieCountry.setText(MessageFormat.format("Country: {0}", intent.getStringExtra("movie_country")));
        movieDesc.setText(intent.getStringExtra("movie_desc"));
        movieRating.setText(String.valueOf(intent.getFloatExtra("movie_rating", 0)));
        movieResDate.setText(strDate);
    }
}
