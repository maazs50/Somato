package com.example.somato.dashboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchedResponse {

@SerializedName("results_found")
@Expose
private int resultsFound;
@SerializedName("restaurants")
@Expose
private List<Restaurant> restaurants;

public Integer getResultsFound() {
return resultsFound;
}


public List<Restaurant> getRestaurants() {
return restaurants;
}

public void setRestaurants(List<Restaurant> restaurants) {
this.restaurants = restaurants;
}

}
