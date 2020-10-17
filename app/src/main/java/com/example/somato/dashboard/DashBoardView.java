package com.example.somato.dashboard;

import com.example.somato.categories.model.CategoriesList;
import com.example.somato.dashboard.model.Restaurant;
import com.example.somato.dashboard.model.SearchedResponse;
import com.example.somato.utitlities.ErrorResponse;

public interface DashBoardView {
    public void onSearchResultSucess(SearchedResponse response);
    public void onError(ErrorResponse error);
}
