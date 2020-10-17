package com.example.somato.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.somato.R;

public class ProfileActivity extends AppCompatActivity {
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}