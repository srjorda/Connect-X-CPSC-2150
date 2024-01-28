package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Skyler Wolf -- sWolf-7
    Robby Infinger -- RobbyInfinger
    Samuel Jordan -- srjorda
    Nicholas Jordan -- BallyhooAndBigTop
 */

public class BoardPosition
{
    /**
     * This class is in charge of calculating the board position of characters placed into it and by who placed the character
     * @invariant Row >= 0 AND Row < GameBoard.ROW_COUNT AND Column >= 0 AND Column < GameBoard.COL_COUNT
     */

    private int Row;
    private int Column;

    /**
     * This method creates a parameterized constructor for BoardPosition
     * @param aRow - value to be set in Row field
     * @param aColumn - value to be set in Column field
     * 
     * @pre aRow >= 0 AND aRow < GameBoard.ROW_COUNT AND aColumn >= 0 AND aColumn < GameBoard.COL_COUNT
     * @post Row = aRow AND Column = aColumn
     */
    public BoardPosition(int aRow, int aColumn)
    {
        this.Row = aRow;
        this.Column = aColumn;
    }

    /**
     * This method returns the row index as an int
     * @return row of the board position as an int
     *
     * @pre none
     * @post getRow() = Row AND Row = #Row AND Column = #Column
     */
    public int getRow() { return this.Row; }

    /**
     * This method returns the column index as an int
     * @return the column of the board position as an int
     *
     * @pre none
     * @post getColumn() = Column AND Row = #Row AND Column = #Column
     */
    public int getColumn() { return this.Column; }

    /**
     * This method determines whether two BoardPosition objects describe the same position
     * @param obj the BoardPosition object to compare to the calling object
     * @return true if the BoardPosition objects describe the same position and false otherwise
     *
     * @pre none
     * @post [equals() = true IFF self.Row = obj.Row AND self.Column = obj.Column, false otherwise] AND Row = #Row
     *       AND Column = #Column
     */
    @Override
    public boolean equals(Object obj) { return (this.toString()).equals(obj.toString()); }

    /**
     * This method returns a string describing the BoardPosition as an ordered pair in the form "row,column"
     * @return a string describing the BoardPosition as an ordered pair in the form "row,column"
     *
     * @pre none
     * @post [toString() = the ordered pair describing the BoardPosition] AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString()
    {
        return (Integer.toString(this.Row) + ',' + Integer.toString(this.Column));
    }
}
