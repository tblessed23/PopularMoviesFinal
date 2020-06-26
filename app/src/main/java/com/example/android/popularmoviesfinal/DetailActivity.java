package com.example.android.popularmoviesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;

public class DetailActivity extends AppCompatActivity {

    TextView movieTitleTextView;
    TextView releaseDateTextView;
    TextView userRatingTextView;
    TextView plotSynopsisTextView;
    ImageView moviePosterImageView;

    private Movies movies;
    String movietitle;
    String releasedate;
    String userrating;
    String synopsis;
    String mImageBaseUrl = "http://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        moviePosterImageView = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        // Using getParcelableExtra(String key) method
        if (intent.hasExtra(getResources().getString(R.string.intent_key))) {
            movies = intent.getParcelableExtra(getResources().getString(R.string.intent_key));
            Picasso.get().load(mImageBaseUrl + movies.getmImage()).resize(500,750).into(moviePosterImageView);
            movietitle = movies.getmTitle();
            releasedate = movies.getmReleaseDate();
            userrating = movies.getmUserRating();
            synopsis = movies.getmSynopsis();
        }

        if (movies == null) {
            // Movie data unavailable
            closeOnError();
            return;
        }

        populateUI(movies);

        setTitle(movies.getmTitle());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //populate the user interface
    private void populateUI(Movies movies) {

        //Set the Text of the Movie Object Variables
        movieTitleTextView = findViewById(R.id.movietitle_tv);
        movieTitleTextView.setText(movietitle);

        releaseDateTextView = findViewById(R.id.release_date_tv);
        releaseDateTextView.setText(releasedate);


        userRatingTextView = findViewById(R.id.user_rating_tv);
        userRatingTextView.setText(userrating);

        plotSynopsisTextView = findViewById(R.id.plot_synopsis_tv);
        plotSynopsisTextView.setText(TextUtils.join(",", Collections.singleton(synopsis)));

    }
}