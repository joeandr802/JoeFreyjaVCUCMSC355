package com.example.mineweper;

public class Tile {
    private boolean flagged;
    private boolean covered;
    private boolean hasMine;
    private int surround;
    private static int xCoord;
    private static int yCoord;
    private static int buttonID;

    public Tile(int x, int y, int id) {
        flagged = false;
        covered = true;
        hasMine = false;
        surround = 0;
        xCoord = x;
        yCoord = y;
        buttonID = id;
    }

    public boolean getFlagged() {
        return flagged;
    }

    public void toggleFlagged() {
        flagged = !flagged;
    }

    public boolean getHasMine() {
        return hasMine || getSurround() == 9;
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

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public int getButtonID() {
        return buttonID;
    }
}
