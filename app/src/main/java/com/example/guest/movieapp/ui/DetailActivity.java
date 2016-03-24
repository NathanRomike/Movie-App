package com.example.guest.movieapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.MovieDetail;
import com.example.guest.movieapp.services.MovieDetailService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = DetailActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public ArrayList<MovieDetail> mMovieDetails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String movieId = "";
        String actorId = "";
        if (!intent.getStringExtra("movieId").isEmpty()) {
            movieId = intent.getStringExtra("movieId");
        } else {
            //create fill actorId;
        }

        if(movieId.length() > actorId.length()) {
            final MovieDetailService movieDetailService = new MovieDetailService(this);

            movieDetailService.findMovieDetails(String movieId, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    mMovieDetails = movieDetailService.processResults(response);

                    DetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMovieDetailAdapter = MovieDetailAdapter(getApplicationContext(), mMovieDetails);
                            mRecyclerView.setAdapter(mMovieDetailAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }
            });
        }


    }
}
