package com.example.somato.dashboard.presenter;

import android.app.Activity;

import com.example.somato.categories.CategoriesView;
import com.example.somato.categories.model.CategoriesList;
import com.example.somato.dashboard.DashBoardView;
import com.example.somato.dashboard.model.SearchedResponse;
import com.example.somato.network.BaseRetrofitHandler;
import com.example.somato.utitlities.AppConstants;
import com.example.somato.utitlities.ErrorResponse;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class DashboardPresenter {
    private Activity context;
    private DashBoardView dashBoardView;
    public DashboardPresenter(Activity context, DashBoardView dashBoardView){
        this.context = context;
        this.dashBoardView = dashBoardView;
    }

    public void getResultsForDashboard(int cat,double lat,double lon,String sort,int limit){
        Observable<SearchedResponse> response = BaseRetrofitHandler.getInstance().apiService.searchForDashBoard(cat,lat,lon,sort,limit);
        response.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                map(SearchedResponse -> SearchedResponse).subscribe(new Observer<SearchedResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchedResponse searchedResponse) {
                dashBoardView.onSearchResultSucess(searchedResponse);
            }

            @Override
            public void onError(Throwable e) {
                ErrorResponse errorResponse=null;
                try {
                    String code = ((HttpException) e).response().errorBody().string();
                    Gson gson = new Gson();
                    errorResponse = gson.fromJson(code, ErrorResponse.class);
                    dashBoardView.onError(errorResponse);
                } catch (Exception ex) {
                    errorResponse= new ErrorResponse();
                    errorResponse.setMessage(AppConstants.ERROR_HANDLE_MESSAGE);
                    dashBoardView.onError(errorResponse);

                }            }

            @Override
            public void onComplete() {

            }
        });

    }
}
