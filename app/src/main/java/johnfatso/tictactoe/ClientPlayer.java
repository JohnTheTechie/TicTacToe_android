package johnfatso.tictactoe;

/**
 * classes working as client side of player, should implement this interface
 */
public interface ClientPlayer {

    /**
     * function called when host is ready.
     * client callsback via HostPlayerInterface
     */
    void hostReady();

    /**
     *host intimates the client, the piece allocated to the client
     * @param piece pice allocated to the client
     */
    void allocatedGamePiece(GamePiece piece);
    /**
     * host intimates the client who makes the first move
     *
     * @param order movement turn of the client
     */
    void allocatedMoveOrderForClient(GameOrder order);

    /**
     * the host should inform the cell occupied in its turn
     * @param cell cell which has been selected by the host
     */
    void intimateMove(CellID cell);

    /**
     * the host should query the client for its move
     * @return cell which has been filled by the client
     */
    CellID requestMove();

    /**
     *the host decides the conclusion of the game and announces the client's results
     * @param hasClientWon if the client has won the game
     */
    void intimateClientsResult(boolean hasClientWon);

    /**
     *the host tells the clients, the host players decision to continue or end the game
     * the client acknowledges it via HostPlayerInterface
     * @param isThereANewGame if client wants a new game
     */
    void intimateNextStep(boolean isThereANewGame);
}
