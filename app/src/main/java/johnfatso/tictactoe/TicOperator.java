package johnfatso.tictactoe;
import java.util.Random ;

public class TicOperator {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    static TicOperator game;

    class Player{
        PlayerType type;
        PlayerCard card;

        Player(PlayerType type){
            this.type = type;
        }
    }

    private TicOperator(PlayerType playAgainst) {
        player2 = new Player(playAgainst);
        player1 = new Player(PlayerType.HumanPlayer);
        currentPlayer = player1;
        board = new Board();
        resetCards();
        setTokens();
    }

    void resetCards(){
        board.reset();
    }

    static public TicOperator getInstance(PlayerType playAgainst){
        if(game == null){
            game = new TicOperator(playAgainst);
        }
        return game;
    }

    private void setTokens(){
        player1.card = PlayerCard.card_x;
        player2.card = PlayerCard.card_o;
    }

    PlayerCard[] getBoardStatus(){
        return board.getStatus();
    }

    void input(PlayerCard card, int pos){
        board.changeCard(pos, card);
    }

    PlayerCard getCurrentCard(){
        return currentPlayer.card;
    }

    PlayerType getCurrentPlayerType(){
        return currentPlayer.type;
    }

    void toggleCurrentPlayer(){
        currentPlayer = (currentPlayer==player1)?player2:player1;
    }

    void inputMove(int changeCell){
        board.changeCard(changeCell, currentPlayer.card);
    }

    int getNextCompMove(){
        Random rnd = new Random();
        while (true){
            int pos = rnd.nextInt(9);
            if(board.cardSet[pos].card == PlayerCard.card_empty) return pos;
        }
    }

    boolean checkWinningCondition(PlayerCard card, int pos){
        return board.checkWinningCondition(card, pos);
    }


}
