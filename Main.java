package cs401;

import java.util.Scanner;

public class Main {
    /*
    *________________________________________________________________________________________________*
    | This class allows the user to play text-based minesweeper with 3 different difficulty settings |
    | While it does not take into account empty spaces and fill other empty spaces near it,          |
    | it is still impossible to lose on the first turn.                                              |
    | Version: 1.0.1  Last Updated: 05:39 P.M. 10/31/2021  Last Updater: Freyja Halter               |
    |                                                                                                |
    | Last Update: Added functionality where 0s would activate nearby tiles                          |
    *________________________________________________________________________________________________*
     */

    static int sides;
    static int mineNumber;
    static int howManyMoves;
    static int MaxSides = 18;
    static int MaxMines = 70;
    //important values for entire class to keep in mind

    public static boolean validPlay(int xCoord, int yCoord){
        if((xCoord >= 0 && xCoord < sides) && (yCoord >= 0 && yCoord < sides))
            return true;
        return false;
    }
    //determines if a space is on the board

    public static int countNearbyMines(char[][] testBoard, int[][] mineLoc, int xCoord, int yCoord){
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

    public static void zeroTiler(char[][] playBoard, char[][]testBoard, int[][] mineLoc, int xCoord, int yCoord){
        if(validPlay(xCoord-1,yCoord-1) && testBoard[xCoord-1][yCoord-1] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord-1,yCoord-1);
        }
        if(validPlay(xCoord,yCoord-1) && testBoard[xCoord][yCoord-1] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord,yCoord-1);
        }
        if(validPlay(xCoord+1,yCoord-1) && testBoard[xCoord+1][yCoord-1] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord+1,yCoord-1);
        }
        if(validPlay(xCoord-1,yCoord) && testBoard[xCoord-1][yCoord] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord-1,yCoord);
        }
        if(validPlay(xCoord+1,yCoord) && testBoard[xCoord+1][yCoord] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord+1,yCoord);
        }
        if(validPlay(xCoord-1,yCoord+1) && testBoard[xCoord-1][yCoord+1] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord-1,yCoord+1);
        }
        if(validPlay(xCoord,yCoord+1 )&& testBoard[xCoord][yCoord+1] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord,yCoord+1);
        }
        if(validPlay(xCoord+1,yCoord+1) && testBoard[xCoord+1][yCoord+1] != '0'){
            displayNum(playBoard,testBoard,mineLoc,xCoord+1,yCoord+1);
        }
        //if spaces exist next to a space that equals 0, display those tiles as well
    }
    //displays all spaces around a zero tile if it is chosen

    public static void displayNum(char[][] playBoard, char[][] testBoard, int[][] mineLoc, int xCoord, int yCoord){
        int counter = 0;
        counter = counter + countNearbyMines(testBoard, mineLoc, xCoord, yCoord);
        playBoard[xCoord][yCoord] = (char)(counter+'0');
        testBoard[xCoord][yCoord] = (char)(counter+'0');
        howManyMoves--;

        if(counter == 0){
            zeroTiler(playBoard,testBoard,mineLoc,xCoord,yCoord);
        }
        //if a space is chosen that equals 0, show all neighboring tiles
    }
    //displays the number of mines neighboring a given tile

    public static boolean shouldReset(char[][] playBoard, char[][] testBoard, int[][] mineLoc, int xCoord, int yCoord){
        if(playBoard[xCoord][yCoord] != '~'){
            return false;
        }
        //if the user didn't choose a slot that wasn't already chosen

        if(testBoard[xCoord][yCoord] == '*'){
            showTheBoard(testBoard);
            System.out.println("*________________*");
            System.out.println("|Ha Ha! You Suck!|");
            System.out.println("*________________*");
            return true;
        }
        //if the player chose a bomb

        else{
            displayNum(playBoard, testBoard, mineLoc, xCoord, yCoord);
            //counts how many moves need to be made before the user is done

            return false;
        }
    }

    public static void resetMine(int x, int y, char[][] testboard){
        int i,j;

        for(i = 0; i < sides; i++){
            for(j = 0; j< sides; j++){
                if(testboard[i][j] != '*'){
                    testboard[i][j] = '*';
                    testboard[x][y] = '~';
                    return;
                }
                //makes the first location without the bomb have one instead of the one chosen by the user
            }
        }
    }
    //moves the mine in case the first move would've chosen it

    public static boolean mineIsHere(int x, int y, char[][] testboard){
        if(validPlay(x, y) == true) {
            if (testboard[x][y] == '*')
                return true;
        }
        return false;
    }
    //tells if a mine is at the given location

    public static void showTheBoard(char[][] playBoard){
        int i, j;

        System.out.print("*");
        for(i = 0; i < sides; i++){
            System.out.print("_");
        }
        System.out.println("*");
        //prints roof of the game board

        for(i = 0; i < sides; i++){
            System.out.print("|");
            for(j = 0; j < sides; j++) {
                System.out.print(playBoard[i][j]);
            }
            System.out.println("|");
            //prints the game board, row by row
        }

        System.out.print("*");
        for(i = 0; i < sides; i++){
            System.out.print("_");
        }
        System.out.println("*");
        //prints floor of the game board
    }

    public static void setMines(char[][] testBoard, int[][] mineLoc){
        boolean[] marker= new boolean[MaxSides * MaxSides];
        int xCoord, yCoord, randomLoc;

        for(int i = 0; i<mineNumber; i=i+0){
            randomLoc = (int) (Math.random() * (sides * sides));
            xCoord = randomLoc / sides;
            yCoord = randomLoc % sides;
            //sets random locations

            if(marker[randomLoc] == false){
                mineLoc[i][0] = xCoord;
                mineLoc[i][1] = yCoord;
                //keeps track of the location of this mine

                testBoard[mineLoc[i][0]][mineLoc[i][1]] = '*';
                //stores the mine in the actual testBoard

                marker[randomLoc] = true;
                i++;
                //makes the randomizer not choose the same mine location twice and keeps track that a mine was set
            }
        }
        //sets mines on the board used to check if a mine is chosen
        showTheBoard(testBoard);
    }
    //chooses where the locations of the mines will be

    public static void setBoard(char[][] playBoard, char[][] testBoard){
        for(int i = 0; i<sides; i++){
            for(int j = 0; j<sides; j++){
                playBoard[i][j] = '~';
                testBoard[i][j] = playBoard[i][j];
            }
        }
    }
    //resets the entire board to a basic state

    public static void playGame(){
        Scanner scan = new Scanner(System.in);

        boolean endOfGame = false;
        //flag for if the game has ended

        char[][] playBoard= new char[MaxSides][MaxSides], testBoard = new char[MaxSides][MaxSides];
        //creates a board for playing and for testing the code

        howManyMoves = (sides * sides) - mineNumber;
        int[][] mineLoc = new int[MaxMines][2];
        //keeps track if player has uncovered all non-mine tiles and where the mines are

        int xCoord, yCoord;
        //coordinates of location choice for later on

        setBoard(playBoard, testBoard);
        //reset the board

        setMines(testBoard, mineLoc);
        //puts the mines randomly in the testingBoard

        int curTurn = 0;
        //makes sure first tile chosen isn't a bomb

        while(endOfGame == false){
            showTheBoard(playBoard);
            //displays the current board every turn

            System.out.println("What is the x coordinate of your choice?");
            xCoord = scan.nextInt();
            System.out.println("What is the y coordinate of your choice?");
            yCoord = scan.nextInt();
            //gets location choice from user

            if (curTurn == 0){
                if(mineIsHere(xCoord, yCoord, testBoard) == true){
                    resetMine(xCoord, yCoord, testBoard);
                }
            }
            //makes sure first tile isn't a mine

            curTurn++;
            endOfGame = shouldReset(playBoard, testBoard, mineLoc, xCoord, yCoord);
            if(endOfGame == false && howManyMoves == 0){
                System.out.println("*__________________*");
                System.out.println("|Congrats, you win!|");
                System.out.println("*__________________*");
                endOfGame = true;
            }
        }
        //While the user has tiles left to remove and hasn't blown up a bomb, the game runs

        return;
    }

    public static void Difficulty(){
        int diff;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Difficulty wanted: ");
        System.out.println("0: Beginner");
        System.out.println("1: Intermediate");
        System.out.println("2: Advanced");
        //gives options to the user

        diff=scan.nextInt();

        if(diff == 0){
            sides = 8;
            mineNumber= 10;
        }

        if(diff == 1){
            sides = 13;
            mineNumber= 40;
        }

        if(diff == 2){
            sides = MaxSides;
            mineNumber= MaxMines;
        }
    }
    //sets the values for the size of the board based on the difficulty selected by the user

    public static void main(String[] args) {
	    Difficulty();
	    playGame();
    }
}
