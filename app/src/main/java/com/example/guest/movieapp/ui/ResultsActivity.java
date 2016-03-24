package com.example.guest.movieapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.adapters.ActorListAdapter;
import com.example.guest.movieapp.adapters.MovieListAdapter;
import com.example.guest.movieapp.models.Actor;
import com.example.guest.movieapp.models.Movie;
import com.example.guest.movieapp.services.ActorApiService;
import com.example.guest.movieapp.services.MovieApiService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private MovieListAdapter mMovieAdapter;
    private ActorListAdapter mActorAdapter;

    public ArrayList<Movie> mMovies = new ArrayList<>();
    public ArrayList<Actor> mActors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchInput = intent.getStringExtra("searchInput");
        String spinnerCategory = intent.getStringExtra("spinnerCategory");

        getResults(searchInput, spinnerCategory);
    }

    private void getResults(String searchInput, String spinnerCategory) {

        if (spinnerCategory.equals("Movie")) {
            final MovieApiService movieApiService = new MovieApiService(this);

            movieApiService.findMovies(searchInput, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    mMovies = movieApiService.processResults(response);

                    ResultsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMovieAdapter = new MovieListAdapter(getApplicationContext(), mMovies);
                            mRecyclerView.setAdapter(mMovieAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }
            });
        } else {
            final ActorApiService actorApiService = new ActorApiService(this);

            actorApiService.findActors(searchInput, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    mActors = actorApiService.processResults(response);

                    ResultsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mActorAdapter = new ActorListAdapter(getApplicationContext(), mActors);
                            mRecyclerView.setAdapter(mActorAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResultsActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }
            });
        }

    }
}
