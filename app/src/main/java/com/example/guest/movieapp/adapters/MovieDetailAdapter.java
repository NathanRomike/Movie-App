package com.example.guest.movieapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.MovieDetail;

import java.util.ArrayList;

/**
 * Created by Guest on 3/24/16.
 */
public class MovieDetailAdapter {
    private ArrayList<MovieDetail> mMovieDetails = new ArrayList<>();
    private Context mContext;

    public MovieDetailAdapter(Context context, ArrayList<MovieDetail> movieDetails) {
        mContext = context;
        mMovieDetails = movieDetails;
    }

    @Override
    public MovieDetailAdapter.MovieDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieDetailViewHolder viewHolder = new MovieDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mMovieDetails.size();
    }

    @Override
    public class MovieDetailViewHolder extends RecyclerView.ViewHolder {

    }

}
