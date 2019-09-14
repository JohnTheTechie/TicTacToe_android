package johnfatso.tictactoe;

import android.util.Log;

class Board {

    private static final String TAG = "Board";

    class Cell{
        int index;
        PlayerCard card;
        Cell north, northEast, east, southEast, south, southWest, west, northWest;

        Cell(int index, Cell north, Cell northEast, Cell east, Cell southEast, Cell south, Cell southWest, Cell west, Cell northWest) {
            this.index = index;
            this.north = north;
            this.northEast = northEast;
            this.east = east;
            this.southEast = southEast;
            this.south = south;
            this.southWest = southWest;
            this.west = west;
            this.northWest = northWest;
        }
    }

    Cell[] cardSet;

    Board(){
        cardSet = new Cell[9];
        cardSet[0] = new Cell(0, null, null, cardSet[1], cardSet[4], cardSet[3], null, null, null);
        cardSet[1] = new Cell(0, null, null, cardSet[2], cardSet[5], cardSet[4], cardSet[3], cardSet[0], null);
        cardSet[2] = new Cell(0, null, null, null, null, cardSet[5], cardSet[4], cardSet[1], null);
        cardSet[3] = new Cell(0, cardSet[0], cardSet[1], cardSet[4], cardSet[7], cardSet[6], null, null, null);
        cardSet[4] = new Cell(0, cardSet[1], cardSet[2], cardSet[5], cardSet[8], cardSet[7], cardSet[6], cardSet[3], cardSet[0]);
        cardSet[5] = new Cell(0, cardSet[2], null, null, null, cardSet[8], cardSet[7], cardSet[4], cardSet[1]);
        cardSet[6] = new Cell(0, cardSet[3], cardSet[4], cardSet[7], null, null, null, null, null);
        cardSet[7] = new Cell(0, cardSet[4], cardSet[5], cardSet[8], null, null, null, cardSet[6], cardSet[3]);
        cardSet[8] = new Cell(0, cardSet[5], null, null, null, null, null, cardSet[7], cardSet[4]);
        reset();
    }

    void reset(){
        for (Cell cell: cardSet){
            cell.card = PlayerCard.card_empty;
        }
    }

    void changeCard(int pos, PlayerCard card){
        cardSet[pos].card = card;
        Log.v(TAG, "cell "+pos+" has been updated with "+card.descriptor);
    }

    PlayerCard[] getStatus(){
        PlayerCard[] list = new PlayerCard[9];
        for (int i = 0; i < 9; i++){
            if(cardSet[i] != null) list[i] = cardSet[i].card;
            else list[i] = PlayerCard.card_empty;
        }
        return list;
    }

    boolean checkWinningCondition(PlayerCard card, int cellIndex){

        Log.v(TAG,"checking winning condition for "+card.descriptor+" for cell "+(cellIndex));
        switch (cellIndex){
            case 0:
                if(check_row_1(card) || check_column_1(card) || check_topleft_bottomright(card)) return true;
                break;

            case 1:
                if(check_row_1(card) || check_column_2(card) ) return true;
                break;

            case 2:
                if(check_row_1(card) || check_column_3(card) || check_bottomleft_topright(card)) return true;
                break;

            case 3:
                if(check_row_2(card) || check_column_1(card)) return true;
                break;

            case 4:
                if(check_bottomleft_topright(card) || check_topleft_bottomright(card) || check_row_2(card) || check_column_2(card)) return true;
                break;

            case 5:
                if(check_row_2(card) || check_column_3(card)) return true;
                break;

            case 6:
                if(check_row_3(card) || check_column_1(card) || check_bottomleft_topright(card)) return true;
                break;

            case 7:
                if(check_row_3(card) || check_column_2(card)) return true;
                break;

            case 8:
                if(check_row_3(card) || check_column_3(card) || check_topleft_bottomright(card)) return true;
                break;
        }
        Log.v(TAG,"checking winning condition for "+card.descriptor+" for cell "+(cellIndex)+" No Winning condition");
        return false;
    }

    private boolean check_row_1(PlayerCard card){
        return ((cardSet[0] != null && cardSet[1] != null && cardSet[2] != null) &&
                (cardSet[0].card == card && cardSet[1].card == card && cardSet[2].card == card));
    }

    private boolean check_row_2(PlayerCard card){
        return ((cardSet[3] != null && cardSet[4] != null && cardSet[5] != null) &&
                (cardSet[3].card == card && cardSet[4].card == card && cardSet[5].card == card));
    }

    private boolean check_row_3(PlayerCard card){
        return ((cardSet[6] != null && cardSet[7] != null && cardSet[8] != null) &&
                (cardSet[6].card == card && cardSet[7].card == card && cardSet[8].card == card));
    }

    private boolean check_column_1(PlayerCard card){
        return ((cardSet[0] != null && cardSet[3] != null && cardSet[6] != null) &&
                (cardSet[0].card == card && cardSet[3].card == card && cardSet[6].card == card));
    }

    private boolean check_column_2(PlayerCard card){
        return ((cardSet[1] != null && cardSet[4] != null && cardSet[7] != null) &&
                (cardSet[1].card == card && cardSet[4].card == card && cardSet[7].card == card));
    }

    private boolean check_column_3(PlayerCard card){
        return ((cardSet[2] != null && cardSet[5] != null && cardSet[8] != null) &&
                (cardSet[2].card == card && cardSet[5].card == card && cardSet[8].card == card));
    }

    private boolean check_topleft_bottomright(PlayerCard card){
        boolean isWinningConditionAchieved = ((cardSet[0] != null && cardSet[4] != null && cardSet[8] != null) &&
                (cardSet[0].card == card && cardSet[4].card == card && cardSet[8].card == card));
        if(isWinningConditionAchieved) Log.v(TAG,"checking winning condition for "+card.descriptor+" | diaginal1 completed");
        else Log.v(TAG,"checking winning condition for "+card.descriptor+" | diaginal1 not completed");
        return isWinningConditionAchieved;
    }

    private boolean check_bottomleft_topright(PlayerCard card){
        boolean isWinningConditionAchieved = ((cardSet[6] != null && cardSet[4] != null && cardSet[2] != null) &&
                (cardSet[6].card == card && cardSet[4].card == card && cardSet[2].card == card));
        if(isWinningConditionAchieved) Log.v(TAG,"checking winning condition for "+card.descriptor+" | diaginal2 not completed");
        else Log.v(TAG,"checking winning condition for "+card.descriptor+" | diaginal2 not completed |"+cardSet[6].card.descriptor+" "+cardSet[4].card.descriptor+" "+cardSet[2].card.descriptor);
        return isWinningConditionAchieved;
    }
}
