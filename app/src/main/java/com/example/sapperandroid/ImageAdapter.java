package com.example.sapperandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.gameLogic.Field;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final int imgWidth;
    private final int imgHeight;

    // references to our images
    public	Integer[] mThumbIds = new Integer[200];

    public ImageAdapter(Context c, int imgWidth, int imgHeight) {
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        mContext = c;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
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
            imageView.setLayoutParams(new GridView.LayoutParams(imgWidth, imgHeight));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public void setField(Field field, boolean isHorizontal) {
        List<Integer> imgRefs = new ArrayList<>();
        if (!isHorizontal) {
            for (int i = 0; i < field.getHEIGHT(); ++i) {
                for (int j = 0; j < field.getWIDTH(); ++j) {
                    imgRefs.add(field.getCell(i, j).getInnerType().getImgReference());
                }
            }
        } else {
            for (int i = field.getWIDTH() - 1; i >= 0; --i) {
                for (int j = 0; j < field.getHEIGHT(); ++j) {
                    imgRefs.add(field.getCell(j, i).getInnerType().getImgReference());
                }
            }
        }
        mThumbIds = imgRefs.toArray(mThumbIds);
    }
}
