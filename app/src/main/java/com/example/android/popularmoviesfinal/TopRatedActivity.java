package com.example.android.popularmoviesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TopRatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TopRatedFragment())
                .commit();
    }
}
