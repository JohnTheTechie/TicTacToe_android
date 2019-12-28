package johnfatso.tictactoe;

public class LocalOpponentPlayer extends Player implements Player.PlayerActionListener{

    LocalOpponentPlayer(PlayerEnd playerEndConfiguration, GamePiece gamePiece, GameOrder moveOrder) {
        super(playerEndConfiguration, gamePiece, moveOrder);
        registerUiListener(null);
    }

    /**
     * The implementation should enable
     * updating the board
     * updating the turn tracker
     * intimate the listeners the turn details
     *
     * @param cell  cellId which is filled
     * @param piece the piece filling the cell
     */
    @Override
    public void moveMade(CellID cell, GamePiece piece) {
        getTicTacBox().setCell(cell, piece);
        this.setTurn(true);
        CellID generatedCell = generateMove();
        Logging.v("TAG", this.getClass()+" | moveMade "+generatedCell.toString()+" , "+this.getGamePiece());
        getTicTacBox().setCell(generatedCell, this.getGamePiece());
        this.setTurn(false);
        opponentListener.moveMade(generateMove(), this.getGamePiece());
    }

    @Override
    public void ready() {
        if(getMoveOrder() == GameOrder.MOVE_FIRST){
            getTicTacBox().setCell(generateMove(), this.getGamePiece());
            opponentListener.moveMade(generateMove(), this.getGamePiece());
        }
    }

    /**
     * The function implements how judgement should be handled by the client
     * This function should be implemented by the client only
     * Should be called by host only
     * if the calculations by the client does not match with the host calculation
     * Exception should be raised by client
     *
     * @param winner the winner is host or client
     */
    @Override
    public void judgementAchieved(PlayerEnd winner) {
        if(getTicTacBox().getWinner() != winner)
            throw new IllegalStateException("judgementAchieved(): contradicting judgements achieved");
    }

    /**
     * This function should be called by host only.
     * Opponent player is the only intended recipient
     */
    @Override
    public void intimateRematchRequest() {
        opponentListener.rematchRequestAcceptedByOpponent(true);
    }

    /**
     * This function should only be called by the client
     * Intended recipient is the opponent
     *
     * @param isAccepted if the client accepted a new match requested
     */
    @Override
    public void rematchRequestAcceptedByOpponent(boolean isAccepted) {
        throw new IllegalStateException("rematchRequestAcceptedByOpponent(): this function should " +
                "not be called by a host");
    }

    /**
     * This function intimates the UI listener to start a new game
     * Can only be called only on UI listener
     */
    @Override
    public void destroySelf() {
        opponentListener = null;
    }

    private CellID generateMove(){
        for (CellID cell: CellID.values()){
            GamePiece piece = getTicTacBox().getPiece(cell);
            Logging.v("TAG", this.getClass()+" | generateMove() | "+cell+" holds "+piece);
            if(piece == null) return cell;
        }
        return null;
    }
}
