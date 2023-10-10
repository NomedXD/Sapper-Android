package com.example.gameLogic;

public class Cell {
    private Type inner;
    private Type cover;
    private boolean isOpened;

    public Cell() {
        inner = Type.EMPTY;
        cover = Type.CLOSED;
        isOpened = false;
    }

    public Cell(Type inner, Type cover, boolean isOpened) {
        this.inner = inner;
        this.cover = cover;
        this.isOpened = false;
    }

    public Type getInner() {
        return inner;
    }

    public void setInner(Type inner) {
        this.inner = inner;
    }

    public Type getCover() {
        return cover;
    }

    public void setCover(Type cover) {
        this.cover = cover;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public Type getCellType() {
        return isOpened ? inner : cover;
    }

    //Return inner type of cell
    public Type open() {
        isOpened = true;
        return inner;
    }
}
