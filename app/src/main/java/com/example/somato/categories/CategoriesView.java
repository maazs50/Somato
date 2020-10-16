package com.example.somato.categories;

import com.example.somato.categories.model.CategoriesList;

public interface CategoriesView {
    public void onGetSuccess(CategoriesList categoriesList);
    public void onError(String error);
}
