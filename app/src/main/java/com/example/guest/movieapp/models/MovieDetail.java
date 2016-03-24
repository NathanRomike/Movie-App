package com.example.guest.movieapp.models;

import java.util.ArrayList;

/**
 * Created by Guest on 3/24/16.
 */
public class MovieDetail {
    private String mbackDropPath;
    private String mbudget;
    private String mOriginalLanguage;
    private String mOriginalTitle;
    private String mOverview;
    private String mPosterPath;
    private String mReleaseDate;
    private String mRevenue;
    private String mRuntime;
    private String mTagLine;
    private String mTitle;
    private String mVoteAverage;
    private ArrayList<String> mProductionCompanies;
    private ArrayList<String> mGenres;
    private ArrayList<String> mCountries;
    private ArrayList<String> mSpokenLanguages;

    public MovieDetail(String backDropPath, String budget, String originalLanguage, String originalTitle, String overview, String posterPath, String releaseDate, String revenue, String runtime, String tagLine, String title, String voteAverage, ArrayList<String> productionCompanies, ArrayList<String> genres, ArrayList<String> countries, ArrayList<String> spokenLanguages) {
        this.mbackDropPath = backDropPath;
        this.mbudget = budget;
        this.mOriginalLanguage = originalLanguage;
        this.mOriginalTitle = originalTitle;
        this.mOverview = overview;
        this.mPosterPath = posterPath;
        this.mReleaseDate = releaseDate;
        this.mRevenue = revenue;
        this.mRuntime = runtime;
        this.mTagLine = tagLine;
        this.mTitle = title;
        this.mVoteAverage = voteAverage;
        this.mProductionCompanies = productionCompanies;
        this.mGenres = genres;
        this.mCountries = countries;
        this.mSpokenLanguages = spokenLanguages;
    }

    public String getBackDropPath() {
        return mbackDropPath;
    }

    public String getBudget() {
        return mbudget;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getRevenue() {
        return mRevenue;
    }

    public String getRuntime() {
        return mRuntime;
    }

    public String getTagLine() {
        return mTagLine;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public ArrayList<String> getProductionCompanies() {
        return mProductionCompanies;
    }

    public ArrayList<String> getGenres() {
        return mGenres;
    }

    public ArrayList<String> getCountries() {
        return mCountries;
    }

    public ArrayList<String> getSpokenLanguages() {
        return mSpokenLanguages;
    }
}
