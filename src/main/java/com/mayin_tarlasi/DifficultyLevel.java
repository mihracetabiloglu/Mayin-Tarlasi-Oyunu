package com.mayin_tarlasi;

public enum DifficultyLevel {

    KOLAY(5, 5, 5),
    ORTA(9, 9, 10),
    ZOR(15, 15, 20);

    private final int rows;
    private final int cols;
    private final int mines;

    DifficultyLevel(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public int getMines() {
        return mines;
    }
}

