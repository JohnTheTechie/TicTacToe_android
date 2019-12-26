package johnfatso.tictactoe;

public interface OpponentPlayer {

    //function called when host is ready.
    //client callsback via HostPlayerInterface
    void hostReady();
    //host intimates the client, the piece allocated to the client
    void allocatedGamePiece(GamePiece piece);
    //host intimates the client who makes the first move
    void allocatedMoveOrderForClient(GameOrder order);
    //the host should inform the cell occupied in its turn
    void intimateMove(CellID cell);
    //the host should query the client for its move
    CellID requestMove();
    //the host decides the conclusion of the game and announces the client's results
    void intimateClientsResult(boolean hasClientWon);
    //the host tells the clients, the host players decision to continue or end the game
    //the client acknowledges it via HostPlayerInterface
    void intimateNextStep(boolean isThereANewGame);
}
