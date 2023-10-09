package com.example.sapperandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner levelSpinner;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levelSpinner = findViewById(R.id.levelSpinner);
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), PlayFieldActivity.class);
            intent.putExtra("levelNum", levelSpinner.getSelectedItemId());
            startActivity(intent);
        });
    }
}