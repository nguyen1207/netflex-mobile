package com.example.netflex.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflex.MovieItemActivity;
import com.example.netflex.R;
import com.example.netflex.model.Movie;

import java.text.MessageFormat;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private final List<Movie> movies;
    private LayoutInflater inflater;
    private Context context;

    public MovieListAdapter(Context context, List<Movie> movies) {
        inflater = LayoutInflater.from(context);
        this.movies = movies;
        this.context = context;
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
        holder.moviePrice.setText(MessageFormat.format("{0}$", current.getPrice()));
        holder.moviePoster.setImageResource(current.getPoster());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isLongClick) {
                if(isLongClick){
                    Intent intent = new Intent(context, MovieItemActivity.class);
                    intent.putExtra("movie_name", movies.get(position).getName());
                    intent.putExtra("movie_rating", movies.get(position).getScore());
                    intent.putExtra("movie_director", movies.get(position).getDirector());
                    intent.putExtra("movie_date", movies.get(position).getReleaseDate());
                    intent.putExtra("movie_country", movies.get(position).getCountry());
                    intent.putExtra("movie_desc", movies.get(position).getDescription());
                    intent.putExtra("movie_trailerUrl", movies.get(position).getTrailerUrl());
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Long Click To Display Movie Item!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnLongClickListener {
        public final MovieListAdapter adapter;
        public TextView movieName;
        public TextView movieDescription;
        public TextView movieScore;
        public TextView moviePrice;
        public ImageView moviePoster;
        public ImageButton movieFavourite;
        private ItemClickListener itemClickListener;

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

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }

    public interface ItemClickListener{
        void onClick(View v, int position, boolean isLongClick);
    }
}
