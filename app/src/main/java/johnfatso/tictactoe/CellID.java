package johnfatso.tictactoe;

public enum CellID {
    CELL_ID_00(0,0),
    CELL_ID_01(0,1),
    CELL_ID_02(0,2),
    CELL_ID_10(1,0),
    CELL_ID_11(1,1),
    CELL_ID_12(1,2),
    CELL_ID_20(2,0),
    CELL_ID_21(2,1),
    CELL_ID_22(2,2);

    int x;
    int y;

    CellID(int x, int y){
        this.x = x;
        this.y = y;
    }
}
