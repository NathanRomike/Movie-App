package com.example.guest.movieapp.services;

import android.content.Context;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.Actor;

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


public class ActorApiService {
    private Context mContext;

    public ActorApiService(Context context) {
        this.mContext = context;
    }

    public void findActors(String searchInput, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.themoviedb.org/3/search/person?").newBuilder();
        urlBuilder.addQueryParameter("api_key", API_KEY);
        urlBuilder.addQueryParameter("query", searchInput);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Actor> processResults(Response response) {
        ArrayList<Actor> actors = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray resultsArray = movieJSON.getJSONArray("results");
                for (int i= 0; i< resultsArray.length(); i++) {
                    JSONObject creditJSON = resultsArray.getJSONObject(i);
                    String profilePath = creditJSON.getString("profile_path");
                    String id = creditJSON.getString("id");
                    String name = creditJSON.getString("name");
                    Actor actor = new Actor(profilePath, name, id);
                    actors.add(actor);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actors;
    }



}
