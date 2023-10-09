package com.example.sapperandroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;


public class PlayFieldActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_field);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        GridView gridview = (GridView) findViewById(R.id.sapperField);
        gridview.setOnItemClickListener(gridviewOnItemClickListener);

        ImageAdapter adapter = new ImageAdapter(this, width/20, height/10);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridview.setNumColumns(width / (width / 10));
            adapter = new ImageAdapter(this, width / 10, height / 20);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridview.setNumColumns(width / (width / 20));
            adapter = new ImageAdapter(this, width / 20, height / 10);
        }

        gridview.setAdapter(adapter);
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener = (parent, v, position, id) -> {

    };
}
