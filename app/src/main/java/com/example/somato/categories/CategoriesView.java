package com.example.somato.categories;

import com.example.somato.categories.model.CategoriesList;
import com.example.somato.utitlities.ErrorResponse;

public interface CategoriesView {
    public void onGetSuccess(CategoriesList categoriesList);
    public void onError(ErrorResponse error);
}
