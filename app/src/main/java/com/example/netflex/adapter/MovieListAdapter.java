package com.example.netflex.adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflex.R;
import com.example.netflex.model.Movie;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private final List<Movie> movies;
    private LayoutInflater inflater;

    public MovieListAdapter(Context context, List<Movie> movies) {
        inflater = LayoutInflater.from(context);
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.movielist_item, parent, false);
        return new MovieViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie current = movies.get(position);
        holder.movieName.setText(current.getName());
        holder.movieDescription.setText(current.getDescription());
        holder.movieScore.setText(String.valueOf(current.getScore()));
        holder.moviePrice.setText(current.getPrice() + "$");
        holder.moviePoster.setImageResource(current.getPoster());

        if(current.isFavorite()) {
            holder.movieFavourite.setImageResource(R.drawable.heart_red);
        } else {
            holder.movieFavourite.setImageResource(R.drawable.heart_gradient);
        }

        ConstraintLayout parent = (ConstraintLayout) holder.moviePoster.getParent();
        parent.setTag(current.getId());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        public final MovieListAdapter adapter;
        public TextView movieName;
        public TextView movieDescription;
        public TextView movieScore;
        public TextView moviePrice;
        public ImageView moviePoster;
        public ImageButton movieFavourite;

        public MovieViewHolder(@NonNull View itemView, MovieListAdapter adapter) {
            super(itemView);
            movieName = itemView.findViewById(R.id.name);
            movieDescription = itemView.findViewById(R.id.description);
            movieScore = itemView.findViewById(R.id.score);
            moviePrice = itemView.findViewById(R.id.price);
            moviePoster = itemView.findViewById(R.id.poster);
            movieFavourite = itemView.findViewById(R.id.heart_icon);
            this.adapter = adapter;

            movieDescription.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }
}
