package com.example.guest.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.helpers.DateFormatter;
import com.example.guest.movieapp.models.Movie;
import com.example.guest.movieapp.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/23/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movieImageView) ImageView mMovieImageView;
        @Bind(R.id.movieNameTextView) TextView mMovieNameTextView;
        @Bind(R.id.movieReleaseDateTextView) TextView mMovieReleaseDateTextView;
        @Bind(R.id.snippetTextView) TextView mSnippetTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;
        private Movie mMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("movieId", mMovie.getMovieId());
                    mContext.startActivity(intent);
                }
            });
        }

        public Movie bindMovie(Movie movie) {
            mMovie = movie;
            Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + movie.getPoster_Path()).into(mMovieImageView);
            mMovieNameTextView.setText(movie.getTitle());
            mMovieReleaseDateTextView.setText(" (" + DateFormatter.yearOnly(movie.getReleaseDate()) + ")");
            mSnippetTextView.setText(movie.getSnippet());
            mRatingTextView.setText("Rating: " + movie.getVote_Average());
            return mMovie;
        }
    }
}
