package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Skyler Wolf -- sWolf-7
    Robby Infinger -- RobbyInfinger
    Samuel Jordan -- srjorda
    Nicholas Jordan -- BallyhooAndBigTop
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoardMem extends AbsGameBoard {
    /**
     * This class creates a "Game Board" that allows users to place characters into the board.
     * The board is implemented as a hashmap, where the key is a player's token and the value is the list of spaces
     * their token has been placed in.
     * @invariant [board has a capacity of ROW_COUNT by COL_COUNT] AND [there are no empty
     * spaces beneath occupied spaces] AND [any spaces that don't have tokens don't exist in the hashmap] AND
     * [board has a maximum capacity of MAX_R by MAX_C] AND [board has a minimum capacity of MIN_R by MIN_C]
     * @correspondence board = boardMap
     */

    private Map<Character, List<BoardPosition>> boardMap;
    /**
     * This method constructs the GameBoard object using a hashmap for the gameboard.
     *
     * @param rows The number of rows in the board
     * @param cols The number of columns in the board
     * @param win The number of consecutive tokens required to win
     *
     * @pre MIN_ROW_COUNT <= rows <= MAX_ROW_COUNT AND MIN_COL_COUNT <= cols <= MAX_COL_COUNT
     * AND MIN_WIN_LENGTH <= win <= MAX_WIN_LENGTH
     * @post ROW_COUNT = rows AND COL_COUNT = cols AND WIN_LENGTH = win
     * AND [will initialize a hashmap that represents the board and starts out completely empty]
     */
    public GameBoardMem(int rows, int cols, int win) {

        this.boardMap = new HashMap<Character, List<BoardPosition>>();

        ROW_COUNT = rows;
        COL_COUNT = cols;
        WIN_LENGTH = win;
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

    public void dropToken(char p, int c) {
        boolean tokenPlaced = false;
        // for each row of this column starting from the lowest...
        for (int i = 0; tokenPlaced == false; i++) {
            // if there is no token at that position...
            if (whatsAtPos(new BoardPosition(i,c)) == ' ') {
                // create the key-value pair if it doesn't exist
                if (boardMap.containsKey(p) == false) {
                    boardMap.put(p, new ArrayList<BoardPosition>());
                }
                // then add the board position to that token's list and stop looking
                boardMap.get(p).add(new BoardPosition(i,c));
                tokenPlaced = true;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        // for each pair in the map...
        for (Map.Entry<Character, List<BoardPosition>> entry : boardMap.entrySet()) {
            // ...if the list contains the position...
            if ((entry.getValue()).contains(pos)) {
                // ...return the key Character of the pair (the token Character)
                return entry.getKey();
            }
        }
        // return ' ' if no match is found (a blank space)
        return ' ';
    }

    /**
     * This method returns whether the specified player is at the specified position
     * Overridden to use a faster, more efficient implementation than whatsAtPos()
     * @param pos object of row and column, as a BoardPosition type
     * @param player which person is using the token, as a char
     * @return [True IFF the board position is found in the list correlating to the player marker, false OW]
     *
     * @pre player != ' '
     * @post [isPlayerAtPos() = True IFF the board position is found in the list correlating to the player marker,
     *       false OW] AND self = #self
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        // if there is no mapping for the player token, then no tokens have been placed for it
        if(boardMap.get(player) == null) {
            return false;
        } else {
            return boardMap.get(player).contains(pos);
        }
    }
}
