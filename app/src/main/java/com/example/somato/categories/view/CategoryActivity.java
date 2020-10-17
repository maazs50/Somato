package com.example.somato.categories.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.somato.R;
import com.example.somato.categories.CategoriesView;
import com.example.somato.categories.adapters.CategoryAdapter;
import com.example.somato.categories.model.CategoriesList;
import com.example.somato.categories.model.CategoryFields;
import com.example.somato.categories.presenter.CategoryPresenter;
import com.example.somato.dashboard.view.MainActivity;
import com.example.somato.utitlities.AppConstants;
import com.example.somato.utitlities.ErrorResponse;
import com.example.somato.utitlities.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoriesView,CategoryAdapter.CatListener {
    private CategoryPresenter presenter;
    private CategoryAdapter adapter;
    private RecyclerView recyclerView;
    private List<CategoryFields> list;
    private ProgressBar progressBar;
    private Button doneBtn;
    public static int oldPosition = 0;
    private int catId=0,lang=0;
    private String catName="Delivery";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.category_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progressBar);
        doneBtn = findViewById(R.id.btn_done);
        try {
            oldPosition = SharedPrefsHelper.getInstance().get(AppConstants.CATEGORY_POSITION);
        } catch (NullPointerException ex) {
            oldPosition = 0;
        }

        doneBtn.setOnClickListener(view -> {
            saveLocale();
            Intent goToMain = new Intent(this, MainActivity.class);
            startActivity(goToMain);
            finish();
        });
        presenter = new CategoryPresenter(this,this);
        progressBar.setVisibility(View.VISIBLE);
        presenter.getCategories();
        list=new ArrayList<>();
    }

    @Override
    public void onGetSuccess(CategoriesList categoriesList) {
        progressBar.setVisibility(View.GONE);
        int listSize = categoriesList.getCategoryList().size();
        for (int i=0;i<listSize;i++){
            Log.i("Category "+i,categoriesList.getCategoryList().get(i).getCategoryFields().getName());
            list.add(categoriesList.getCategoryList().get(i).getCategoryFields());
        }
        adapter = new CategoryAdapter(list,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(ErrorResponse error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();
    }


    @Override
    public void getCategory(String name, int id, int pos) {
        catName = name;
        catId = id;
        lang = pos;
        oldPosition = pos;
    }

    public void saveLocale() {
        SharedPrefsHelper.getInstance().save(AppConstants.CATEGORY_ID, catId);
        SharedPrefsHelper.getInstance().save(AppConstants.CATEGORY_NAME, catName);
        SharedPrefsHelper.getInstance().save(AppConstants.CATEGORY_POSITION,lang);
    }
}