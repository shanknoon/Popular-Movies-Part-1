package com.sankar.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageAdapter mImageAdapter;
    ArrayList<Movie> movieList = MovieService.getInstance().getListOfMovies();
    String sortBy = MovieConstants.POPULARITY;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_main);

        if (savedInstanceState == null && movieList.isEmpty()) {
            new FetchMoviesTask().execute(MovieConstants.POPULARITY);
        }

        mImageAdapter = new ImageAdapter(this, MovieService.getInstance().getListOfMovies());
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mImageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.ARG_POSITION, position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sortByPopularity)
        {
            sortBy = MovieConstants.POPULARITY;
            new FetchMoviesTask().execute(sortBy);
            return true;
        }else if(id == R.id.sortByRated)
        {
            sortBy = MovieConstants.HIGHEST_RATED;
            new FetchMoviesTask().execute(sortBy);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList> {

        @Override
        protected ArrayList doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String movieJsonStr = "";

            try {
                String baseUrl = "http://api.themoviedb.org/3/discover/movie";
                StringBuilder urlBuilder = new StringBuilder(baseUrl);
                urlBuilder.append("?").append(MovieConstants.SORT_BY).append("=").append(params[0])
                        .append(".").append(MovieConstants.DESC).append("&")
                        .append("api_key").append("=").append(MovieConstants.API_KEY);

                URL url = new URL(urlBuilder.toString());//"http://api.themoviedb.org/3/discover/movie?"+MovieConstants.SORT_BY+"="+params[0]+"."+MovieConstants.DESC+"&api_key="+MovieConstants.API_KEY);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer = buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                movieJsonStr = buffer.toString();
                //Log.v("movieJsonStr: ", movieJsonStr);
                if (!movieJsonStr.isEmpty()) {
                    new ImageParser().parseJsonString(movieJsonStr);
                }
            } catch (IOException e) {
                Log.e("MovieFetchTask", "error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("FetchMoviesTask", "error", e);
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList listMovie) {

            mImageAdapter.notifyDataSetChanged();

        }

    }
}
