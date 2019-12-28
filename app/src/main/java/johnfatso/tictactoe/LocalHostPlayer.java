package johnfatso.tictactoe;

public class LocalHostPlayer extends Player implements LocalOpponentPlayer.PlayerActionListener{

    public LocalHostPlayer(PlayerEnd playerEndConfiguration, GamePiece gamePiece, GameOrder moveOrder, boolean isRemoteOpponent) {
        super(playerEndConfiguration, gamePiece, moveOrder);
        PlayerEnd opponentPlayerEndConfiguration =
                playerEndConfiguration==PlayerEnd.PLAYER_HOST?PlayerEnd.PLAYER_CLIENT:PlayerEnd.PLAYER_HOST;
        GamePiece opponentGamePiece =
                gamePiece == GamePiece.PIECE_CROSS ? GamePiece.PIECE_CIRCLE : GamePiece.PIECE_CROSS;
        GameOrder opponentMoveOrder =
                moveOrder == GameOrder.MOVE_FIRST ? GameOrder.MOVE_SECOND : GameOrder.MOVE_FIRST;
        if(!isRemoteOpponent){
            Player.PlayerActionListener opponentListener = new LocalOpponentPlayer(opponentPlayerEndConfiguration, opponentGamePiece, opponentMoveOrder);
            registerOpponentListner(opponentListener);
            opponentListener.registerOpponentListner(this);
        }
    }

    /**
     * The implementation should enable
     * updating the board
     * updating the turn tracker
     * intimate the listeners the turn details
     *
     * @param cell   cellId which is filled
     * @param piece  the piece filling the cell
     */
    @Override
    public void moveMade(CellID cell, GamePiece piece) {
        Logging.v("TAG", this.getClass()+" | moveMade "+cell.toString()+" , "+piece.toString());
        getTicTacBox().setCell(cell, piece);
        uiListener.moveMade(cell, piece);
        this.setTurn(true);
        checkJudgment();
    }

    @Override
    public void ready() {
    }

    /**
     * The function implements how judgement should be handled by the client
     * This function should be implemented by the client only
     * Should be called by host only
     *
     * @param winner the winner is host or client
     */
    @Override
    public void judgementAchieved(PlayerEnd winner) {
        throw new IllegalStateException("judgementAchieved(): this function should not be called by a client");
    }


    /**
     * This function should be called by host only.
     * Opponent player is the only intended recipient
     *
     */
    @Override
    public void intimateRematchRequest() {
        throw new IllegalStateException("intimateRematchRequest(): this function should not be called by a client");
    }

    /**
     * This function should only be called by the client
     * Intended recipient is the opponent
     */
    @Override
    public void rematchRequestAcceptedByOpponent(boolean isAccepted) {
        uiListener.rematchRequestResponseByOpponent(isAccepted);
        uiListener = null;
        opponentListener = null;
    }

    /**
     * This function intimates the UI listener to start a new game
     * Can only be called only on UI listener
     */
    @Override
    public void destroySelf() {
        throw new IllegalStateException("destroySelf(): function should not be called by a client");
    }
}
