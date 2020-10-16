package com.example.somato.categories.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesList {
    @SerializedName("categories")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
