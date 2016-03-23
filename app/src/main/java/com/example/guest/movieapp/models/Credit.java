package com.example.guest.movieapp.models;

/**
 * Created by Guest on 3/23/16.
 */
public class Credit {
    private String mAdult;
    private String mCharacter;
    private String mCreditId;
    private String mMovieId;
    private String mOriginalTitle;
    private String mPosterPath;
    private String mReleaseDate;
    private String mTitle;

    public Credit(String adult, String character, String creditId, String movieId, String originalTitle, String posterPath, String releaseDate, String title) {
        this.mAdult = adult;
        this.mCharacter = character;
        this.mCreditId = creditId;
        this.mMovieId = movieId;
        this.mOriginalTitle = originalTitle;
        this.mPosterPath = posterPath;
        this.mReleaseDate = releaseDate;
        this.mTitle = title;
    }

    public String getAdult() {
        return mAdult;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public String getMovieId() {
        return mMovieId;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getTitle() {
        return mTitle;
    }
}


