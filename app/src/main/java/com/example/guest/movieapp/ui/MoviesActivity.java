package com.example.guest.movieapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.services.MovieApiService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Intent intent = getIntent();
        String searchInput = intent.getStringExtra("searchInput");

        getMovies(searchInput);
    }

    private void getMovies(String searchInput) {
        final MovieApiService movieApiService = new MovieApiService(this);

        movieApiService.findMovies(searchInput, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v("JSON DATA" , jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
