package com.example.somato.dashboard.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.somato.R;
import com.example.somato.Restaurant.view.RetaurantDetailsActivity;
import com.example.somato.categories.view.CategoryActivity;
import com.example.somato.dashboard.DashBoardView;
import com.example.somato.dashboard.ProfileActivity;
import com.example.somato.dashboard.adapters.HomeAdapter;
import com.example.somato.dashboard.model.Restaurant;
import com.example.somato.dashboard.model.Restaurant_;
import com.example.somato.dashboard.model.SearchedResponse;
import com.example.somato.dashboard.presenter.DashboardPresenter;
import com.example.somato.network.Config;
import com.example.somato.seeAll.SeeAllActivity;
import com.example.somato.utitlities.AppConstants;
import com.example.somato.utitlities.ErrorResponse;
import com.example.somato.utitlities.SharedPrefsHelper;

public class MainActivity extends AppCompatActivity implements DashBoardView, HomeAdapter.HonClickListener {
    private DashboardPresenter presenter;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private ImageView profileIv, settingsIv;
    private TextView moreTv, titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar2);
        toolbar = findViewById(R.id.toolbar);
        profileIv = findViewById(R.id.iv_profile);
        settingsIv = findViewById(R.id.iv_settings);
        moreTv = findViewById(R.id.more_tv);
        titleTv = findViewById(R.id.title);
        progressBar.setVisibility(View.VISIBLE);
        profileIv.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
        settingsIv.setOnClickListener(view -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        });
        moreTv.setOnClickListener(view -> {
            Intent intent = new Intent(this, SeeAllActivity.class);
            startActivity(intent);
        });
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        int catId = SharedPrefsHelper.getInstance().get(AppConstants.CATEGORY_ID, 1);
        String catName = SharedPrefsHelper.getInstance().get(AppConstants.CATEGORY_NAME, "Delivery");
        titleTv.setText("Near by you for - " + catName);
        presenter = new DashboardPresenter(this, this);
        presenter.getResultsForDashboard(catId, Double.valueOf(Config.lattitude), Double.valueOf(Config.longitude), "real_distance", 20);
    }

    /**
     * @param response
     */
    @Override
    public void onSearchResultSucess(SearchedResponse response) {
        progressBar.setVisibility(View.GONE);
        if (response.getRestaurants().size() > 0) {
            adapter = new HomeAdapter(response.getRestaurants(), this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            moreTv.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "No restaurants available-\nChange category or see all", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onError(ErrorResponse error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void getRestaurantId(Restaurant_ restaurant) {
        Intent goToDetails = new Intent(this, RetaurantDetailsActivity.class);
        goToDetails.putExtra("res_id", restaurant.getId());
        goToDetails.putExtra("res_name", restaurant.getName());
        goToDetails.putExtra("res_address", restaurant.getLocation().getAddress());
        goToDetails.putExtra("thumb", restaurant.getThumb());
        goToDetails.putExtra("rating_point", restaurant.getUserRating().getAggregateRating());
        startActivity(goToDetails);
    }
}