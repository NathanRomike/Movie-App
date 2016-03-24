package com.example.guest.movieapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.helpers.CurrencyFormatter;
import com.example.guest.movieapp.helpers.DateFormatter;
import com.example.guest.movieapp.models.MovieDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/24/16.
 */
public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailViewHolder> {
    private ArrayList<MovieDetail> mMovieDetails = new ArrayList<>();
    private Context mContext;

    public MovieDetailAdapter(Context context, ArrayList<MovieDetail> movieDetails) {
        mContext = context;
        mMovieDetails = movieDetails;
    }

    @Override
    public MovieDetailAdapter.MovieDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_detail_item, parent, false);
        MovieDetailViewHolder viewHolder = new MovieDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieDetailAdapter.MovieDetailViewHolder holder, int position) {
        holder.bindMovieDetail(mMovieDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieDetails.size();
    }

    public class MovieDetailViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movieDetailImageView) ImageView mImageView;
        @Bind(R.id.movieDetailNameTextView) TextView mNameTextView;
        @Bind(R.id.movieDetailReleaseDateTextView) TextView mReleaseDateTextView;
        @Bind(R.id.movieDetailProductionCompanyTextView) TextView mProductionCompanyTextView;
        @Bind(R.id.movieDetailBudgetTextView) TextView mBudgetTextView;
        @Bind(R.id.movieDetailRevenueTextView) TextView mRevenueTextView;
        @Bind(R.id.movieDetailRunTimeTextView) TextView mRunningTimeTextView;
        @Bind(R.id.movieDetailOverviewTextView) TextView mOverviewTextView;
        private MovieDetail mMovieDetail;

        public MovieDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public MovieDetail bindMovieDetail(MovieDetail movieDetail) {
            mMovieDetail = movieDetail;
            Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + movieDetail.getPosterPath()).into(mImageView);
            mNameTextView.setText(movieDetail.getTitle());
            mReleaseDateTextView.setText(" (" + DateFormatter.yearOnly(movieDetail.getReleaseDate()) + ")");
            mProductionCompanyTextView.setText(parseArrayListIntoString(movieDetail.getProductionCompanies()));
            mBudgetTextView.setText(String.format(mContext.getString(R.string.budget_header), CurrencyFormatter.toDollars(movieDetail.getBudget())));
            mRevenueTextView.setText(String.format(mContext.getString(R.string.revenue_header), CurrencyFormatter.toDollars(movieDetail.getRevenue())));
            mRunningTimeTextView.setText(String.format(mContext.getString(R.string.running_time_header), movieDetail.getRuntime()));
            mOverviewTextView.setText(movieDetail.getOverview());
            return mMovieDetail;
        }

        public String parseArrayListIntoString(ArrayList<String> arrayList) {
            String newString = "";
            for (int i = 0; i < mMovieDetail.getProductionCompanies().size(); i++) {
                newString += arrayList.get(i) + ", ";
            }
            if (newString.length() > 1) {
                return newString.substring(0, newString.length() - 2);
            } else {
                return newString;
            }
        }
    }



}
