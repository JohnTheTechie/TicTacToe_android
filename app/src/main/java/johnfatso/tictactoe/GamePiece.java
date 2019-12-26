package johnfatso.tictactoe;

public enum GamePiece {
    PIECE_CIRCLE(0x01),
    PIECE_CROSS(0x02);

    int value;

    GamePiece(int value){
        this.value = value;
    }
}
