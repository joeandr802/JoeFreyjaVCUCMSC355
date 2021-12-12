package com.example.mineweper;

public class Tile {
    private boolean flagged;
    private boolean covered;
    private boolean hasMine;

    public Tile() {
        flagged = false;
        covered = true;
        hasMine = false;
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
    }

    public boolean getCovered() {
        return covered;
    }

    public void uncover() {
        covered = false;
    }
}
