package com.example.guest.movieapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.helpers.DateFormatter;
import com.example.guest.movieapp.models.ActorDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/24/16.
 */
public class ActorDetailAdapter extends RecyclerView.Adapter<ActorDetailAdapter.ActorDetailViewHolder> {
    private ArrayList<ActorDetail> mActorDetails = new ArrayList<>();
    private Context mContext;

    public ActorDetailAdapter(Context context, ArrayList<ActorDetail> actorDetails) {
        mContext = context;
        mActorDetails = actorDetails;
    }

    @Override
    public ActorDetailAdapter.ActorDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_detail_item, parent, false);
        ActorDetailViewHolder viewHolder = new ActorDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActorDetailAdapter.ActorDetailViewHolder holder, int position) {
        holder.bindActorDetail(mActorDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mActorDetails.size();
    }

    public class ActorDetailViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.actorDetailPosterImageView) ImageView mActorDetailPosterImageView;
        @Bind(R.id.actorDetailMovieNameTextView) TextView mActorDetailMovieNameTextView;
        @Bind(R.id.actorDetailReleaseDateTextView) TextView mActorDetailReleaseDateTextView;
        @Bind(R.id.actorDetailCharacterTextView) TextView mActorDetailCharacterTextView;
        private ActorDetail mActorDetail;

        public ActorDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ActorDetail bindActorDetail(ActorDetail actorDetail) {
            mActorDetail = actorDetail;
            Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + actorDetail.getPosterPath()).into(mActorDetailPosterImageView);
            mActorDetailMovieNameTextView.setText(actorDetail.getMovieTitle());
            mActorDetailReleaseDateTextView.setText(" (" + DateFormatter.yearOnly(actorDetail.getReleaseDate()) + ")");
            mActorDetailCharacterTextView.setText(actorDetail.getCharacter());
            return mActorDetail;
        }
    }
}
