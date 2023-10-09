package com.example.sapperandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayFieldActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private SapperGridAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_field);

        final GridView g = (GridView) findViewById(R.id.sapperField);
        mAdapter = new SapperGridAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        g.setAdapter(mAdapter);
        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener((parent, v, position, id) -> {

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
