package com.example.somato.Restaurant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.somato.R;

public class RetaurantDetailsActivity extends AppCompatActivity {
    private ImageView thumbnail;
    private RatingBar ratingBar;
    private TextView rating_count;
    private TextView nameRest;
    private TextView addressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retaurant_details);
        thumbnail =findViewById(R.id.imageView);
        ratingBar =findViewById(R.id.ratingBar);
        rating_count = findViewById(R.id.rating_tv);
        nameRest = findViewById(R.id.tv_restName);
        addressTxt = findViewById(R.id.tv_address);
        Intent intent = getIntent();
        String thumb = intent.getStringExtra("thumb");
        String name = intent.getStringExtra("res_name");
        String address = intent.getStringExtra("res_address");
        double rating = intent.getDoubleExtra("rating_point",0.0);
        String ratingStr = String.valueOf(rating);
        Glide.with(this).load(thumb).placeholder(getDrawable(R.drawable.image6)).into(thumbnail);
        nameRest.setText(name);
        addressTxt.setText(address);
        ratingBar.setRating(Float.valueOf(ratingStr));
        rating_count.setText(ratingStr);
    }
}