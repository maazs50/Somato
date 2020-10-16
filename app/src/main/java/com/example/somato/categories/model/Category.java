package com.example.somato.categories.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("categories")
    private CategoryFields categoryFields;

    public CategoryFields getCategoryFields() {
        return categoryFields;
    }
}
