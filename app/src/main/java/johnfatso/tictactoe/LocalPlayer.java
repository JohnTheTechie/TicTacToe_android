package johnfatso.tictactoe;

public class LocalPlayer implements HostPlayer, ClientPlayer, localEngine {

    //local player configuration is to be defined by the instantiator class during creation
    private PlayerEnd localPlayerConfig;
    private GamePiece avatar;
    private TicTacBox ticTacBox;

    //client variables
    private ClientPlayer client;
    private boolean hostReady;

    //host variables
    private HostPlayer host;
    private boolean clientReady;

    public LocalPlayer(PlayerEnd localPlayerConfig) {
        this.localPlayerConfig = localPlayerConfig;
        ticTacBox = new TicTacBox(GamePiece.PIECE_CROSS);
        avatar = GamePiece.PIECE_CROSS;
        //TODO: implement functionality to implement randomly allocate the piece during the runtime
    }

    /**
     * function called when host is ready.
     * client callsback via HostPlayerInterface
     */
    @Override
    public void hostReady() {
        this.hostReady = true;
        host.clientReady();
    }

    /**
     * host intimates the client, the piece allocated to the client
     *
     * @param piece
     */
    @Override
    public void allocatedGamePiece(GamePiece piece) {

    }

    /**
     * host intimates the client who makes the first move
     *
     * @param order
     */
    @Override
    public void allocatedMoveOrderForClient(GameOrder order) {

    }

    /**
     * the host should inform the cell occupied in its turn
     *
     * @param cell
     */
    @Override
    public void intimateMove(CellID cell) {

    }

    /**
     * the host should query the client for its move
     *
     * @return
     */
    @Override
    public CellID requestMove() {
        return null;
    }

    /**
     * the host decides the conclusion of the game and announces the client's results
     *
     * @param hasClientWon
     */
    @Override
    public void intimateClientsResult(boolean hasClientWon) {

    }

    /**
     * the host tells the clients, the host players decision to continue or end the game
     * the client acknowledges it via HostPlayerInterface
     *
     * @param isThereANewGame
     */
    @Override
    public void intimateNextStep(boolean isThereANewGame) {

    }


    //host player configuration function definitions
    /**
     * the client shall register itself as the client to the host
     *
     * @param client reference of the client
     */
    @Override
    public HostPlayer registerClient(ClientPlayer client) {
        this.client = client;
        this.client.allocatedGamePiece(GamePiece.PIECE_CIRCLE);
        this.client.allocatedMoveOrderForClient(GameOrder.MOVE_SECOND);
        return this;
    }

    /**
     * the client needs to acknowledge the ready flag called by the host
     */
    @Override
    public void clientReady() {
        clientReady = true;
        this.client.hostReady();
    }

    /**
     * the client should recieve and evaluate the results received from the host
     * acknowledge true if results match, ack false if result mismatch with local calculation
     *
     * @param resultMatch true if the received the calculations matched
     */
    @Override
    public void ackResult(boolean resultMatch) {
        if (resultMatch) throw new IllegalStateException("victory condition calculation failed");
    }

    /**
     * acknowledge the request for a new game if requested
     *
     * @param isAccepted true if the request for new game accepted
     */
    @Override
    public void newGame(boolean isAccepted) {

    }

    @Override
    public void moveMade(CellID cell) {
        ticTacBox.setCell(cell, avatar);
        client.intimateMove(cell);
        ticTacBox.setCell(client.requestMove(), GamePiece.PIECE_CIRCLE);
    }

    interface OnJudgementListener{
        void onJudgement(boolean isVictory);
    }
}
