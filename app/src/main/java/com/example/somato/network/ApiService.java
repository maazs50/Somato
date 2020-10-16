package com.example.somato.network;


import com.example.somato.categories.model.CategoriesList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers({"user-key: "+Config.API_KEY,"Accept: application/json"})
    @GET(ApiEndPoints.CATEGORIES)
    Observable<CategoriesList> getCategories();

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
