package johnfatso.tictactoe;

/**
 * Interface to be implemted by he local host or local client implementations
 */

public interface PlayerGUIInterface {
    /**
     * The Gui would call this function to intimate the user selected move
     *
     * @param cellID the selected cellId
     */
    void onUserSelectedCell(CellID cellID);

    /**
     * communicate if a rematch has been requested
     *
     */
    void onRematchRequestedByEndUser();

    void registerUiListener(Player.UiListener listener);
}
