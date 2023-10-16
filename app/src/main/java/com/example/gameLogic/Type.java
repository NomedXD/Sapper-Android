package com.example.gameLogic;

import com.example.sapperandroid.R;

public enum Type {
    BOMB(R.drawable.bomb), EMPTY(R.drawable.empty), CLOSED(R.drawable.unknown_dark), FLAG(R.drawable.flag_dark),
    WRONG_FLAG(R.drawable.flag_dark_wrong), NUMBER_1(R.drawable.num1), NUMBER_2(R.drawable.num2),
    BOMB_BOOM(R.drawable.bomb_boom), NUMBER_3(R.drawable.num3), NUMBER_4(R.drawable.num4), NUMBER_5(R.drawable.num5),
    NUMBER_6(R.drawable.num6), NUMBER_7(R.drawable.num7), NUMBER_8(R.drawable.num8);

    private final Integer imgReference;

    Type(Integer imgReference) {
        this.imgReference = imgReference;
    }

    public Integer getImgReference() {
        return imgReference;
    }
}
