package johnfatso.tictactoe;


/**
 * The class acting as the host of the game must implement this interface
 */
public interface HostPlayer {
    /**
     * the client shall register itself as the client to the host
     * the host shall return its reference
     * @param client reference of the client
     */
    HostPlayer registerClient(ClientPlayer client);
    /**
     *the client needs to acknowledge the ready flag called by the host
     */
    void clientReady();
    /**
     * the client should recieve and evaluate the results received from the host
     * acknowledge true if results match, ack false if result mismatch with local calculation
     * @param resultMatch true if the received the calculations matched
     */
    void ackResult(boolean resultMatch);

    /**
     *acknowledge the request for a new game if requested
     * @param isAccepted true if the request for new game accepted
     */
    void newGame(boolean isAccepted);
}
