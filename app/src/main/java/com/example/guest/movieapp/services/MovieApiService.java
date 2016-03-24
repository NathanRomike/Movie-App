package com.example.guest.movieapp.services;

import android.content.Context;

import com.example.guest.movieapp.R;
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
public class MovieApiService {
    private Context mContext;

    public MovieApiService(Context context) {
        this.mContext = context;
    }

    public void findMovies(String searchInput, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.themoviedb.org/3/search/movie?").newBuilder();
        urlBuilder.addQueryParameter("api_key", API_KEY);
        urlBuilder.addQueryParameter("query", searchInput);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray resultsArray = movieJSON.getJSONArray("results");
                if (resultsArray.getJSONObject(0).has("known_for")) {
                    //handle actors and directors here
                    JSONArray actorMovieArray = resultsArray.getJSONObject(0).getJSONArray("known_for");
                    movies = processArray(actorMovieArray);

                } else {
                    //handle movies here
                    movies = processArray(resultsArray);
                }
                return movies;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private ArrayList<Movie> processArray(JSONArray specificArray) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < specificArray.length(); i++) {
            try {
                JSONObject movieJSON = specificArray.getJSONObject(i);
                String adult = movieJSON.getString("adult");
                String backdrop_path = movieJSON.getString("backdrop_path");
                String movieId = movieJSON.getString("id");
                String original_language = movieJSON.getString("original_language");
                String original_title = movieJSON.getString("original_title");
                String overview = movieJSON.getString("overview");
                String snippet = getSnippet(overview);
                String release_date = movieJSON.getString("release_date");
                String poster_path = movieJSON.getString("poster_path");
                String popularity = movieJSON.getString("popularity");
                String title = movieJSON.getString("title");
                String video = movieJSON.getString("video");
                String vote_average = movieJSON.getString("vote_average");
                String vote_count = movieJSON.getString("vote_count");
                String media_type = movieJSON.getString("media_type");

                ArrayList<String> genre_ids = new ArrayList<>();
                JSONArray genre_idsJSON = movieJSON.getJSONArray("genre_ids");
                for (int y = 0; y < genre_idsJSON.length(); y++) {
                    genre_ids.add(genre_idsJSON.get(y).toString());
                }

                Movie movie = new Movie(adult, backdrop_path, movieId, original_language, original_title, overview, snippet, release_date, poster_path, popularity, title, video, vote_average, vote_count, media_type, genre_ids);
                movies.add(movie);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public String getSnippet(String overview) {
        String snippet;
        if (overview.length() > 200) {
            snippet = overview.substring(0, 200) + "...";
        } else {
            snippet = overview;
        }
        return snippet;
    }

}

