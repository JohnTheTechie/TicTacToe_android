package johnfatso.tictactoe;

public enum PlayerCard {
    card_x("card_X"),
    card_o("card_O"),
    card_empty("card_EMPTY");

    final String descriptor;

    PlayerCard(final String string){
        this.descriptor = string;
    }
}
