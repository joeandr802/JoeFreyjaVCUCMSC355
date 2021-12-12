package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class Play extends AppCompatActivity {

    public Button button;
    static int sides = 8;
    static int mineNumber = 10;
    static int howManyMoves;
    static int MaxSides = 18;
    static int MaxMines = 70;
    static boolean isFlagging = false;
    static int[] BUTTON_IDS = {
            R.id.tile00,
            R.id.tile01,
            R.id.tile02,
            R.id.tile03,
            R.id.tile04,
            R.id.tile05,
            R.id.tile06,
            R.id.tile07,
            R.id.tile10,
            R.id.tile11,
            R.id.tile12,
            R.id.tile13,
            R.id.tile14,
            R.id.tile15,
            R.id.tile16,
            R.id.tile17,
            R.id.tile20,
            R.id.tile21,
            R.id.tile22,
            R.id.tile23,
            R.id.tile24,
            R.id.tile25,
            R.id.tile26,
            R.id.tile27,
            R.id.tile30,
            R.id.tile31,
            R.id.tile32,
            R.id.tile33,
            R.id.tile34,
            R.id.tile35,
            R.id.tile36,
            R.id.tile37,
            R.id.tile40,
            R.id.tile41,
            R.id.tile42,
            R.id.tile43,
            R.id.tile44,
            R.id.tile45,
            R.id.tile46,
            R.id.tile47,
            R.id.tile50,
            R.id.tile51,
            R.id.tile52,
            R.id.tile53,
            R.id.tile54,
            R.id.tile55,
            R.id.tile56,
            R.id.tile57,
            R.id.tile60,
            R.id.tile61,
            R.id.tile62,
            R.id.tile63,
            R.id.tile64,
            R.id.tile65,
            R.id.tile66,
            R.id.tile67,
            R.id.tile70,
            R.id.tile71,
            R.id.tile72,
            R.id.tile73,
            R.id.tile74,
            R.id.tile75,
            R.id.tile76,
            R.id.tile77
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        button = findViewById(R.id.mainMenu);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Play.this, Menu0.class);
            startActivity(intent);
        });
        //main menu button

        button = findViewById(R.id.flagOn);
        button.setOnClickListener(v -> isFlagging = true);
        //flagging mode button

        button = findViewById(R.id.flagOff);
        button.setOnClickListener(v -> isFlagging = false);
        //uncovering mode button

        playGame();
    }

    public static boolean validPlay(int xCoord, int yCoord){
        return (xCoord >= 0 && xCoord < sides) && (yCoord >= 0 && yCoord < sides);
    }
    //determines if a space is on the board

    public static int countNearbyMines(Tile[][] testBoard, int xCoord, int yCoord){
        int counter = 0;
        //how many mines are next to the space chosen

        if(validPlay(xCoord-1, yCoord-1)){
            if(mineIsHere(xCoord-1,yCoord-1,testBoard))
                counter++;
        }
        //is a mine a N.W. Neighbor

        if(validPlay(xCoord, yCoord-1)){
            if(mineIsHere(xCoord,yCoord-1,testBoard))
                counter++;
        }
        //is a mine a N. Neighbor

        if(validPlay(xCoord+1, yCoord-1)){
            if(mineIsHere(xCoord+1,yCoord-1,testBoard))
                counter++;
        }
        //is a mine a N.E. Neighbor

        if(validPlay(xCoord-1, yCoord)){
            if(mineIsHere(xCoord-1,yCoord,testBoard))
                counter++;
        }
        //is a mine a W. Neighbor

        if(validPlay(xCoord+1, yCoord)){
            if(mineIsHere(xCoord+1,yCoord,testBoard))
                counter++;
        }
        //is a mine an E. Neighbor

        if(validPlay(xCoord-1, yCoord+1)){
            if(mineIsHere(xCoord-1,yCoord+1,testBoard))
                counter++;
        }
        //is a mine a S.W. Neighbor

        if(validPlay(xCoord, yCoord+1)){
            if(mineIsHere(xCoord,yCoord+1,testBoard))
                counter++;
        }
        //is a mine a S. Neighbor

        if(validPlay(xCoord+1, yCoord+1)){
            if(mineIsHere(xCoord+1,yCoord+1,testBoard))
                counter++;
        }
        //is a mine a S.E. Neighbor

        return counter;
    }
    //counts how many mines are next to the value chosen

    public void zeroTiler(Tile[][]testBoard, int xCoord, int yCoord){
        if(validPlay(xCoord-1,yCoord-1) && !testBoard[xCoord-1][yCoord-1].getHasMine()){
            displayNum(testBoard,xCoord-1,yCoord-1);
        }
        if(validPlay(xCoord,yCoord-1) && !testBoard[xCoord][yCoord-1].getHasMine()){
            displayNum(testBoard,xCoord,yCoord-1);
        }
        if(validPlay(xCoord+1,yCoord-1) && !testBoard[xCoord+1][yCoord-1].getHasMine()){
            displayNum(testBoard,xCoord+1,yCoord-1);
        }
        if(validPlay(xCoord-1,yCoord) && !testBoard[xCoord-1][yCoord].getHasMine()){
            displayNum(testBoard,xCoord-1,yCoord);
        }
        if(validPlay(xCoord+1,yCoord) && !testBoard[xCoord+1][yCoord].getHasMine()){
            displayNum(testBoard,xCoord+1,yCoord);
        }
        if(validPlay(xCoord-1,yCoord+1) && !testBoard[xCoord-1][yCoord+1].getHasMine()){
            displayNum(testBoard,xCoord-1,yCoord+1);
        }
        if(validPlay(xCoord,yCoord+1 )&& !testBoard[xCoord][yCoord+1].getHasMine()){
            displayNum(testBoard,xCoord,yCoord+1);
        }
        if(validPlay(xCoord+1,yCoord+1) && !testBoard[xCoord+1][yCoord+1].getHasMine()){
            displayNum(testBoard,xCoord+1,yCoord+1);
        }
        //if spaces exist next to a space that equals 0, display those tiles as well
    }
    //displays all spaces around a zero tile if it is chosen

    public void displayNum(Tile[][] tileBoard, int xCoord, int yCoord){
        int counter;
        counter = countNearbyMines(tileBoard, xCoord, yCoord);
        tileBoard[xCoord][yCoord].setSurround(counter);

        howManyMoves--;

        if(counter == 0){
            zeroTiler(tileBoard,xCoord,yCoord);
        }
        //if a space is chosen that equals 0, show all neighboring tiles

        checkEnd(tileBoard, xCoord, yCoord);
    }
    //displays the number of mines neighboring a given tile

    public void checkEnd(Tile[][] tileBoard, int xCoord, int yCoord){
        if (tileBoard[xCoord][yCoord].getCovered()){
            return;
        }
        //if the user didn't choose a slot that wasn't already chosen

        if (tileBoard[xCoord][yCoord].getHasMine()){
            setContentView(R.layout.activity_lose);
            return;
        }
        //if the player chose a bomb

        if (howManyMoves == 0) {
            setContentView(R.layout.activity_win);
        }
        //if the player has uncovered all non-bomb spaces

    }

    public static void resetMine(int x, int y, Tile[][] tileBoard) {
        int newX, newY, randomLoc;

        while(tileBoard[x][y].getHasMine()) {
            randomLoc = (int) (Math.random() * (sides * sides));
            newX = randomLoc / sides;
            newY = randomLoc % sides;

            if(!tileBoard[newX][newY].getHasMine()) {
                tileBoard[newX][newY].setHasMine(true);
                tileBoard[x][y].setHasMine(false);
            }
        }
    }
    //moves the mine in case the first move would've chosen it

    public static boolean mineIsHere(int x, int y, Tile[][] tileBoard){
        if(validPlay(x, y)) {
            return tileBoard[x][y].getHasMine();
        }
        return false;
    }
    //tells if a mine is at the given location

    public static void setMines(Tile[][] tileBoard, int[][] mineLoc){
        boolean[] marker= new boolean[MaxSides * MaxSides];
        int xCoord, yCoord, randomLoc;

        for(int i = 0; i<mineNumber;){
            randomLoc = (int) (Math.random() * (sides * sides));
            xCoord = randomLoc / sides;
            yCoord = randomLoc % sides;
            //sets random locations

            if(!marker[randomLoc]){
                mineLoc[i][0] = xCoord;
                mineLoc[i][1] = yCoord;
                //keeps track of the location of this mine

                tileBoard[mineLoc[i][0]][mineLoc[i][1]].setHasMine(true);
                //stores the mine in the actual testBoard

                marker[randomLoc] = true;
                i++;
                //makes the randomizer not choose the same mine location twice and keeps track that a mine was set
            }
        }
        //sets mines on the board used to check if a mine is chosen
    }
    //chooses where the locations of the mines will be

    public void setBoard(Tile[][] tileBoard, Button[][] buttonBoard){
        for(int i = 0; i<sides; i++){
            for(int j = 0; j<sides; j++){

                for (int id: BUTTON_IDS) {
                    buttonBoard[i][j] = findViewById(id);
                    tileBoard[i][j] = new Tile(i, j, id);
                }

                button = buttonBoard[i][j];
                button.setOnClickListener(v -> {
                    if (isFlagging) {
                        findTileById(tileBoard, button.getId()).toggleFlagged();
                        button.setVisibility(View.INVISIBLE);
                        //test for idea i have
                    }

                    else {
                        if (howManyMoves == (sides * sides) - mineNumber){
                            if(mineIsHere(findTileById(tileBoard, button.getId()).getX(),
                                    findTileById(tileBoard, button.getId()).getY(), tileBoard)){
                                resetMine(findTileById(tileBoard, button.getId()).getX(),
                                        findTileById(tileBoard, button.getId()).getY(), tileBoard);
                            }
                        }

                        displayNum(tileBoard,
                                findTileById(tileBoard, button.getId()).getX(),
                                findTileById(tileBoard, button.getId()).getY());

                        //CODE FOR REVEALING THE SPACE GOES HERE

                        button.setVisibility(View.GONE);
                        //default for now; makes button disappear
                    }
                });
            }
        }
    }

    public Tile findTileById(Tile[][] tileBoard, int id) {
        for(int i = 0; i<sides; i++) {
            for (int j = 0; j < sides; j++) {
                if (tileBoard[i][j].getButtonID() == id) {
                    return tileBoard[i][j];
                }
            }
        }
        return null;
    }

    public void playGame(){

        Tile[][] tileBoard = new Tile[sides][sides];
        //creates a board to store the tile objects

        Button[][] buttonBoard = new Button[sides][sides];

        howManyMoves = (sides * sides) - mineNumber;

        int[][] mineLoc = new int[MaxMines][2];
        //keeps track if player has uncovered all non-mine tiles and where the mines are

        setBoard(tileBoard, buttonBoard);
        //sets up the board and buttons

        setMines(tileBoard, mineLoc);
        //puts the mines randomly in the testingBoard
    }
}