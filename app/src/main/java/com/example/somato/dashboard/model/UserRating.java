package com.example.somato.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRating {

@SerializedName("aggregate_rating")
@Expose
private double aggregateRating;
@SerializedName("rating_text")
@Expose
private String ratingText;
@SerializedName("rating_color")
@Expose
private String ratingColor;
@SerializedName("votes")
@Expose
private int votes;

public double getAggregateRating() {
return aggregateRating;
}

public String getRatingText() {
return ratingText;
}


public String getRatingColor() {
return ratingColor;
}


public Integer getVotes() {
return votes;
}

}