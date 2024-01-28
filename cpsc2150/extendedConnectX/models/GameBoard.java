package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Skyler Wolf -- sWolf-7
    Robby Infinger -- RobbyInfinger
    Samuel Jordan -- srjorda
    Nicholas Jordan -- BallyhooAndBigTop
 */

public class GameBoard extends AbsGameBoard
{
    /**
     * This class creates a "Game Board" that allows users to place characters into the board.
     * The board is implemented as a 2D array.
     * @invariant [GameBoard is of size ROW_COUNT by COL_COUNT] AND [there are no empty spaces
     * beneath occupied spaces] AND [GameBoard has a maximum size of MAX_R by MAX_C] AND
     * [GameBoard has a minimum size of MIN_R by MIN_C]
     * @correspondence board = boardArr
     */

    private char[][] boardArr;

//  Primary Methods
    /**
     * This method constructs the GameBoard object, mainly the boardArr field that will act as the actual game board
     *
     * @param rows The number of rows in the board
     * @param cols The number of columns in the board
     * @param win The number of consecutive tokens required to win
     *
     * @pre MIN_ROW_COUNT <= rows <= MAX_ROW_COUNT AND MIN_COL_COUNT <= cols <= MAX_COL_COUNT
     * AND MIN_WIN_LENGTH <= win <= MAX_WIN_LENGTH
     * @post ROW_COUNT = rows AND COL_COUNT = cols AND WIN_LENGTH = win
     * AND [will initialize a gameBoard of size GameBoard.ROW_COUNT by GameBoard.COL_COUNT with all spaces empty (" ")]
     */
    public GameBoard(int rows, int cols, int win) {
        ROW_COUNT = rows;
        COL_COUNT = cols;
        WIN_LENGTH = win;

        this.boardArr = new char[getNumRows()][getNumColumns()];

        // This initializes an array 
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                boardArr[i][j] = ' ';
            }
        }

    }

    public int getNumRows() { 
        return ROW_COUNT; 
    }

    public int getNumColumns() { 
        return COL_COUNT; 
    }

    public int getNumToWin() { 
        return WIN_LENGTH; 
    }

    public void dropToken(char p, int c)
    {
        boolean tokenPlaced = false;
        for (int i = 0; tokenPlaced == false; i++) {
            if (boardArr[i][c] == ' ') {
                boardArr[i][c] = p;
                tokenPlaced = true;
            }
        }
        
    }

    public char whatsAtPos(BoardPosition pos)
    {
        return this.boardArr[pos.getRow()][pos.getColumn()];
    }


}
