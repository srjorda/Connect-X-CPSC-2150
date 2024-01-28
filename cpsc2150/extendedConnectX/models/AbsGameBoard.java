package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard
{
    /**
     * An abstract parent class for GameBoard that implements IGameBoard
     * Only contains the overridden toString function to convert the game board into a string
     * and fields used by all implementations.
     *
     * @correspondence
     *      ROW_COUNT = this.ROW_COUNT
     *      COL_COUNT = this.COL_COUNT
     *      WIN_LENGTH = this.WIN_LENGTH
     *      MAX_ROW_COUNT = MAX_R
     *      MAX_COL_COUNT = MAX_C
     *      MAX_WIN_LENGTH = MAX_W
     *      MIN_ROW_COUNT = MIN_R
     *      MIN_COL_COUNT = MIN_C
     *      MIN_WIN_LENGTH = MIN_W
     */

    public int ROW_COUNT = MIN_R;
    public int COL_COUNT = MIN_C;
    public int WIN_LENGTH = MIN_W;

    //  Constants
    public static final int MAX_R = 100;
    public static final int MAX_C = 100;
    public static final int MAX_W = 25;

    public static final int MIN_R = 3;
    public static final int MIN_C = 3;
    public static final int MIN_W = 3;

    /**
     * This method converts the game board into a string which is then returned
     * @return a representation of the game board as a string
     *
     * @pre none
     * @post [toString() = the game board represented as a string] AND self = #self
     */
    @Override
    public String toString()
    {
        String boardString = "";
        for (int i = getNumRows(); i >= 0; i--) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (i == getNumRows()) {
                    boardString += "|" + String.format("%2d", j);
                } else {
                    boardString += "|" + this.whatsAtPos(new BoardPosition(i, j)) + " ";
                }
            }
            boardString += "|\n";
        }
        return boardString;
    }
}
