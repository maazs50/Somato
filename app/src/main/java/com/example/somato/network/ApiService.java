package com.example.somato.network;


import com.example.somato.categories.model.CategoriesList;
import com.example.somato.dashboard.model.SearchedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"user-key: " + Config.API_KEY, "Accept: application/json"})
    @GET(ApiEndPoints.CATEGORIES)
    Observable<CategoriesList> getCategories();

    @Headers({"user-key: " + Config.API_KEY, "Accept: application/json"})
    @GET(ApiEndPoints.SEARCH)
    Observable<SearchedResponse> searchForDashBoard(
            @Query("category") int category,
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("sort") String sortBy,
            @Query("count") int limit);

/*    @Headers("Content-Type: application/json")
    @GET(ApiEndPoints.RECENT_MORE)
    Observable<RecentMoreResponse> getRecentMore(@Header(Utilites.AUTH_TOKEN) String token,
                                                 @Path("categ_id") String categId,
                                                 @Query("page") String page);

    *//**
     * Get PurChaseMedia Currency  Api Call
     *//*
    @Headers("Content-Type: application/json")
    @POST(ApiEndPoints.APPLY_CURRENCY_MEDIA)
    Observable<PurchaseResponse> getPurChaseMediaCurrency(@Header(Utilites.AUTH_TOKEN) String token,
                                                          @Body PurchaseDataPayload payload);
    @Headers("Content-Type: application/json")
    @DELETE(ApiEndPoints.DEL_DEVICE)
    Observable<DeleteDeviceResponse> deleteDevice(@Header(Utilites.AUTH_TOKEN) String token,
                                                  @Path("deviceId") String deviceId);*/

}
