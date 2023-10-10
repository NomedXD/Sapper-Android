package com.example.gameLogic;

public class Cell {
    private InnerType innerType;
    private boolean isOpened;

    public Cell() {
        innerType = InnerType.EMPTY;
        isOpened = false;
    }

    public Cell(InnerType innerType, boolean isOpened) {
        this.innerType = innerType;
        this.isOpened = false;
    }

    public InnerType getInnerType() {
        return innerType;
    }

    public void setInnerType(InnerType innerType) {
        this.innerType = innerType;
    }
}
