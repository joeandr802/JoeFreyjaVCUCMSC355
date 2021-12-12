package com.example.mineweper;

public class Tile {
    private boolean flagged;
    private boolean covered;
    private boolean hasMine;
    private int surround;

    public Tile() {
        flagged = false;
        covered = true;
        hasMine = false;
        surround = 0;
    }

    public boolean getFlagged() {
        return flagged;
    }

    public void toggleFlagged() {
        flagged = !flagged;
    }

    public boolean getHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean mine) {
        hasMine = mine;
        if (hasMine) {
            surround = 9;
        }
    }

    public boolean getCovered() {
        return covered;
    }

    public void uncover() {
        covered = false;
    }

    public int getSurround() {
        return surround;
    }

    public void setSurround(int mineNum) {
        if (-1 < mineNum && mineNum < 9) {
            surround = mineNum;
        }
    }
}
