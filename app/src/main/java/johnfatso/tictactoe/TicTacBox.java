package johnfatso.tictactoe;

/**
 * This class will maintain the status of the board
 * It contains the reference to the player who instantiated it, and to the board
 *
 * The caller can intialize, set the cell, get the cell occupant and calculate victory judgement
 *
 */
public class TicTacBox {

    //the piece allocated to the host
    private GamePiece playersPiece;
    //status of the game board
    private GamePiece[][] tappers;

    public TicTacBox(GamePiece playersPiece) {
        this.playersPiece = playersPiece;

        //initialize the board
        tappers = new GamePiece[3][3];

        //set all the cells to null as the default value
        for (int columnId=0; columnId<3; columnId++){
            for (int rowId=0; rowId<3; rowId++){
                tappers[columnId][rowId] = null;
            }
        }
    }

    /**
     * sets the specified piece to the specified cell
     * @param cell cell to be filled
     * @param piece piece to occupy the cell
     */
    void setCell(CellID cell, GamePiece piece){
        tappers[cell.x][cell.y] = piece;
    }

    /**
     * This function returns the occupying piece
     * null in case no one is occupying
     * @param cell Cellid
     * @return occupying cell
     */
    GamePiece getcell(CellID cell){
        return tappers[cell.x][cell.y];
    }

    /**
     * this function will check the entire board if a match has been achieved
     * if achieved, it will return the PlayerEnd object indicating the winner.
     * else it return null, to indicate no winner
     *
     * @return PlayerEnd who won, (if no one, return null)
     */
    PlayerEnd getWinner(){
        //run iterator for all rows
        for (int columnId = 0; columnId < 3; columnId++){
            //check if each rows has a match
            if ( isMatch(tappers[columnId][0], tappers[columnId][1], tappers[columnId][1]) != null )
                return getPlayer(tappers[columnId][0]);
        }

        //run iterator for all columns
        for (int rowId = 0; rowId < 3; rowId++){
            //check if each column has a match
            if ( isMatch(tappers[0][rowId], tappers[1][rowId], tappers[2][rowId]) != null )
                return getPlayer(tappers[0][rowId]);
        }

        //check the diagonals for a match
        if ( isMatch(tappers[0][0], tappers[1][1], tappers[2][2]) != null )
            return getPlayer(tappers[0][0]);

        if ( isMatch(tappers[0][3], tappers[1][1], tappers[2][0]) != null )
            return getPlayer(tappers[0][0]);

        //if none have a match return null
        return null;
    }

    private PlayerEnd getPlayer(GamePiece piece){
        if (playersPiece == piece) return PlayerEnd.PLAYER_HOST;
        else return PlayerEnd.PLAYER_CLIENT;
    }

     private GamePiece isMatch(GamePiece c1, GamePiece c2, GamePiece c3){
        //verify if all three cells have been filled already to check for a match
        if((c1 != null) && (c2 != null) && (c3 != null)){
            //if not null, check if all the cells have the same value
            if((c1.value & c2.value & c3.value) == GamePiece.PIECE_CIRCLE.value) return GamePiece.PIECE_CIRCLE;
            else if ((c1.value & c2.value & c3.value) == GamePiece.PIECE_CROSS.value) return GamePiece.PIECE_CROSS;
            //return null since no match occurred
            else return null;

         }
        //return null since not all the cells have been filled up
        else return null;
     }

}
