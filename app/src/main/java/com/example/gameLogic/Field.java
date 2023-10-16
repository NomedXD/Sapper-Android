package com.example.gameLogic;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Cell[][] field;
    private final int WIDTH = 10;
    private final int HEIGHT = 20;

    private boolean isDeminingStarted;

    private int numMines;

    public Field() {
        field = new Cell[HEIGHT][WIDTH];
        isDeminingStarted = false;
        numMines = 0;
    }

    private int generateInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private boolean outBounds(int x, int y) {
        return (x < 0 || x > WIDTH - 1) || (y < 0 || y > HEIGHT - 1);
    }

    private int calcBombsNear(int x, int y) {
        /*
        if (outBounds(x, y)) return 0;
        int result = 0;
        for (int i = y - 1; i <= y + 1; ++i) {
            for (int j = x - 1; j <= x + 1; ++j) {
                    if (outBounds(j, i) || i == y && j == x) continue;
                    if (field[i][j].getInner() == Type.BOMB) {
                        ++result;
                }
            }
        }
        return result;
        */
        if (outBounds(x, y)) return 0;
        int i = 0;
        for (int offsetX = -1; offsetX <= 1; ++offsetX) {
            for (int offsetY = -1; offsetY <= 1; ++offsetY) {
                if (outBounds(offsetX + x, offsetY + y)) continue;
                if (field[offsetY + y][offsetX + x].getInner() == Type.BOMB) {
                    ++i;
                }
            }
        }
        return i;
    }

    private void countBombsNearEachCell() {
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                if (field[i][j].getInner() != Type.BOMB) {
                    int bombsNear = calcBombsNear(j, i);
                    if (bombsNear != 0) {
                        field[i][j].setInner(Type.valueOf("NUMBER_" + calcBombsNear(j, i)));
                    }
                }
            }
        }
    }

    private void moveAwayBomb(int x, int y) {
        class Coordinates {
            private final int x;
            private final int y;

            public Coordinates(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        if (field[y][x].getInner() != Type.BOMB) return;

        List<Coordinates> notBombs = new ArrayList<>();
        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                if (field[i][j].getInner() != Type.BOMB) {
                    notBombs.add(new Coordinates(j, i));
                }
            }
        }

        Coordinates randomPlace = new Coordinates(x, y);
        while (randomPlace.x == x && randomPlace.y == y) {
            randomPlace = notBombs.get(generateInt(0, notBombs.size() - 1));
        }

        field[randomPlace.y][randomPlace.x].setInner(Type.BOMB);
        field[y][x].setInner(Type.EMPTY);
        countBombsNearEachCell(); //SOOOO BAAAAD!!!, ONLY CELLS NEAR SOURCE AND DESTINATION and themselves may change value,
        // so it's a waste of resources to do traversal through all cells
    }

    public void reveal(int x, int y) {

        if (!isDeminingStarted && field[y][x].getInner() == Type.BOMB) {
            //Here logic to move bomb away and preferably to reserve some space near click(currently only move away mine implemented)
            moveAwayBomb(x, y);
            isDeminingStarted = true;
            reveal(x, y);
        } else {
            isDeminingStarted = true;
            if (outBounds(x, y)) return;
            if (field[y][x].isOpened()) return;
            field[y][x].open();
            if (calcBombsNear(x, y) != 0) return;
            reveal(x - 1, y - 1);
            reveal(x - 1, y + 1);
            reveal(x + 1, y - 1);
            reveal(x + 1, y + 1);
            reveal(x - 1, y);
            reveal(x + 1, y);
            reveal(x, y - 1);
            reveal(x, y + 1);
        }
    }

    public void generate(int numMines) {
        isDeminingStarted = false;
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
        this.numMines = numMines;
        countBombsNearEachCell();
    }

    public Cell getCell(int x, int y) {
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
