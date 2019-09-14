package johnfatso.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {



    ImageButton[] ticcers;
    PlayerCard currentCard;
    TicOperator operator;

    Button resetButton;
    ConstraintLayout disclaimerArea;
    ImageView winnerImage;
    TextView resultText;

    PlayerType oppositePlayer;

    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        initialize(savedInstanceState);
    }

    void initialize(Bundle savedInstanceState){
        ticcers = new ImageButton[9];
        ticcers[0] = (ImageButton) findViewById(R.id.card_1);
        ticcers[1] = (ImageButton) findViewById(R.id.card_2);
        ticcers[2] = (ImageButton) findViewById(R.id.card_3);
        ticcers[3] = (ImageButton) findViewById(R.id.card_4);
        ticcers[4] = (ImageButton) findViewById(R.id.card_5);
        ticcers[5] = (ImageButton) findViewById(R.id.card_6);
        ticcers[6] = (ImageButton) findViewById(R.id.card_7);
        ticcers[7] = (ImageButton) findViewById(R.id.card_8);
        ticcers[8] = (ImageButton) findViewById(R.id.card_9);

        operator = TicOperator.getInstance(PlayerType.HumanPlayer);

        currentCard = operator.getCurrentCard();

        resetButton = (Button) findViewById(R.id.button_reset);
        disclaimerArea = (ConstraintLayout) findViewById(R.id.disclaimerPane);
        winnerImage = (ImageView) findViewById(R.id.winnerImage);
        resultText = (TextView) findViewById(R.id.result);

        if(savedInstanceState!=null) {
            oppositePlayer = (PlayerType) savedInstanceState.get("OppositePlayer");
        }
    }

    void onCardSelected(View view){

        ImageButton card = (ImageButton) view;

        card.setImageResource(currentCard==PlayerCard.card_x?R.mipmap.x:R.mipmap.o);

        operator.input(currentCard, getCardIndex(card));

        if(operator.checkWinningCondition(currentCard,getCardIndex(card))){
            setVisibilityWinnerDisclaimer(View.VISIBLE);
        }
        else {
            operator.toggleCurrentPlayer();
            currentCard = operator.getCurrentCard();
            card.setEnabled(false);
        }

    }

    void onResetButtonSelected(View view){
        ticcers[0].setImageResource(R.mipmap.card_plain_game);
        ticcers[0].setEnabled(true);
        for(ImageButton button:ticcers){
            button.setImageResource(R.mipmap.card_plain_game);
            button.setEnabled(true);
        }
        operator.resetCards();
        setVisibilityWinnerDisclaimer(View.INVISIBLE);
    }

    int getCardIndex(ImageButton imageButton) {
        for(int i=0; i<9 ; i++){
            if (ticcers[i].equals(imageButton)) return i;
        }
        return -1;
    }

    void setVisibilityWinnerDisclaimer(int isVisible){
        disclaimerArea.setVisibility(isVisible);
        winnerImage.setVisibility(isVisible);
        resultText.setVisibility(isVisible);
        winnerImage.setImageResource(currentCard==PlayerCard.card_x?R.mipmap.x:R.mipmap.o);
        if(oppositePlayer == PlayerType.HumanPlayer){
            resultText.setText(R.string.winner_vs_Human);
        }
        else {
            if(operator.getCurrentPlayerType() == PlayerType.ComputerPlayer) resultText.setText(R.string.loser_vs_computer);
            else resultText.setText(R.string.winner_vs_computer);
        }
    }


}
