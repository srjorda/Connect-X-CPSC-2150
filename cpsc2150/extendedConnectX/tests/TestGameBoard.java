package cpsc2150.extendedConnectX.tests;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {

    /**
     * Factory method to create IGameBoards, here of dynamic type GameBoard
     * @param r number of rows for the board to have
     * @param c number of columns for the board to have
     * @param w number in a row required to win
     * @return [an IGameBoard of dynamic type GameBoard with r rows, c columns, and requiring w in a row to win]
     */
    private IGameBoard IGameBoardFactory(int r, int c, int w) { return new GameBoard(r, c, w); }

    /**
     * Creates and returns a 2d char array with dimensions r by c filled with only spaces
     * @param r number of rows in the matrix
     * @param c number of columns in the matrix
     * @return [a 2d char array of dimensions r by c filled with only spaces]
     */
    private char[][] MatrixFactory(int r, int c) {
        char[][] matrix = new char[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                matrix[i][j] = ' ';
            }
        }
        return matrix;
    }

    /**
     * returns a 2d array transformed into a string
     * @param board the 2d array to transform into a string
     * @return [the 2d array passed in as a string in the proper format]
     */
    private String MatrixToString(char[][] board) {
        String boardString = "";
        for (int i = board.length; i >= 0; i--) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == board.length) {
                    boardString += "|" + String.format("%2d", j);
                }
                else {
                    boardString += "|" + board[i][j] + " ";
                }
            }
            boardString += "|\n";
        }
        return boardString;
    }

// 3 constructor tests (may be unique for each implementation)
    @Test // passes if an empty (' ') board with a routine 4 rows 3 columns and win length of 4 is created
    public void testConstructor_empty4x5_winlength4() {
        IGameBoard gb = IGameBoardFactory(4, 5, 4);
        char[][] expected = MatrixFactory(4,5);
        assertEquals(gb.toString(), MatrixToString(expected));
        assertEquals(4,gb.getNumToWin());
    }
    @Test // passes if an empty (' ') board with a min 3 rows and columns and 3 win length is created
    public void testConstructor_minArguments() {
        IGameBoard gb = IGameBoardFactory(3, 3, 3);
        char[][] expected = MatrixFactory(3,3);
        assertEquals(gb.toString(), MatrixToString(expected));
        assertEquals(3,gb.getNumToWin());
    }
    @Test // passes if an empty (' ') board with a max 100 rows and columns and 25 win length is created
    public void testConstructor_maxArguments() {
        IGameBoard gb = IGameBoardFactory(100, 100, 25);
        char[][] expected = MatrixFactory(100,100);
        assertEquals(gb.toString(), MatrixToString(expected));
        assertEquals(25,gb.getNumToWin());
    }

// 5 whatsAtPos tests (may be unique for each implementation)
    @Test // passes if the marker at routine position 1,2 matches the expected 'X'
    public void testWhatsAtPos_routine_Row1Col2() {
        IGameBoard gb = IGameBoardFactory(5, 5, 4);

        gb.dropToken('O', 2);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertEquals(gb.whatsAtPos(new BoardPosition(1,2)), 'X');
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if the marker at min position 0,0 matches the expected 'X'
    public void testWhatsAtPos_bottomLeft_Row0Col0() {
        IGameBoard gb = IGameBoardFactory(5, 5, 4);

        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertEquals(gb.whatsAtPos(new BoardPosition(0,0)), 'X');
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if the marker at max position 4,4 matches the expected 'X'
    public void testWhatsAtPos_topRight_Row4Col4() {
        IGameBoard gb = IGameBoardFactory(5, 5, 4);

        gb.dropToken('O', 4);
        gb.dropToken('X', 4);
        gb.dropToken('O', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);

        String preAssert = gb.toString();
        assertEquals(gb.whatsAtPos(new BoardPosition(4,4)), 'X');
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if the marker at center position 2,2 matches the expected 'O'
    public void testWhatsAtPos_centerO_Row2Col2() {
        IGameBoard gb = IGameBoardFactory(5, 5, 4);

        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('O', 2);

        String preAssert = gb.toString();
        assertEquals(gb.whatsAtPos(new BoardPosition(2,2)), 'O');
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if the marker in an empty board matches the expected ' '
    public void testWhatsAtPos_emptyBoard_Row0Col0() {
        IGameBoard gb = IGameBoardFactory(5, 5, 4);

        String preAssert = gb.toString();
        assertEquals(gb.whatsAtPos(new BoardPosition(0,0)), ' ');
        assertEquals(preAssert, gb.toString());
    }

// 5 dropToken tests (may be unique for each implementation)
    @Test //Place token on empty board
    public void testDropToken_emptyBoard(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
    }
    @Test //Place token in non-empty column; new token doesn't fill the column
    public void testDropToken_notFullRow(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 0);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(1, 0), 'O'));
    }
    @Test //Place token in empty column on non-empty board
    public void testDropToken_newRow_true(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 1);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0, 1), 'O'));
    }
    @Test //Placed token fills its column
    public void testDropToken_FillColumn(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('O', 0);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(2, 0), 'O'));
    }
    @Test //Placed token fills the board
    public void testDropToken_FullBoard(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('O', 0);

        gb.dropToken('Y', 1);
        gb.dropToken('Z', 1);
        gb.dropToken('X', 1);

        gb.dropToken('X', 2);
        gb.dropToken('Y', 2);
        gb.dropToken('Z', 2);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(2,2), 'Z'));
    }

// 5 isPlayerAtPos tests
    @Test // Tests to make sure true is returned when the player is actually at the position
    public void testIsPlayerAtPos_true(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);
        
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Tests to make sure isPlayerAtPos can identify when the player is not at the specified position,
          // even when the token does exist elsewhere on the board and the current spot is occupied by a token
    public void testisPlayerAtPos_false_existsElsewhere(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);
        
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);

        String preAssert = gb.toString();
        assertFalse(gb.isPlayerAtPos(new BoardPosition(0, 2), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Tests to ensure the function returns false when the board is completely empty
    public void testIsPlayerAtPos_false_emptyBoard(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        String preAssert = gb.toString();
        assertFalse(gb.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Checks to ensure that the function returns false when the token doesn't exist on the board at all
    public void testIsPlayerAtPos_false_tokenNotOnBoard(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertFalse(gb.isPlayerAtPos(new BoardPosition(2, 2), 'Z'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Checks to ensure the function returns false when the checked position is empty even if the board as a whole
          // is not
    public void testIsPlayerAtPos_false_emptySpace(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertFalse(gb.isPlayerAtPos(new BoardPosition(1, 1), 'X'));
        assertEquals(preAssert, gb.toString());
    }

// 3 checkIfFree tests
    @Test //passes if an empty column is correctly identified as free
    public void testCheckIfFree_emptyColumn_True(){
        IGameBoard gb = IGameBoardFactory(3,5,3);

        String preAssert = gb.toString();
        assertTrue(gb.checkIfFree(4));
        assertEquals(preAssert, gb.toString());
    }
    @Test //passes if a partially (not completely) full column is correctly identified as free
    public void testCheckIfFree_partiallyFullColumn_True(){
        IGameBoard gb = IGameBoardFactory(3,5,3);

        gb.dropToken('X',4);
        gb.dropToken('O',4);

        String preAssert = gb.toString();
        assertTrue(gb.checkIfFree(4));
        assertEquals(preAssert, gb.toString());
    }
    @Test //passes if a completely full column is correctly identified as not free
    public void testCheckIfFree_completelyFullColumn_False(){
        IGameBoard gb = IGameBoardFactory(3,5,3);

        gb.dropToken('X',4);
        gb.dropToken('O',4);
        gb.dropToken('X',4);

        String preAssert = gb.toString();
        assertFalse(gb.checkIfFree(4));
        assertEquals(preAssert, gb.toString());
    }

// 4 checkHorizWin tests
    @Test // passes if completing a sequence in the top right results in a win
    public void testCheckHorizWin_lastMarkerTopRight_Row2Col4() {
        IGameBoard gb = IGameBoardFactory(3,5,3);

        gb.dropToken('X', 2);
        gb.dropToken('O', 3);
        gb.dropToken('O', 4);

        gb.dropToken('O', 2);
        gb.dropToken('O', 3);
        gb.dropToken('X', 4);

        gb.dropToken('X', 2);
        gb.dropToken('X', 3);
        gb.dropToken('X', 4);

        String preAssert = gb.toString();
        assertTrue(gb.checkHorizWin(new BoardPosition(2,4),'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if completing a sequence in the bottom left results in a win
    public void testCheckHorizWin_lastMarkerBottomLeft_Row0Col0() {
        IGameBoard gb = IGameBoardFactory(3,5,3);

        gb.dropToken('X', 2);
        gb.dropToken('X', 1);
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertTrue(gb.checkHorizWin(new BoardPosition(0,0),'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if completing a sequence in the center results in a win
    public void testCheckHorizWin_lastMarkerMiddle_Row1Col2() {
        IGameBoard gb = IGameBoardFactory(3,5,3);

        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 3);

        gb.dropToken('X', 1);
        gb.dropToken('X', 3);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertTrue(gb.checkHorizWin(new BoardPosition(1,2),'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if not having enough tokens in a sequence does not result in a win
    public void testCheckHorizWin_enoughMarkersNoWin_Row0Col3() {
        IGameBoard gb = IGameBoardFactory(3,5,3);

        gb.dropToken('X', 0);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('X', 3);

        String preAssert = gb.toString();
        assertFalse(gb.checkHorizWin(new BoardPosition(0,3),'X'));
        assertEquals(preAssert, gb.toString());
    }

// 4 checkVertWin tests
    @Test // passes if completing a stack in the bottom left results in a win
    public void testCheckVertWin_stackBottomLeft_Row2Col0() {
        IGameBoard gb = IGameBoardFactory(5,5,3);

        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertTrue(gb.checkVertWin(new BoardPosition(2,0),'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if completing a stack in the top right results in a win
    public void testCheckVertWin_stackTopRight_Row4Col4() {
        IGameBoard gb = IGameBoardFactory(5,5,3);

        gb.dropToken('O', 4);
        gb.dropToken('O', 4);
        gb.dropToken('X', 4);
        gb.dropToken('X', 4);
        gb.dropToken('X', 4);

        String preAssert = gb.toString();
        assertTrue(gb.checkVertWin(new BoardPosition(4,4),'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if completing a stack in a more central position results in a win
    public void testCheckVertWin_stackInMiddle_Row3Col2() {
        IGameBoard gb = IGameBoardFactory(5,5,3);

        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertTrue(gb.checkVertWin(new BoardPosition(3,2),'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if not having enough tokens in a sequence does not result in a win
    public void testCheckVertWin_enoughMarkersNoWin_Row4Col0() {
        IGameBoard gb = IGameBoardFactory(5,5,3);

        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertFalse(gb.checkVertWin(new BoardPosition(4,0),'X'));
        assertEquals(preAssert, gb.toString());
    }

// 7 checkDiagWin tests
    @Test  // Passes if a win is recognized on a southwest to northeast diagonal when the most southwest token
           // is placed last
    public void testCheckDiagWin_SWtoNE_SWlast(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertTrue(gb.checkDiagWin(new BoardPosition(0, 0), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Passes if a win is recognized on a southwest to northeast diagonal when a middle token is placed last
    public void testCheckDiagWin_SWtoNE_middleLast(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);

        String preAssert = gb.toString();
        assertTrue(gb.checkDiagWin(new BoardPosition(1, 1), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Passes if a win is recognized on a southwest to northeast diagonal when the most northeast token
          // is placed last
    public void testCheckDiagWin_SWtoNE_NElast(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 2);
        gb.dropToken('O', 2);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertTrue(gb.checkDiagWin(new BoardPosition(2, 2), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Passes if a win is recognized on a northwest to southeast diagonal when the most northwest token
          // is placed last
    public void testCheckDiagWin_NWtoSE_NWlast(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 2);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);

        String preAssert = gb.toString();
        assertTrue(gb.checkDiagWin(new BoardPosition(2, 0), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Passes if a win is recognized on a northwest to southeast diagonal when a middle token is placed last
    public void testCheckDiagWin_NWtoSE_middleLast(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('O', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 2);
        gb.dropToken('O', 1);
        gb.dropToken('X', 1);

        String preAssert = gb.toString();
        assertTrue(gb.checkDiagWin(new BoardPosition(1, 1), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Passes if a win is recognized on a northwest to southeast diagonal when the most southeast token
    // is placed last
    public void testCheckDiagWin_NWtoSE_SElast(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('O', 1);
        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('O', 0);
        gb.dropToken('X', 0);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertTrue(gb.checkDiagWin(new BoardPosition(0, 2), 'X'));
        assertEquals(preAssert, gb.toString());
    }
    @Test // Passes if the function returns false when the last token dropped doesn't cause a diagonal win
    public void testCheckDiagWin_noWin(){
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 2);
        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('X', 2);
        gb.dropToken('O', 1);
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertFalse(gb.checkDiagWin(new BoardPosition(2, 2), 'X'));
        assertEquals(preAssert, gb.toString());
    }

// 4 checkTie tests
    @Test // passes if game can continue after player 2 turn
    public void testCheckTie_player1_false() {
        IGameBoard gb = IGameBoardFactory(3, 3, 3);
        
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertFalse(gb.checkTie());
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if game is tied after player 1 turn
    public void testCheckTie_player1_true() {
        IGameBoard gb = IGameBoardFactory(3, 3, 3);

        gb.dropToken('X', 0);
        gb.dropToken('O', 2);
        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('X', 2);
        gb.dropToken('O', 1);
        gb.dropToken('X', 0);
        gb.dropToken('O', 1);
        gb.dropToken('X', 2);

        String preAssert = gb.toString();
        assertTrue(gb.checkTie());
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if game can continue after player 2 turn
    public void testCheckTie_player2_false() {
        IGameBoard gb = IGameBoardFactory(3, 3, 3);
        
        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('X', 2);
        gb.dropToken('O', 0);

        String preAssert = gb.toString();
        assertFalse(gb.checkTie());
        assertEquals(preAssert, gb.toString());
    }
    @Test // passes if game is tied after player 2 turn
    public void testCheckTie_player2_true() {
        IGameBoard gb = IGameBoardFactory(3, 4, 3);

        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('X', 3);
        gb.dropToken('O', 2);
        gb.dropToken('X', 1);
        gb.dropToken('O', 0);
        gb.dropToken('X', 3);
        gb.dropToken('O', 2);
        gb.dropToken('X', 0);
        gb.dropToken('O', 3);
        gb.dropToken('X', 2);
        gb.dropToken('O', 1);

        String preAssert = gb.toString();
        assertTrue(gb.checkTie());
        assertEquals(preAssert, gb.toString());
    }
}
