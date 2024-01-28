package cpsc2150.extendedConnects;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Skyler Wolf -- sWolf-7
    Samuel Jordan -- srjorda
    Nicholas Jordan -- BallyhooAndBigTop
    Robby Infinger -- RobbyInfinger
 */

import cpsc2150.extendedConnectX.models.AbsGameBoard;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.util.Scanner;
import java.util.ArrayList;

public class GameScreen
{
    /**
     * Contains the functions that handle input and output with the user and controls the flow of the program
     */
    
    /**
     * prompts the player if they would like to play another round of ConnectX and returns their answer
     * @return true if the player wants to play another game, false OW
     * @pre none
     * @post playAgainPrompt = true IFF [the player answers that they want to play again], playAgainPrompt = false OW
     */
    private static boolean playAgainPrompt()
    {
        //Variable Declarations
        char ans;
        Scanner sc = new Scanner(System.in);

        //Do while sentinel loop to decide if a player would like to play again.
        do {
            System.out.println("Would you like to play again? Y/N");
            ans = sc.nextLine().charAt(0);

        } while( !(ans == 'Y' || ans == 'y' || ans == 'N' ||ans == 'n') );

        if (ans == 'Y' || ans == 'y'){
            sc.close();
            return true;
        }
        else {
            // If the player enters N or n or somehow the above logic is skipped, this will close the program safely.
            sc.close();
            java.lang.System.exit(0);
        }
        return false;
    }

    public static void main(String[] args)
    {
        // IMPORTANT CONSTANTS
        final int MAX_P = 10;
        final int MIN_P = 2;

        // Variable Declarations
        boolean repeat = false;
        IGameBoard myBoard = null;
        char player;
        int playerColInput;
        char charInput;
        Scanner sc = new Scanner(System.in);
        int numPlayers, numRows, numCols, numToWin;
        ArrayList<Character> tokens = new ArrayList<Character>();

        // The following sentinel structure will act as a menu for playing the game.
        do {
            // resets the game
            sc = new Scanner(System.in);
            repeat = false;
            tokens.clear();

            // get number of players
            do {
                System.out.println("How many players?");
                numPlayers = Integer.parseInt(sc.nextLine());
                if (numPlayers < MIN_P) {
                    System.out.println("Must be at least " + MIN_P + " players");
                    repeat = true;
                } else if (numPlayers > MAX_P) {
                    System.out.println("Must be " + MAX_P + " players or fewer");
                    repeat = true;
                } else {
                    repeat = false;
                }
            } while (repeat);

            // get tokens for each player
            for (int i = 0; i < numPlayers; i++) {
                do {
                    System.out.println("Enter the character to represent player " + (i + 1));
                    charInput = sc.nextLine().charAt(0);
                    charInput = Character.toUpperCase(charInput);

                    if (tokens.contains(charInput)) {
                        System.out.println(charInput + " is already taken as a player token!");
                    }
                } while (tokens.contains(charInput));
            
                tokens.add(charInput);
            }
            player = tokens.get(0);

            // get board size rows
            do {
                System.out.println("How many rows should be on the board?");
                numRows = Integer.parseInt(sc.nextLine());

                if (numRows < AbsGameBoard.MIN_R) {
                    System.out.println("Row count must be at least " + AbsGameBoard.MIN_R);
                } else if (numRows > AbsGameBoard.MAX_R){
                    System.out.println("Row count must be at most " + AbsGameBoard.MAX_R);
                }
            } while (numRows < AbsGameBoard.MIN_R || numRows > AbsGameBoard.MAX_R);

            // get board size columns
            do {
                System.out.println("How many columns should be on the board?");
                numCols = Integer.parseInt(sc.nextLine());

                if (numCols < AbsGameBoard.MIN_C) {
                    System.out.println("Column count must be at least " + AbsGameBoard.MIN_C);
                } else if (numCols > AbsGameBoard.MAX_C){
                    System.out.println("Column count must be at most " + AbsGameBoard.MAX_C);
                }
            } while (numCols < AbsGameBoard.MIN_C || numCols > AbsGameBoard.MAX_C);

            // get win condition
            do {
                System.out.println("How many in a row to win?");
                numToWin = Integer.parseInt(sc.nextLine());

                if (numToWin < AbsGameBoard.MIN_W) {
                    System.out.println("Win length must be at least " + AbsGameBoard.MIN_W);
                } else if (numToWin > AbsGameBoard.MAX_W) {
                    System.out.println("Win length must be at most " + AbsGameBoard.MAX_W);
                } else if (numToWin > numRows) {
                    System.out.println("Win length cannot exceed given number of rows");
                } else if (numToWin > numCols) {
                    System.out.println("Win length cannot exceed given number of columns");
                }
            } while (numToWin < AbsGameBoard.MIN_W || numToWin > AbsGameBoard.MAX_W || numToWin > numRows || numToWin > numCols);

            // get implementation to use
            do {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                charInput = sc.nextLine().charAt(0);

                if (charInput != 'm' && charInput != 'M' && charInput != 'f' && charInput != 'F') {
                    System.out.println("Please enter F or M");
                }
            } while (charInput != 'm' && charInput != 'M' && charInput != 'f' && charInput != 'F');

            // creates and displays board
            if (charInput == 'f' || charInput == 'F' ) {
                myBoard = new GameBoard(numRows,numCols,numToWin);
            } else if (charInput == 'm' || charInput == 'M') {
                myBoard = new GameBoardMem(numRows,numCols,numToWin);
            }
            System.out.println( myBoard.toString() );

            // each turn is one loop of this
            do {

                // loops for column input, goes until input is usable/valid
                do {
                    System.out.println( "Player " + player + ", what column do you want to place your marker in?" );
                    playerColInput = Integer.parseInt(sc.nextLine());

                    if (playerColInput < 0) {
                        System.out.println( "Column cannot be less than 0" );
                        repeat = true;
                    } else if (playerColInput > myBoard.getNumColumns()-1) {
                        System.out.println( "Column cannot be greater than " + Integer.toString(myBoard.getNumColumns()-1) );
                        repeat = true;
                    } else if (myBoard.checkIfFree(playerColInput) == false) {
                        System.out.println( "Column is full" );
                        repeat = true;
                    } else {
                        repeat = false;
                    }
                } while (repeat);

                // places player's marker
                myBoard.dropToken(player, playerColInput);
                System.out.println( myBoard.toString() );

                // end of round:
                // checks for win and tie, and if neither are true then rotates to next player token
                if (myBoard.checkForWin(playerColInput)) {
                    System.out.println( "Player " + player + " Won!" );
                    repeat = playAgainPrompt();
                } else if (myBoard.checkTie()) {
                    System.out.println( "It's a Tie!" );
                    repeat = playAgainPrompt();
                } else {
                    if(tokens.lastIndexOf(player) + 1 == numPlayers) {
                        player = tokens.get(0);
                    } else {
                        player = tokens.get(tokens.lastIndexOf(player) + 1);
                    }
                }
            // if repeat is false here, it means to not reset/repeat the game
            } while (repeat == false);

            sc.close();
            
        } while (repeat);
    }
}
