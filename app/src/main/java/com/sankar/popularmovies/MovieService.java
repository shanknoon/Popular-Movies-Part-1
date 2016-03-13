package com.sankar.popularmovies;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sankar on 1/24/2016.
 */
public class MovieService {

    private static MovieService instance = null;
    public ArrayList<Movie> listOfMovies;

    private MovieService()
    {
        listOfMovies = new ArrayList<>();
    }

    public static MovieService getInstance()
    {
        if(instance == null){
            instance = new MovieService();
        }

        return instance;
    }

    public ArrayList<Movie> getListOfMovies(){
        return listOfMovies;
    }

    public void addAllMovies(ArrayList<Movie> movieList){
        listOfMovies.addAll(movieList);
    }

    public void removeAllMovies()
    {
        listOfMovies.clear();
    }

    public void addMovie(Movie movie)
    {
        listOfMovies.add(movie);
    }

    public Movie getMovie(int position)
    {
        return listOfMovies.get(position);
    }
}
