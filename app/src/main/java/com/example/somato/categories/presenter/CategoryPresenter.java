package com.example.somato.categories.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.example.somato.categories.CategoriesView;
import com.example.somato.categories.model.CategoriesList;
import com.example.somato.network.BaseRetrofitHandler;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
            categoriesView.onError(e.toString());
        }

        @Override
        public void onComplete() {

        }
    });

    }
}
