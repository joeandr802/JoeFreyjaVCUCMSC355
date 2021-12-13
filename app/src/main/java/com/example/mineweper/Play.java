package com.example.mineweper;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class Play extends AppCompatActivity {

    public Button button;
    static int sides;
    static int mineNumber;
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
    //array of tile ids in activity_play.xml

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
        return (xCoord > -1 && xCoord < sides) && (yCoord > -1 && yCoord < sides);
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

    public void zeroTiler(Tile[][]testBoard, int xCoord, int yCoord, int buttonId){
        //int buttonId;
        Button tempButton;

        if(validPlay(xCoord-1,yCoord-1) &&
                testBoard[xCoord-1][yCoord-1].getSurround()!=0){
            buttonId = BUTTON_IDS[((xCoord-1)*8)+(yCoord-1)];
           displayNum(testBoard,xCoord-1,yCoord-1, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();
        }
        if(validPlay(xCoord,yCoord-1) &&
                testBoard[xCoord][yCoord-1].getSurround()!=0){
            buttonId = BUTTON_IDS[(xCoord*8)+(yCoord-1)];
            displayNum(testBoard,xCoord,yCoord-1, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        if(validPlay(xCoord+1,yCoord-1) &&
                testBoard[xCoord+1][yCoord-1].getSurround()!=0){
            buttonId = BUTTON_IDS[((xCoord+1)*8)+(yCoord-1)];
            displayNum(testBoard,xCoord+1,yCoord-1, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        if(validPlay(xCoord-1,yCoord) &&
                testBoard[xCoord-1][yCoord].getSurround()!=0){
            buttonId = BUTTON_IDS[((xCoord-1)*8)+yCoord];
            displayNum(testBoard,xCoord-1,yCoord, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        if(validPlay(xCoord+1,yCoord) &&
                testBoard[xCoord+1][yCoord].getSurround()!=0){
            buttonId = BUTTON_IDS[((xCoord+1)*8)+yCoord];
            displayNum(testBoard,xCoord+1,yCoord, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        if(validPlay(xCoord-1,yCoord+1) &&
                testBoard[xCoord-1][yCoord+1].getSurround()!=0){
            buttonId = BUTTON_IDS[((xCoord-1)*8)+(yCoord+1)];
            displayNum(testBoard,xCoord-1,yCoord+1, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        if(validPlay(xCoord,yCoord+1 )&&
                testBoard[xCoord][yCoord+1].getSurround()!=0){
            buttonId = BUTTON_IDS[(xCoord*8)+(yCoord+1)];
            displayNum(testBoard,xCoord,yCoord+1, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        if(validPlay(xCoord+1,yCoord+1) &&
                testBoard[xCoord+1][yCoord+1].getSurround()!=0){
            buttonId = BUTTON_IDS[((xCoord+1)*8)+(yCoord+1)];
            displayNum(testBoard,xCoord+1,yCoord+1, buttonId);
            tempButton = findViewById(buttonId);
            tempButton.performClick();

        }
        //if spaces exist next to a space that equals 0, display those tiles as well
    }
    //displays all spaces around a zero tile if it is chosen

    public void displayNum(Tile[][] tileBoard, int xCoord, int yCoord, int buttonId){
        int counter;
        counter = countNearbyMines(tileBoard, xCoord, yCoord);
        tileBoard[xCoord][yCoord].setSurround(counter);

        Button tempButton = findViewById(buttonId);

        findTileById(tileBoard, buttonId).uncover();

        tempButton.setText(String.valueOf(findTileById(tileBoard, buttonId).getSurround()));
        
        /*int buttonId;
        Button tempButton;
        buttonId = BUTTON_IDS[((xCoord)*8)+(yCoord)];
        tempButton = findViewById(buttonId);
        tempButton.performClick();*/

        howManyMoves--;

        if(counter == 0){
            zeroTiler(tileBoard,xCoord,yCoord,buttonId);
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

        if (mineIsHere(xCoord, yCoord, tileBoard)){
            setContentView(R.layout.activity_lose);
            return;
        }
        //if the player chose a bomb

        if (howManyMoves < 1) {
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
                //helps the randomizer choose unique mine locations and keeps track of set mines
            }
        }
        //sets mines on the board used to check if a mine is chosen
    }
    //chooses where the locations of the mines will be

    public void setBoard(Tile[][] tileBoard){
        for(int i = 0; i<sides; i++){
            for(int j = 0; j<sides; j++){

                int buttonId;
                Button setupButton;

                buttonId = BUTTON_IDS[(i*8)+j];
                tileBoard[i][j] = new Tile(i, j, buttonId);

                setupButton = findViewById(buttonId);

                setupButton.setOnClickListener(v -> {
                    if (findTileById(tileBoard, buttonId).getCovered()) {
                        Button tempButton;
                        tempButton = findViewById(buttonId);

                        if (isFlagging) {
                            findTileById(tileBoard, buttonId).toggleFlagged();
                            if (findTileById(tileBoard, buttonId).getFlagged()) {
                                tempButton.setText("F");
                            } else {
                                tempButton.setText("-");
                            }
                        }
                        //handles flagging a space

                        else {
                            if (howManyMoves == (sides * sides) - mineNumber) {
                                if (mineIsHere(findTileById(tileBoard, buttonId).getX(),
                                        findTileById(tileBoard, buttonId).getY(), tileBoard)) {
                                    resetMine(findTileById(tileBoard, buttonId).getX(),
                                            findTileById(tileBoard, buttonId).getY(), tileBoard);
                                }
                            }
                            //if this is the first turn and a mine is hit, reroll the mine

                            displayNum(tileBoard, findXById(buttonId), findYById(buttonId), buttonId);
                            //checks for empty tiles and whether the game should end

                            //findTileById(tileBoard, buttonId).uncover();

                           // tempButton.setText(String.valueOf(findTileById(tileBoard,
                                   // buttonId).getSurround()));
                        }
                        //handles uncovering a space
                    }
                });
            }
        }
    }
    //sets up the board

    public Tile findTileById(Tile[][] tileBoard, int id) {
        for(int i = 0; i<BUTTON_IDS.length; i++) {
            if (BUTTON_IDS[i] == id) {
                return tileBoard[i/8][i%8];
            }
        }
        return null;
    }

    public int findXById(int id) {
        for(int i = 0; i<BUTTON_IDS.length; i++) {
            if (BUTTON_IDS[i] == id) {
                return i/8;
            }
        }
        return -1;
    }

    public int findYById(int id) {
        for(int i = 0; i<BUTTON_IDS.length; i++) {
            if (BUTTON_IDS[i] == id) {
                return i%8;
            }
        }
        return -1;
    }

    //given an id of a button, finds its corresponding Tile and returns it

    public void playGame() {

        sides = 8;
        mineNumber = 10;
        howManyMoves = (sides * sides) - mineNumber;
        //formula used to determine the number of non-mine spaces

        Tile[][] tileBoard = new Tile[sides][sides];
        //creates a board to store the tile objects

        int[][] mineLoc = new int[MaxMines][2];
        //keeps track if player has uncovered all non-mine tiles and where the mines are

        setBoard(tileBoard);
        //sets up the board and buttons

        setMines(tileBoard, mineLoc);
        //puts the mines randomly in the testingBoard
    }
    //plays the game
}