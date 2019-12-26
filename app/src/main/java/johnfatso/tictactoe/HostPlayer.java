package johnfatso.tictactoe;

public interface HostPlayer {
    //the client needs to acknowledge the ready flag called by the host
    void clientReady();
    //the client should recieve and evaluate the results received from the host
    //acknowledge true if results match, ack false if result mismatch with local calculation
    void ackResult(boolean resultMatch);
    //acknowledge the request for a new game if requested
    void newGame(boolean isAccepted);
}
