package com.example.guest.movieapp.models;

import java.util.ArrayList;

/**
 * Created by Guest on 3/23/16.
 */
public class Movie {
    private String mAdult;
    private String mBackDrop_Path;
    private String mMovieId;
    private String mOriginal_Language;
    private String mOverView;
    private String mReleaseDate;
    private String mPoster_Path;
    private String mPopularity;
    private String mTitle;
    private String mVideo;
    private String mVote_Average;
    private String mVote_Count;
    private String mMedia_Type;
    private ArrayList<String> mGenre_Ids;


    public Movie(String adult, String backdrop_path, String movieId, String original_language, String original_title, String overview, String release_date, String poster_path, String popularity, String title, String video, String vote_average, String vote_count, String media_type, ArrayList<String> genre_ids) {
        this.mAdult = adult;
        this.mBackDrop_Path = backdrop_path;
        this.mMovieId = movieId;
        this.mOriginal_Language = original_language;
        this.mOverView = overview;
        this.mReleaseDate = release_date;
        this.mPoster_Path = poster_path;
        this.mPopularity = popularity;
        this.mTitle = title;
        this.mVideo = video;
        this.mVote_Average = vote_average;
        this.mVote_Count = vote_count;
        this.mMedia_Type = media_type;
        this. mGenre_Ids = genre_ids;

    }

    public String getAdult() {
        return mAdult;
    }

    public String getBackDrop_Path() {
        return mBackDrop_Path;
    }

    public String getMovieId() {
        return mMovieId;
    }

    public String getOriginal_Language() {
        return mOriginal_Language;
    }

    public String getOverView() {
        return mOverView;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPoster_Path() {
        return mPoster_Path;
    }

    public String getPopularity() {
        return mPopularity;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVideo() {
        return mVideo;
    }

    public String getVote_Average() {
        return mVote_Average;
    }

    public String getVote_Count() {
        return mVote_Count;
    }

    public String getMedia_Type() {
        return mMedia_Type;
    }

    public ArrayList<String> getGenre_Ids() {
        return mGenre_Ids;
    }


}
