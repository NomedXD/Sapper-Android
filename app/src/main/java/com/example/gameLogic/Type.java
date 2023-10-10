package com.example.gameLogic;

import com.example.sapperandroid.R;

public enum Type {
    BOMB(R.drawable.bomb), EMPTY(R.drawable.nobomb), CLOSED(R.drawable.closed),
    NUMBER_1(R.drawable.num1), NUMBER_2(R.drawable.num2), NUMBER_3(R.drawable.num3),
    NUMBER_4(R.drawable.num4), NUMBER_5(R.drawable.num5), NUMBER_6(R.drawable.num6),
    NUMBER_7(R.drawable.num7), NUMBER_8(R.drawable.num8);

    private final Integer imgReference;

    Type(Integer imgReference) {
        this.imgReference = imgReference;
    }

    public Integer getImgReference() {
        return imgReference;
    }
}
