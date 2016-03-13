package com.sankar.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = getIntent().getIntExtra(ARG_POSITION, -1);

        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        TextView txtMovieDesc = (TextView) findViewById(R.id.txtMovieDesc);
        TextView textViewVoteAverage = (TextView) findViewById(R.id.textViewVote2);
        TextView textViewReleaseDate = (TextView) findViewById(R.id.textViewDate2);

        Movie movie = MovieService.getInstance().getMovie(position);
        Picasso.with(getApplicationContext()).load(movie.getImage_path()).placeholder(R.drawable.placeholder).into(imageView);
        imageView.getLayoutParams().height = 500; // OR
        imageView.getLayoutParams().width = 500;
        textViewVoteAverage.setText(Double.toString(movie.getVote_average())+"/10");
        textViewReleaseDate.setText(movie.getRelease_date());
        txtMovieDesc.setText(movie.getOverview());
        setTitle(movie.getTitle());

    }
}
