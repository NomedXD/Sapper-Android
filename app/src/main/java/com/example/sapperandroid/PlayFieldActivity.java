package com.example.sapperandroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;

import com.example.commonResource.CommonVars;
import com.example.gameLogic.Field;


public class PlayFieldActivity extends Activity {

    private Integer difficulty;
    ImageAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_field);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        GridView gridview = findViewById(R.id.sapperField);
        gridview.setOnItemClickListener(gridviewOnItemClickListener);

        adapter = new ImageAdapter(this, width/20, height/10);

        if (CommonVars.field == null) {
            CommonVars.field = new Field();
            CommonVars.field.generate(25);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridview.setNumColumns(width / (width / 10));
            adapter = new ImageAdapter(this, width / 10, height / 20);
            adapter.setField(CommonVars.field, false);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridview.setNumColumns(width / (width / 20));
            adapter = new ImageAdapter(this, width / 20, height / 10);
            adapter.setField(CommonVars.field, true);
        }
        gridview.setAdapter(adapter);
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener = (parent, v, position, id) -> {
        //Here logic on opening cells!!!
        /*
        Field field = new Field();
        field.generate(25);
        System.out.println("haha");
        adapter.setField(field, false);
        */
    };
}
