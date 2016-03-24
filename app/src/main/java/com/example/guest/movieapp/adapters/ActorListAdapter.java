package com.example.guest.movieapp.adapters;

/**
 * Created by Guest on 3/24/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.Actor;
import com.example.guest.movieapp.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorViewHolder> {
    private ArrayList<Actor> mActors = new ArrayList<>();
    private Context mContext;

    public ActorListAdapter(Context context, ArrayList<Actor> actors) {
        mContext = context;
        mActors = actors;
    }

    @Override
    public ActorListAdapter.ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_list_item, parent, false);
        ActorViewHolder viewHolder = new ActorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActorListAdapter.ActorViewHolder holder, int position) {
        holder.bindActor(mActors.get(position));
    }

    @Override
    public int getItemCount() {
        return mActors.size();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.personImageView) ImageView personImageView;
        @Bind(R.id.personNameTextView) TextView personNameTextView;

        private Context mContext;
        private Actor mActor;

        public ActorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("actorId", mActor.getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public Actor bindActor(Actor actor) {
            mActor = actor;
            Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185/" + actor.getProfilePath()).into(personImageView);
            personNameTextView.setText(actor.getName());
            return mActor;
        }

    }
}