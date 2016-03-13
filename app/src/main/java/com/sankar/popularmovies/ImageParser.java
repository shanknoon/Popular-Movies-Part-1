package com.sankar.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sankar on 1/24/2016.
 */
public class ImageParser {

    public ArrayList<Movie> parseJsonString(String jsonStr){
        long id;
        String title;
        String image_path;
        double popularity;
        long vote_count;
        double vote_average;
        String overview;
        String release_date;

        MovieService movieService = MovieService.getInstance();
        movieService.removeAllMovies();

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject movieObject = (JSONObject)jsonArray.get(i);
                Movie movie = new Movie();
                id = movieObject.getLong("id");
                movie.setId(id);
                title = (String)movieObject.get("title");
                movie.setTitle(title);
                image_path = movieObject.isNull("poster_path")?"":(String)movieObject.get("poster_path");
                movie.setImage_path(image_path);
                popularity = movieObject.getDouble("popularity");
                movie.setPopularity(popularity);
                vote_count = movieObject.getLong("vote_count");
                movie.setVote_count(vote_count);
                vote_average = movieObject.getDouble("vote_average");
                movie.setVote_average(vote_average);
                overview = (String)movieObject.get("overview");
                movie.setOverview(overview);
                release_date = (String)movieObject.getString("release_date");
                movie.setRelease_date(release_date);
                movieService.addMovie(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return movieService.getListOfMovies();
    }
}
