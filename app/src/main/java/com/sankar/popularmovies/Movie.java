package com.sankar.popularmovies;

/**
 * Created by muthu ganesan on 1/24/2016.
 */
public class Movie {

    private long id;
    private String title;
    private String image_path;
    private double popularity;
    private long vote_count;
    private double vote_average;
    private String overview;
    private String release_date;

    StringBuffer baseUrl = new StringBuffer("http://image.tmdb.org/t/p/w185");

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        baseUrl.append(image_path);
        this.image_path = baseUrl.toString();
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getVote_count() {
        return vote_count;
    }

    public void setVote_count(long vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image_path='" + image_path + '\'' +
                ", popularity=" + popularity +
                ", vote_count=" + vote_count +
                ", vote_average=" + vote_average +
                ", overview='" + overview + '\'' +
                ", release_date=" + release_date +
                '}';
    }
}
