package com.example.sapperandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.commonResource.CommonVars;
import com.example.commonResource.Const;
import com.example.gameLogic.Type;

public class PlayFieldActivity extends Activity {

    private GridView gridView;
    private Button backButton;
    private Button newButton;
    private TextView winLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_field);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        backButton = findViewById(R.id.btnBack);
        newButton = findViewById(R.id.btnNew);
        winLabel = findViewById(R.id.win_label);
        showMessage(CommonVars.message);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            finish();
            startActivity(intent);
        });
        newButton.setOnClickListener(view -> {
            CommonVars.field.generate((int) (Const.BOMBS_PER_LEVEL * (CommonVars.difficulty + 1)));
            ((ImageAdapter)gridView.getAdapter()).setField(CommonVars.field, getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
            winLabel.setVisibility(View.INVISIBLE);
        });

        gridView = findViewById(R.id.sapperField);
        gridView.setOnItemClickListener(gridviewOnItemClickListener);
        gridView.setOnItemLongClickListener(gridviewOnItemLongClickListener);

        ImageAdapter adapter = new ImageAdapter(this, width / Const.HEIGHT, height / Const.WIDTH);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridView.setNumColumns(width / (width / Const.WIDTH));
            adapter = new ImageAdapter(this, width / Const.WIDTH, height / Const.HEIGHT);
            adapter.setField(CommonVars.field, false);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridView.setNumColumns(width / (width / Const.HEIGHT));
            adapter = new ImageAdapter(this, width / Const.HEIGHT, height / Const.WIDTH);
            adapter.setField(CommonVars.field, true);
        }
        gridView.setAdapter(adapter);
    }

    private int[] getRelativeToDevicePositionCoordinates(int position) {
        int[] coordinates = new int[2];
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            coordinates[0] = Const.WIDTH - 1 - position / Const.HEIGHT;
            coordinates[1] = position % Const.HEIGHT;
        } else {
            coordinates[0] = position % Const.WIDTH;
            coordinates[1] = position / Const.WIDTH;
        }
        return coordinates;
    }

    private void vibrate(int millisTime) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(VibrationEffect.createOneShot(millisTime, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    private void showMessage(Message message) {
        winLabel.setVisibility(View.VISIBLE);
        winLabel.setTextColor(ContextCompat.getColor(getApplicationContext(), message.getColor()));
        winLabel.setText(message.getText());
    }

    private final GridView.OnItemClickListener gridviewOnItemClickListener = (parent, v, position, id) -> {
        if (CommonVars.field.isGameEnded()) {
            return;
        }
        int[] relativeCoordinates = getRelativeToDevicePositionCoordinates(position);
        int x = relativeCoordinates[0];
        int y = relativeCoordinates[1];
        CommonVars.field.reveal(x, y);
        if (CommonVars.field.getCell(x, y).getInner() == Type.BOMB && CommonVars.field.getCell(x, y).getCover() != Type.FLAG) {
            CommonVars.field.revealAll();
            CommonVars.field.getCell(x, y).setInner(Type.BOMB_BOOM);
            CommonVars.message = new Message(Const.loseMessage, R.color.endGameColor);
            showMessage(CommonVars.message);
            vibrate(Const.winLoseVibroDuration);
        }
        if (CommonVars.field.isDemined()) {
            CommonVars.message = new Message(Const.winMessage, R.color.winColor);
            showMessage(CommonVars.message);
            vibrate(Const.winLoseVibroDuration);
        }
        ((ImageAdapter)gridView.getAdapter()).setField(CommonVars.field, getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    };

    private final AdapterView.OnItemLongClickListener gridviewOnItemLongClickListener = (parent, v, position, id) -> {
        if (CommonVars.field.isGameEnded()) {
            return true;
        }
        int[] relativeCoordinates = getRelativeToDevicePositionCoordinates(position);
        int x = relativeCoordinates[0];
        int y = relativeCoordinates[1];
        ((ImageAdapter)gridView.getAdapter()).setGridCellImg(position, CommonVars.field.getCell(x, y).changeFlag().getImgReference());
        vibrate(Const.flagVibroDuration);
        return true;
    };
}
