package cpsc2150.extendedConnectX.models;

public interface IGameBoard
{
    /**
     * @Initialization Ensures:
     *      An empty game board capable of size ROW_COUNT by COL_COUNT will be created
     * @Defines:
     *      board
     *      ROW_COUNT: Z
     *      COL_COUNT: Z
     *      WIN_LENGTH: Z
     *      MAX_ROW_COUNT: Z
     *      MAX_COL_COUNT: Z
     *      MAX_WIN_LENGTH: Z
     *      MIN_ROW_COUNT: Z
     *      MIN_COL_COUNT: Z
     *      MIN_WIN_LENGTH: Z
     * @Constraints:
     *     MIN_ROW_COUNT*MIN_COL_COUNT <= |board| <= MAX_ROW_COUNT*MAX_COL_COUNT AND
     *     MIN_WIN_LENGTH <= WIN_LENGTH <= MAX_WIN_LENGTH AND
     *     [there are no empty spaces below occupied spaces]
     *
     */

//  Primary Methods
    /**
     * returns the number of rows in the GameBoard
     * @return the number of rows in the GameBoard as an int
     * @pre none
     * @post [getNumRows() = number of rows in board] AND self = #self
     */
    public int getNumRows();

    /**
     * returns the number of columns in the GameBoard
     * @return the number of columns in the GameBoard as an int
     * @pre none
     * @post [getNumColumn() = number of columns in board] AND self = #self
     */
    public int getNumColumns();

    /**
     * returns the number of tokens in a row needed to win the game
     * @return the number of tokens in a row needed to win the game as an int
     * @pre none
     * @post [getNumToWin() = length of a winning sequence] AND self = #self
     */
    public int getNumToWin();

    /**
     * This Method places the character p in column c. The token will be placed in the lowest available row in column c.
     * @param p character to use as the token, as a char
     * @param c column to place the token in, as an int
     *
     * @pre c >= 0 AND c < GameBoard.COL_COUNT AND checkIfFree(c) = true AND p != ' '
     * @post [self = #self except the specified token will be added to the lowest available row in the specified column]
     */
    public void dropToken(char p, int c);
    // has to directly edit the board

    /**
     * This method returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.
     * @param pos object of row and column, as a BoardPosition type
     * @return Marker found at pos, blank if no marker found
     *
     * @pre none
     * @post [whatsAtPos() = the character at the specified position] AND self = #self
     */
    public char whatsAtPos(BoardPosition pos);
    // needs direct access to view the board

//  Secondary/Default Methods
    /**
     * This method returns true if the column can accept another token; false otherwise
     * @param c number that represents the index of the column, as an int
     * @return True if column is not full, false if column is full
     *
     * @pre c >= 0 AND c < GameBoard.COL_COUNT
     * @post [checkIfFree() = true if there is space in the top row of the column or false if there isn't]
     *       AND self = #self
     */
    public default boolean checkIfFree(int c)
    {
        // can be done using whatsAtPos for the top slot in a column

        if (this.whatsAtPos(new BoardPosition(getNumRows() - 1,c)) == ' ') {
            return true;
        }
        return false;
    }

    /**
     * This method checks for win of recent marker
     * @param c column the latest token was placed in, as an int
     * @return True if win is found, false if win is not found
     *
     * @pre c >= 0 AND c < GameBoard.COL_COUNT AND [c is the column the latest token was placed in]
     * @post [checkForWin() = true if placing the marker created a long enough horizontal, vertical, or
     *       diagonal sequence to win or false if not] AND self = #self
     */
    public default boolean checkForWin(int c)
    {
        // can be done using whatsAtPos to find the top (most recent) token in a column and the Win functions

        BoardPosition pos = null;
        char player = '\0';
        boolean foundToken = false;
        for (int r = this.getNumRows() - 1; foundToken == false; r--) {
            if (this.whatsAtPos(new BoardPosition(r,c)) != ' ') {
                pos = new BoardPosition(r,c);
                player = this.whatsAtPos(pos);
                foundToken = true;
            }
        }
        return (checkDiagWin(pos,player) || checkHorizWin(pos,player) || checkVertWin(pos,player));
    }

    /**
     * This method checks for gameBoard being full of player markers, results in game tie
     * @return True if a tie is found, false if game can continue
     *
     * @pre none
     * @post [checkTie() = true if the board is full and no one has won or false if there is still room to play]
     *       AND self = #self
     */
    public default boolean checkTie()
    {
        // can be done using checkForWin and checkIfFree (thus using whatsAtPos)
        // ex: if checkForWin is false and whatsAtPos across the top row returns no ' ', then there is a tie

        for (int c = 0; c < this.getNumColumns(); c++) {
            if (checkIfFree(c) == true || checkForWin(c) == true) { 
                return false; 
            }
        }
        return true;
    }

    /**
     * This method checks for win of recent marker horizontally
     * @param pos object of row and column, as a BoardPosition type
     * @param p character to use as the token, as a char
     * @return True if win found horizontally, false if no win found
     *
     * @pre p != ' '
     * @post [checkHorizWin() = true if a horizontal sequence of GameBoard.WIN_LENGTH positions with token p is found or
     *       false if not] AND self = #self
     */
    public default boolean checkHorizWin(BoardPosition pos, char p)
    {
        // can be done using isPlayerAtPos and thus whatsAtPos

        //Variable Declarations
        int markerStreakCur = 0;
        int markerStreakMax = 0;

        for (int c = 0; c < this.getNumColumns(); c++) {
            if (this.isPlayerAtPos(new BoardPosition(pos.getRow(), c), p)) {
                markerStreakCur += 1;
                markerStreakMax = Math.max(markerStreakMax, markerStreakCur);
            } else {
                markerStreakCur = 0; 
            }

            if (markerStreakMax >= this.getNumToWin()){
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks for win of recent marker vertically
     * @param pos object of row and column, as a BoardPosition type
     * @param p character to use as the token, as a char
     * @return True if win found vertically, false if no win found
     *
     * @pre p != ' '
     * @post [checkVertWin() = true if a vertical sequence of GameBoard.WIN_LENGTH positions with token p is found or
     *       false if not] AND self = #self
     */
    public default boolean checkVertWin(BoardPosition pos, char p)
    {
        // can be done using isPlayerAtPos and thus whatsAtPos

        //Variable Declarations
        int markerStreakCur = 0;
        int markerStreakMax = 0;

        for (int r = 0; r < this.getNumRows(); r++) {

            if (this.isPlayerAtPos(new BoardPosition(r, pos.getColumn()), p)) {
                markerStreakCur += 1;
                markerStreakMax = Math.max(markerStreakMax, markerStreakCur); // Compares each win streak.
            } else { 
                markerStreakCur = 0; 
            }

            // If streak is large enough to win return true.
            if (markerStreakMax >= this.getNumToWin()){
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks for win with a specified marker for both left and right diagonal
     * @param pos object of row and column, as a BoardPosition type
     * @param p character to use as the token, as a char
     * @return true if win found diagonally, false if no win found
     *
     * @pre p != ' '
     * @post [checkDiagWin() = true if a diagonal sequence of GameBoard.WIN_LENGTH positions with token p is found or
     *       false if not] AND self = #self
     */
    public default boolean checkDiagWin(BoardPosition pos, char p)
    {
        // can be done using isPlayerAtPos and thus whatsAtPos

        // forward diagonal, fd (/)
        // checks from bottom left of the diagonal, moving up and to the right
        
        // Variable Declarations
        int fdStartRow = pos.getRow() - Math.min(pos.getRow(), pos.getColumn());
        int fdStartCol = pos.getColumn() - Math.min(pos.getRow(), pos.getColumn());

        int markerStreakCur = 0;
        int markerStreakMax = 0;

        // The following code will iterate and locate wins along the diagonals of our board.
        for (int i = 0; (fdStartRow+i < this.getNumRows()) && (fdStartCol+i < this.getNumColumns()); i++) {

            if (this.isPlayerAtPos(new BoardPosition(fdStartRow + i, fdStartCol + i), p)) {
                markerStreakCur += 1;
                markerStreakMax = Math.max(markerStreakMax, markerStreakCur);
            } else { 
                markerStreakCur = 0; 
            }

            if (markerStreakMax >= this.getNumToWin()){
                return true;
            }
        }

        // back diagonal, bd (\)
        // checks from bottom right of the diagonal, moving up and to the left

        // Variable Declarations
        int bdStartRow = pos.getRow() - Math.min(pos.getRow(), this.getNumColumns() - 1 -pos.getColumn());
        int bdStartCol = pos.getColumn() + Math.min(pos.getRow(), this.getNumColumns() - 1 -pos.getColumn());

        markerStreakCur = 0;
        markerStreakMax = 0;

        for (int i = 0; (bdStartRow + i < this.getNumRows()) && (bdStartCol - i >= 0); i++) {
            if (this.isPlayerAtPos(new BoardPosition(bdStartRow + i, bdStartCol -i ), p)) {
                markerStreakCur += 1;
                markerStreakMax = Math.max(markerStreakMax, markerStreakCur);
            } else { 
                markerStreakCur = 0;
            }

            // Return true if the diagonal has a streak that meets the win requirements
            if (markerStreakMax >= this.getNumToWin()){
                return true;
            }
        }

        return false;
    }

    /**
     * This method locates current player token and returns true if the player is at pos; otherwise, it returns false.
     * @param pos object of row and column, as a BoardPosition type
     * @param player which person is using the token, as a char
     * @return [True if player marker is found at pos, false if no marker found, false if player marker not found]
     *
     * @pre player != ' '
     * @post [isPlayerAtPos() = true if player's token is found at position pos, and false otherwise] AND self = #self
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        // can be done using whatsAtPos and comparing to the player token
        return (this.whatsAtPos(pos) == player);
    }

}
