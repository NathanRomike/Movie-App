package com.example.guest.movieapp.services;

import android.content.Context;

import com.example.guest.movieapp.R;
import com.example.guest.movieapp.models.MovieDetail;

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
public class MovieDetailService {
    private Context mContext;

    public MovieDetailService(Context context) {
        this.mContext = context;
    }

    public void findMovieDetails(String movieId, Callback callback) {
        String API_KEY = mContext.getString(R.string.API_KEY);

        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.themoviedb.org/3/movie/" + movieId + "?").newBuilder();
        urlBuilder.addQueryParameter("api_key", API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<MovieDetail> processResults (Response response) {
        ArrayList<MovieDetail> movieDetails = new ArrayList< >();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);
                String backdropPath = movieJSON.getString("backdrop_path");
                String budget = movieJSON.getString("budget");
                String imdbId = movieJSON.getString("imbd_id");
                String originalLanguage = movieJSON.getString("original_language");
                String originalTitle = movieJSON.getString("original_title");
                String overview = movieJSON.getString("overview");
                String poster_path = movieJSON.getString("poster_path");
                String releaseDate = movieJSON.getString("release_date");
                String revenue = movieJSON.getString("revenue");
                String runTime = movieJSON.getString("runtime");
                String tagLine = movieJSON.getString("tagline");
                String title = movieJSON.getString("title");
                String voteAverage = movieJSON.getString("vote_average");

                ArrayList<String> productionCompaniesArray = new ArrayList<>();
                JSONArray productionCompaniesJSON = movieJSON.getJSONArray("production_companies");
                for (int i = 0; i < productionCompaniesJSON.length(); i++) {
                    productionCompaniesArray.add(productionCompaniesArray.get(i).toString());
                }

                ArrayList<String> genresArray = new ArrayList<>();
                JSONArray genresJSON = movieJSON.getJSONArray("genres");
                for (int i = 0; i < genresJSON.length(); i++) {
                    genresArray.add(genresArray.get(i).toString());
                }

                ArrayList<String> countriesArray = new ArrayList<>();
                JSONArray countriesJSON = movieJSON.getJSONArray("countries");
                for (int i = 0; i < countriesJSON.length(); i++) {
                    countriesArray.add(countriesArray.get(i).toString());
                }

                ArrayList<String> spokenLanguagesArray = new ArrayList<>();
                JSONArray spokenLanguagesJSON = movieJSON.getJSONArray("spoken_languages");
                for (int i = 0; i < spokenLanguagesJSON.length(); i++) {
                    spokenLanguagesArray.add(spokenLanguagesArray.get(i).toString());
                }

                MovieDetail movieDetail = new MovieDetail(backdropPath, budget, imdbId, originalLanguage, originalTitle, overview, poster_path, releaseDate, revenue, runTime, tagLine, title, voteAverage, productionCompaniesArray, genresArray, countriesArray, spokenLanguagesArray);
                movieDetails.add(movieDetail);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieDetails;
    }
}
