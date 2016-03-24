package com.example.guest.movieapp.services;

import android.content.Context;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.ActorDetail;

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
 * Created by Guest on 3/24/16.
 */
public class ActorDetailService {
    private Context mContext;

    public ActorDetailService(Context context) {
        this.mContext = context;
    }

    public void findActorDetails(String actorId, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.themoviedb.org/3/person/" + actorId + "/movie_credits?").newBuilder();
        urlBuilder.addQueryParameter("api_key", API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<ActorDetail> processResults (Response response) {
        ArrayList<ActorDetail> actorDetails = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject actorJSON = new JSONObject(jsonData);
                JSONArray castArray = actorJSON.getJSONArray("cast");
                for (int i = 0; i < castArray.length(); i++) {
                    JSONObject actorDetailJSON = castArray.getJSONObject(i);
                    String character = actorDetailJSON.getString("character");
                    String movieId = actorDetailJSON.getString("id");
                    String posterPath = actorDetailJSON.getString("poster_path");
                    String releaseDate = actorDetailJSON.getString("release_date");
                    String movieTitle = actorDetailJSON.getString("title");
                    ActorDetail actorDetail = new ActorDetail(character, movieId, posterPath, releaseDate, movieTitle);
                    actorDetails.add(actorDetail);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actorDetails;
    }
}
