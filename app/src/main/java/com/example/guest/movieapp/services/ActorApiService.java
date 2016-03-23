package com.example.guest.movieapp.services;

import android.content.Context;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.Credit;
import com.example.guest.movieapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 3/23/16.
 */
public class ActorApiService {
    private Context mContext;

    public ActorApiService(Context context) {
        this.mContext = context;
    }

    public void findActors(String personId, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.themoviedb.org/3/person/" + personId + "/movie_credits?").newBuilder();
        urlBuilder.addQueryParameter("api_key", API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Credit> processResults(Response response) {
        ArrayList<Credit> credits = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray resultsArray = movieJSON.getJSONArray("cast");
                for (int i= 0; i< movieJSON.length(); i++) {
                    JSONObject creditJSON = resultsArray.getJSONObject(i);
                    String adult = creditJSON.getString("adult");
                    String character = creditJSON.getString("character");
                    String creditId = creditJSON.getString("credit_id");
                    String movieId = creditJSON.getString("id");
                    String originalTitle = creditJSON.getString("original_title");
                    String posterPath = creditJSON.getString("poster_path");
                    String releaseDate = creditJSON.getString("release_date");
                    String title = creditJSON.getString("title");
                    Credit credit = new Credit(adult, character, creditId, movieId, originalTitle, posterPath, releaseDate, title);
                    credits.add(credit);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return credits;
    }



}
