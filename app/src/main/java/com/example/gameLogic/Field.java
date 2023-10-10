package com.example.gameLogic;

public class Field {
    private Cell[][] field;
    private final int WIDTH = 10;
    private final int HEIGHT = 20;


    public Field() {
        field = new Cell[HEIGHT][WIDTH];
    }

    private int generateInt(int min, int max) {
        return (int)( Math.random() * (max - min + 1) + min);
    }

    public void generate(int numMines) {
        field = new Cell[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                field[i][j] = new Cell();
            }
        }
        int i = 0;
        while (i < numMines) {//We don't want mines to overlap, so while loop
            int x = generateInt(0, WIDTH - 1);
            int y = generateInt(0, HEIGHT - 1);
            if (field[y][x].getInner() == Type.BOMB) continue;
            field[y][x].setInner(Type.BOMB);
            i++;
        }
    }

    public Cell getCell(int y, int x) {
        if ((y < 0 || y > HEIGHT - 1) || (x < 0 || x > WIDTH - 1)) {
            throw new NullPointerException("Index out of bounds!");
        }
        return field[y][x];
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
