package johnfatso.tictactoe;

public class LocalClient implements ClientPlayer {

    final PlayerEnd playerConfiguration = PlayerEnd.PLAYER_CLIENT;
    private GamePiece avatar;
    private GameOrder order;
    private TicTacBox ticTacBox;
    private HostPlayer hostPlayer;

    public LocalClient(HostPlayer hostPlayer) {
        this.hostPlayer = hostPlayer;
        this.hostPlayer.registerClient(this);
    }

    /**
     * function called when host is ready.
     * client callsback via HostPlayerInterface
     */
    @Override
    public void hostReady() {

    }

    /**
     * host intimates the client, the piece allocated to the client
     *
     * @param piece pice allocated to the client
     */
    @Override
    public void allocatedGamePiece(GamePiece piece) {
        this.avatar = piece;
    }

    /**
     * host intimates the client who makes the first move
     *
     * @param order movement turn of the client
     */
    @Override
    public void allocatedMoveOrderForClient(GameOrder order) {
        this.order = order;
    }

    /**
     * the host should inform the cell occupied in its turn
     *
     * @param cell cell which has been selected by the host
     */
    @Override
    public void intimateMove(CellID cell) {
        this.ticTacBox.setCell(cell, avatar);
    }

    /**
     * the host should query the client for its move
     *
     * @return cell which has been filled by the client
     */
    @Override
    public CellID requestMove() {
        return null;
    }

    /**
     * the host decides the conclusion of the game and announces the client's results
     *
     * @param hasClientWon if the client has won the game
     */
    @Override
    public void intimateClientsResult(boolean hasClientWon) {

    }

    /**
     * the host tells the clients, the host players decision to continue or end the game
     * the client acknowledges it via HostPlayerInterface
     *
     * @param isThereANewGame if client wants a new game
     */
    @Override
    public void intimateNextStep(boolean isThereANewGame) {

    }
}
