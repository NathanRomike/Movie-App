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
                if(productionCompaniesJSON.length() > 0) {
                    for (int i = 0; i < productionCompaniesJSON.length(); i++) {
                        String productionCompany = productionCompaniesJSON.getJSONObject(i).getString("name");
                        productionCompaniesArray.add(productionCompany);
                    }
                }

                ArrayList<String> genresArray = new ArrayList<>();
                JSONArray genresJSON = movieJSON.getJSONArray("genres");
                if(genresJSON.length() > 0) {
                    for (int i = 0; i < genresJSON.length(); i++) {
                        String genre = genresJSON.getJSONObject(i).getString("name");
                        genresArray.add(genre);
                    }
                }

                ArrayList<String> countriesArray = new ArrayList<>();
                JSONArray countriesJSON = movieJSON.getJSONArray("production_countries");
                if(countriesJSON.length() > 0) {
                    for (int i = 0; i < countriesJSON.length(); i++) {
                        String country = countriesJSON.getJSONObject(i).getString("name");
                        countriesArray.add(country);
                    }
                }

                ArrayList<String> spokenLanguagesArray = new ArrayList<>();
                JSONArray spokenLanguagesJSON = movieJSON.getJSONArray("spoken_languages");
                if(spokenLanguagesJSON.length() > 0) {
                    for (int i = 0; i < spokenLanguagesJSON.length(); i++) {
                        String language = spokenLanguagesJSON.getJSONObject(i).getString("name");
                        spokenLanguagesArray.add(language);
                    }
                }

                MovieDetail movieDetail = new MovieDetail(backdropPath, budget, originalLanguage, originalTitle, overview, poster_path, releaseDate, revenue, runTime, tagLine, title, voteAverage, productionCompaniesArray, genresArray, countriesArray, spokenLanguagesArray);
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
