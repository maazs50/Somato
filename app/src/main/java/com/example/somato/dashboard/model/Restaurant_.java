package com.example.somato.dashboard.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Restaurant_ {

    @SerializedName("R")
    @Expose
    private R r;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("timings")
    @Expose
    private String timings;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;

    public R getR() {
        return r;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getTimings() {
        return timings;
    }

    public String getThumb() {
        return thumb;
    }

    public UserRating getUserRating() {
        return userRating;
    }

}

