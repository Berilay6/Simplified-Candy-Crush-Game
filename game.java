//Beril Aydın

import java.util.Random;
import java.util.Scanner;
public class game{
    public static void main(String[]args){
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Which mode do you want to play?: ");
        int gameMode= scan.nextInt();

        if(gameMode==1){
            System.out.println("Welcome to the game! \nEnter \'s\'' to start the game or \'q\' to quit:");
            char ch = scan.next().charAt(0);

            if(ch=='s'){
                System.out.println("You have to collect 200 points in 15 moves! \nGood luck!");
                System.out.println("Enter the size of the matrix:");

                int x = scan.nextInt();
                int y = scan.nextInt();
                CandyCrush game = new CandyCrush(x, y);
                game.playMode1();
            }

            else{
                System.out.println("It was good to see you, Goodbye!");
            }
        }

        else{
            System.out.println("Welcome to the game! \nEnter ’s’ to start the game or ’q’ to quit:");
            char ch = scan.next().charAt(0);

            if(ch=='s'){
                System.out.println("You have to collect 100 points for each color in 20 moves! \nGood luck!");
                System.out.println("Enter the size of the matrix:");

                int x = scan.nextInt();
                int y = scan.nextInt();
                CandyCrush game = new CandyCrush(x, y);
                game.playMode2();
            }

            else{
                System.out.println("It was good to see you, Goodbye!");
            }
        }

        scan.close();

    }
}

class Colors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE
}

class CandyCrush {
    private static int ROWS;
    private static int COLS;
    private static final int NUM_CANDY_TYPES = 3;
    
    static String red = Colors.RED + 'R' + Colors.RESET;
    static String green = Colors.GREEN + 'G' + Colors.RESET;
    static String blue = Colors.BLUE + 'B' + Colors.RESET;

    private static final String[] CANDY_SYMBOLS = {red, green, blue};

    private static String[][] board;
    private static Random random;

    public CandyCrush(int row, int col) {   //constructor
        ROWS=row;
        COLS=col;
        board = new String[ROWS][COLS];
        random = new Random();

        initializeBoard();
    }

    private static void initializeBoard() {
        //filling the empty board
    	for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = RandomCandy();
            }
        }
    	//if there are matches initiallize the board until there are no matches
        while(checkForMatches()){
             for (int i = 0; i < ROWS; i++) {
                 for (int j = 0; j < COLS; j++) {
                     board[i][j] = RandomCandy();
                 }
             }
        }
    }

    private static String RandomCandy() {
        int index = random.nextInt(NUM_CANDY_TYPES);
        return CANDY_SYMBOLS[index];
    }

    private static void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
    }

    private static boolean checkForMatches() {
        boolean foundMatch = false;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String currentCandy = board[i][j];

                // Check horizontally
                int count = 1;
                while (j + count < COLS && board[i][j + count].equals(currentCandy)) {
                    count++;
                }
                if (count == 3) {
                    foundMatch = true;
                    break;
                }

                // Check vertically
                count = 1;
                while (i + count < ROWS && board[i + count][j].equals(currentCandy)) {
                    count++;
                }
                if (count == 3) {
                    foundMatch = true;
                }
            }
            if(foundMatch){
                break;
            }
        }

        return foundMatch;
    }

     public static String[][] updateBoard(){

        if(checkForMatches()){
        	boolean found=false;
        	
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length-2; j++) {
                    //horizontal
                    if(board[i][j]==board[i][j+1] && board[i][j+1]==board[i][j+2]){
                        for (int k = 0; k < 3; k++) {
                            board[i][j + k] = RandomCandy();
                        }
                        found=true;
                        break;
                    }
                }
                if(found) {
                	break;
                }
            }
            
            for(int i=0; i<board.length-2; i++){
                for(int j=0; j<board[i].length; j++){
                    //vertical
                    if(board[i][j]==board[i+1][j] && board[i+1][j]==board[i+2][j]){
                    	for (int k = 0; k < 3; k++) {
                            board[i + k][j] = RandomCandy();
                        }
                    	found=true;
                    	break;
                    }
                }
                if(found) {
                	break;
                }
            }
        }

        return board;
    }

    private static void swapCells(int x, int y, String direction){
        
        int row=x-1;    //x and y will not be written considering that the index starts from zero.
        int col=y-1;
        String temp="";

        switch(direction){
            case "up":
                if(x-1!=0){
                    temp = board[row][col];
                    board[row][col]=board[row-1][col];
                    board[row-1][col]=temp;
                    if(checkForMatches()){
                        System.out.println("Clearing Board:");
                        System.out.println();
                        printBoard();
                        System.out.println("------------------------");
                        System.out.println();
                    }

                    else{   //if the move doesn't lead to a match.
                        temp = board[row][col];
                        board[row][col]=board[row-1][col];
                        board[row-1][col]=temp;
                        System.out.println("Invalid move!");
                        printBoard();
                        System.out.println();
                    }
                }

                else{
                    System.out.println("Invalid move!");
                    printBoard();
                    System.out.println();
                }
                break;

            case "down":
                if(x-1!=board.length-1){
                    temp = board[row][col];
                    board[row][col]=board[row+1][col];
                    board[row+1][col]=temp;
                    if(checkForMatches()){
                        System.out.println("Clearing Board:");
                        System.out.println();
                        printBoard();
                        System.out.println("------------------------");
                        System.out.println();
                    }

                    else{
                        temp = board[row][col];
                        board[row][col]=board[row+1][col];
                        board[row+1][col]=temp;
                        System.out.println("Invalid move!");
                        printBoard();
                        System.out.println();
                    }
                }

                else{
                    System.out.println("Invalid move!");
                    printBoard();
                    System.out.println();
                }
                break;

            case "right":
                if(y-1!=board[0].length-1){
                    temp = board[row][col];
                    board[row][col]=board[row][col+1];
                    board[row][col+1]=temp;
                    if(checkForMatches()){
                        System.out.println("Clearing Board:");
                        System.out.println();
                        printBoard();
                        System.out.println("------------------------");
                        System.out.println();
                    }

                    else{
                        temp = board[row][col];
                        board[row][col]=board[row][col+1];
                        board[row][col+1]=temp;
                        System.out.println("Invalid move!");
                        printBoard();
                        System.out.println();
                     }

                }

                else{
                    System.out.println("Invalid move!");
                    printBoard();
                    System.out.println();
                }
                break;

            case "left":
                if(y-1!=0){
                    temp = board[row][col];
                    board[row][col]=board[row][col-1];
                    board[row][col-1]=temp;
                    if(checkForMatches()){
                        System.out.println("Clearing Board:");
                        System.out.println();
                        printBoard();
                        System.out.println("------------------------");
                        System.out.println();
                    }
                    else{
                        temp = board[row][col];
                        board[row][col]=board[row][col-1];
                        board[row][col-1]=temp;
                        System.out.println("Invalid move!");
                        printBoard();
                        System.out.println();
                    }
                }

                else{
                    System.out.println("Invalid move!");
                    printBoard();
                    System.out.println();
                }
                break;
        }

    }

    private static boolean isMovePossible(){

        boolean isPossible =false;

        for(int i=0; i<ROWS-1; i++){
            for(int j=0; j<COLS-1; j++){

                //check vertically
                if(board[i][j]==board[i+1][j]){
                    isPossible = true;
                }

                //check horizontally
                else if(board[i][j]==board[i][j+1]){
                    isPossible=true;
                }

            }
            if(isPossible){
                break;
            }
        }
        return isPossible;
    }

    private static String matchedCandyType(){
        String matchedCandy="";
        boolean foundMatch=false;

    	for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String currentCandy = board[i][j];

                // Check horizontally
                int count = 1;
                while (j + count < COLS && board[i][j + count].equals(currentCandy)) {
                    count++;
                }
                if (count == 3) {
                    matchedCandy=board[i][j];
                    foundMatch = true;
                    break;
                }

                // Check vertically
                count = 1;
                while (i + count < ROWS && board[i + count][j].equals(currentCandy)) {
                    count++;
                }
                if (count == 3) {
                    matchedCandy=board[i][j];
                    foundMatch = true;
                }
            }
            if(foundMatch){
                break;
            }
        }
        return matchedCandy;
    
    }

    private static boolean checkWinCondition(int score){
        boolean win = false;
        if(score>=200){
            win=true;
        }
        return win;
    }
    //method overloading
     private static boolean checkWinCondition(int scoreRed, int scoreGreen, int scoreBlue){
        boolean win = false;

            if(scoreRed>=100 && scoreBlue>=100 && scoreGreen>=100){
                win=true;
            }
        return win;
    }

    public static void playMode1(){
        Scanner scan = new Scanner(System.in);
        // initialize board until move is possible
         while(true){                       
            if(isMovePossible()){
                System.out.println("You have 15 moves to reach the goal!");
                break;
            }
            else
                initializeBoard(); 
            }
        
        int score=0;
        for(int i=1; i<=15; i++){
                printBoard();
                System.out.println("Enter the cell:");
                int a = scan.nextInt();
                int b = scan.nextInt();

                System.out.println("Enter the direction:");
                String direction = scan.next();

                swapCells(a, b, direction);

                int count=0;
                while(checkForMatches()){
                    updateBoard();
                    printBoard();
                    System.out.println("------------------------");
                    System.out.println();
                    count++;
                }

                score+=count*15;

                if(count!=0){
                    System.out.println("You have collected " + score + " points and you have " + (15-i) + " moves left!");
                }

                    
                while(!isMovePossible()){
                        System.out.println("There are no possible moves, shuffling the board.....");
                        initializeBoard();
                }
        }

        if(checkWinCondition(score)){
          System.out.println("************ Congrats, You Win! :) **************");  
        }

        else{
            System.out.println("************ You Lost, Try Again! :( *************");
        }

        scan.close();
    }

    public static void playMode2(){

        Scanner scan = new Scanner(System.in);
        // initialize board until move is possible
         while(true){                       
            if(isMovePossible()){
                System.out.println("You have 20 moves to reach the goal!");
                break;
            }
            else
                initializeBoard(); 
            }
        
        int scoreRed=0;
        int scoreGreen=0;
        int scoreBlue=0;

        for(int i=1; i<=20; i++){
                printBoard();
                System.out.println("Enter the cell:");
                int a = scan.nextInt();
                int b = scan.nextInt();

                System.out.println("Enter the direction:");
                String direction = scan.next();

                swapCells(a, b, direction);

                int count=0;
                while(checkForMatches()){

                    if(matchedCandyType().equals(red)){
                        scoreRed+=10;
                    }
                    else if(matchedCandyType().equals(green)){
                        scoreGreen+=10;
                    }
                    else{
                        scoreBlue+=10;
                    }

                    updateBoard();
                    printBoard();
                    System.out.println("------------------------");
                    System.out.println();
                    count++;
                }

                

                if(count!=0){
                    System.out.println("You have collected " + scoreRed +" points for red, " + scoreGreen+ " points for green, " + scoreBlue + " points for blue and you have " + (20-i) + " moves left!");
                }

                    
                while(!isMovePossible()){
                        System.out.println("There are no possible moves, shuffling the board.....");
                        initializeBoard();
                }
        }

        if(checkWinCondition(scoreRed, scoreGreen, scoreBlue)){
          System.out.println("************ Congrats, You Win! :) **************");  
        }

        else{
            System.out.println("************ You Lost, Try Again! :( *************");
        }

        scan.close();
    }
    
}

