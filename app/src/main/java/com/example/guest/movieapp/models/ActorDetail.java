package com.example.guest.movieapp.models;

import java.util.ArrayList;

/**
 * Created by Guest on 3/24/16.
 */
public class ActorDetail {
    private String mCharacter;
    private String mMovieId;
    private String mPosterPath;
    private String mReleaseDate;
    private String mMovieTitle;

    public ActorDetail(String character, String movieId, String posterPath, String releaseDate, String movieTitle) {
        this.mCharacter = character;
        this.mMovieId = movieId;
        this.mPosterPath = posterPath;
        this.mReleaseDate = releaseDate;
        this.mMovieTitle = movieTitle;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public String getMovieId() {
        return mMovieId;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }
}
