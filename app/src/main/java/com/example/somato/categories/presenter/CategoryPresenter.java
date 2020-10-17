package com.example.somato.categories.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.example.somato.categories.CategoriesView;
import com.example.somato.categories.model.CategoriesList;
import com.example.somato.network.BaseRetrofitHandler;
import com.example.somato.utitlities.AppConstants;
import com.example.somato.utitlities.ErrorResponse;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class CategoryPresenter {
    private Activity context;
    private CategoriesView categoriesView;
    public CategoryPresenter(Activity context, CategoriesView categoriesView){
        this.context = context;
        this.categoriesView = categoriesView;
    }

    public void getCategories(){
    Observable<CategoriesList> response = BaseRetrofitHandler.getInstance().apiService.getCategories();
    response.subscribeOn(Schedulers.newThread()).
            observeOn(AndroidSchedulers.mainThread()).
            map(CategoriesList -> CategoriesList).subscribe(new Observer<CategoriesList>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(CategoriesList categoriesList) {
            categoriesView.onGetSuccess(categoriesList);
        }

        @Override
        public void onError(Throwable e) {
            ErrorResponse errorResponse=null;
            try {
                String code = ((HttpException) e).response().errorBody().string();
                Gson gson = new Gson();
                errorResponse = gson.fromJson(code, ErrorResponse.class);
                categoriesView.onError(errorResponse);
            } catch (Exception ex) {
                errorResponse= new ErrorResponse();
                errorResponse.setMessage(AppConstants.ERROR_HANDLE_MESSAGE);
                categoriesView.onError(errorResponse);

            }        }

        @Override
        public void onComplete() {

        }
    });

    }
}
