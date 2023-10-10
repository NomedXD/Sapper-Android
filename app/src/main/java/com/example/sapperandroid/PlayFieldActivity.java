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
    private ImageAdapter adapter;
    private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_field);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        gridView = findViewById(R.id.sapperField);
        gridView.setOnItemClickListener(gridviewOnItemClickListener);

        adapter = new ImageAdapter(this, width/20, height/10);

        if (CommonVars.field == null) {
            CommonVars.field = new Field();
            CommonVars.field.generate(25);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridView.setNumColumns(width / (width / 10));
            adapter = new ImageAdapter(this, width / 10, height / 20);
            adapter.setField(CommonVars.field, false);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridView.setNumColumns(width / (width / 20));
            adapter = new ImageAdapter(this, width / 20, height / 10);
            adapter.setField(CommonVars.field, true);
        }
        gridView.setAdapter(adapter);
    }

    private int[] getRelativeToDevicePositionCoordinates(int position) {
        int[] coordinates = new int[2];
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            coordinates[0] = 10 - 1 - position / 20;
            coordinates[1] = position % 20;
        } else {
            coordinates[0] = position % 10;
            coordinates[1] = position / 10;
        }
        return coordinates;
    }

    private final GridView.OnItemClickListener gridviewOnItemClickListener = (parent, v, position, id) -> {
        int[] relativeCoordinates = getRelativeToDevicePositionCoordinates(position);
        int x = relativeCoordinates[0];
        int y = relativeCoordinates[1];
        ((ImageAdapter)gridView.getAdapter()).setGridCellImg(position, CommonVars.field.getCell(y, x).open().getImgReference());
    };
}
