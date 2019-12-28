package johnfatso.tictactoe;



/**
 * This class is the base class for all the player implementations
 * Player shall define the basic functions of a player base object
 *
 */
public class Player implements PlayerGUIInterface{
    //this variable stores whether the object is host or client
    private PlayerEnd playerEndConfiguration;
    //this variable stores the game piece used by the player
    private GamePiece gamePiece;
    //if the player makes the first move
    private GameOrder moveOrder;
    //To store the state of the board
    private TicTacBox ticTacBox;
    //for storing a reference to the opponent player
    UiListener uiListener;
    PlayerActionListener opponentListener;
    //tracker to check of the player has attained turn
    private boolean isTurn;

    Player(PlayerEnd playerEndConfiguration, GamePiece gamePiece, GameOrder moveOrder) {
        this.playerEndConfiguration = playerEndConfiguration;
        this.gamePiece = gamePiece;
        this.moveOrder = moveOrder;
        this.ticTacBox = new TicTacBox(this.gamePiece);
        Logging.v("TAG", this.getClass()+" | object created | "+gamePiece.value);
        setTurn(this.moveOrder == GameOrder.MOVE_FIRST);
    }

    void setTurn(boolean isTurn){
        this.isTurn = isTurn;
        Logging.v("TAG", this.getClass()+" | setTurn() | turn : "+isTurn);
        if(uiListener != null)
            uiListener.onTurnTokenUpdated(isTurn);
    }

    TicTacBox getTicTacBox() {
        return ticTacBox;
    }

    GameOrder getMoveOrder() {
        Logging.v("TAG", this.getClass()+"getMoveOrder() | "+moveOrder.toString());
        return moveOrder;
    }

    public GamePiece getGamePiece() {
        Logging.v("TAG", this.getClass()+" | getGamePiece() | "+gamePiece);
        return gamePiece;
    }

    //@Override
    //void registerUiListener(UiListener listener){
      //  uiListener = listener;
    //}


    @Override
    public void registerUiListener(UiListener listener) {
        Logging.v("TAG", this.getClass()+" | uiListener added");
        uiListener = listener;
    }

    public void registerOpponentListner(PlayerActionListener listener){
        Logging.v("TAG", this.getClass()+" | playerListener added");
        opponentListener = listener;
    }

    /**
     * This function should be used / overridden by a host alone
     * This function shall check for a judgement and intimate the opponent and the GUI
     */
    void checkJudgment(){
        if(playerEndConfiguration == PlayerEnd.PLAYER_HOST){
            Logging.v("TAG", this.getClass()+" | host requesting judgement");
            PlayerEnd winner = ticTacBox.getWinner();
            if(winner != null){
                Logging.v("TAG", this.getClass()+" | winner judged | "+winner.toString());
                uiListener.judgementAchieved(winner);
                opponentListener.judgementAchieved(winner);
            }
            else Logging.v("TAG", this.getClass()+"| no winner judged");
        }
    }

    /**
     * The Gui would call this function to intimate the user selected move
     *
     * @param cellID the selected cellId
     */
    @Override
    public void onUserSelectedCell(CellID cellID) {
        if(this.isTurn){
            Logging.v("TAG", this.getClass()+" | turn confirmed | user selected move | "+cellID.toString());
            ticTacBox.setCell(cellID, this.gamePiece);
            this.setTurn(false);
            checkJudgment();
            opponentListener.moveMade(cellID,gamePiece);
        }
        else {
            throw new IllegalStateException("onUserSelectedCell(): cannot be called when player do not posses the turn token");
        }
    }

    /**
     * communicate if a rematch has been requested
     *
     */
    @Override
    public void onRematchRequestedByEndUser() {
        opponentListener.intimateRematchRequest();
    }

    /**
     * The extensions of the player parent class should implement this interface
     * to enable communication between them
     */
    public interface PlayerActionListener{
        /**
         * The implementation should enable
         *  updating the board
         *  updating the turn tracker
         *  intimate the listeners the turn details
         *
         * @param cell cellId which is filled
         * @param piece the piece filling the cell
         *
         */
        void moveMade(CellID cell, GamePiece piece);

        void ready();

        /**
         * The function implements how judgement should be handled by the client
         * This function should be implemented by the client only
         * Should be called by host only
         * if the calculations by the client does not match with the host calculation
         * Exception should be raised by client
         *
         * @param winner the winner is host or client
         */
        void judgementAchieved(PlayerEnd winner);

        /**
         * This function should be called by host only.
         * Opponent player is the only intended recipient
         *
         */
        void intimateRematchRequest();

        /**
         * This function should only be called by the client
         * Intended recipient is the opponent
         *
         */
        void rematchRequestAcceptedByOpponent(boolean isAccepted);

        /**
         * This function intimates the UI listener to start a new game
         * Can only be called only on UI listener
         */
        void destroySelf();

        void registerOpponentListner(PlayerActionListener listener);
    }

    public interface UiListener{
        /**
         * The implementation should enable
         *  updating the board
         *  updating the turn tracker
         *  intimate the listeners the turn details
         *
         * @param cell cellId which is filled
         * @param piece the piece filling the cell
         *
         */
        void moveMade(CellID cell, GamePiece piece);

        /**
         * The function implements how judgement should be handled by the client
         * This fnction should be implemented by the client only
         * Should be called by host only
         *
         * @param winner the winner is host or client
         */
        void judgementAchieved(PlayerEnd winner);

        /**
         * to be called when turn token is received by the player
         * this message is meant for the listeners other than the opponent
         * for the GUI to enable user operation
         * for the remote comm object for transmission for transmitting to remote device
         *
         * @param isTurn true is the caller has turn
         */
        void onTurnTokenUpdated(boolean isTurn);

        /**
         * This function should only be called on the UI
         *
         */
        void rematchRequestResponseByOpponent(boolean isAccepted);

    }
}