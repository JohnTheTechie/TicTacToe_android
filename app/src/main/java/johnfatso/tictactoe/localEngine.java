package johnfatso.tictactoe;

/**
 * This interface shall be used by the UI to interact with the game middleware
 */
public interface localEngine {
    void moveMade(CellID cell);
}
