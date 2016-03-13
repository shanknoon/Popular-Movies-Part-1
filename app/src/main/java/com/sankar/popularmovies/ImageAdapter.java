package com.sankar.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sankara narayanan on 1/13/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Movie> mListOfMovies = new ArrayList<Movie>();

    public ImageAdapter(Context ctx, ArrayList<Movie> listMovie) {
        mContext = ctx;
        mListOfMovies = listMovie;
    }

    public int getCount() {
        return mListOfMovies.size();
    }

    public Object getItem(int position) {
        return mListOfMovies.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(mListOfMovies.get(position).getImage_path()).placeholder(R.drawable.placeholder).into(imageView);

        return imageView;
    }

}