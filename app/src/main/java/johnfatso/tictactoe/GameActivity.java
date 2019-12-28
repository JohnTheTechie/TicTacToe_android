package johnfatso.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements Player.UiListener {

    private final String LOG_TAG = "TAG";

    PlayerEnd userEnd;
    PlayerGUIInterface game;

    Button[][] tappers;
    ConstraintLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //removing the action bar from the game screen
        ActionBar bar = getSupportActionBar();
        bar.hide();

        Intent intent = getIntent();
        if(intent.getStringExtra("Players").equals("single")){
            userEnd = PlayerEnd.PLAYER_HOST;
            game = new LocalHostPlayer(PlayerEnd.PLAYER_HOST,
                    GamePiece.PIECE_CROSS,
                    GameOrder.MOVE_FIRST,
                    false);
            game.registerUiListener(this);
        }

        //creating and defining the buttons
        configureBoard();

    }

    void configureBoard(){

        tappers = new Button[3][3];
        board = findViewById(R.id.game_board_holder);

        //button references created
        tappers[0][0] = findViewById(R.id.game_tapper11);
        tappers[0][1] = findViewById(R.id.game_tapper12);
        tappers[0][2] = findViewById(R.id.game_tapper13);
        tappers[1][0] = findViewById(R.id.game_tapper21);
        tappers[1][1] = findViewById(R.id.game_tapper22);
        tappers[1][2] = findViewById(R.id.game_tapper23);
        tappers[2][0] = findViewById(R.id.game_tapper31);
        tappers[2][1] = findViewById(R.id.game_tapper32);
        tappers[2][2] = findViewById(R.id.game_tapper33);

        //OnClickListeneres for all nine buttons defined
        tappers[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_00);
            }
        });

        tappers[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_01);
            }
        });

        tappers[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_02);
            }
        });

        tappers[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_10);
            }
        });

        tappers[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_11);
            }
        });

        tappers[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_12);
            }
        });

        tappers[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_20);
            }
        });

        tappers[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_21);
            }
        });

        tappers[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button) view).setText("x");
                ((Button) view).setClickable(false);
                game.onUserSelectedCell(CellID.CELL_ID_22);
            }
        });

    }

    /**
     * The implementation should enable
     * updating the board
     * updating the turn tracker
     * intimate the listeners the turn details
     *
     * @param cell  cellId which is filled
     * @param piece the piece filling the cell
     */
    @Override
    public void moveMade(CellID cell, GamePiece piece) {
        tappers[cell.x][cell.y].setText("O");
        tappers[cell.x][cell.y].setClickable(false);
    }

    /**
     * The function implements how judgement should be handled by the client
     * This fnction should be implemented by the client only
     * Should be called by host only
     *
     * @param winner the winner is host or client
     */
    @Override
    public void judgementAchieved(PlayerEnd winner) {
        boolean isUserWinner = userEnd==winner;
        if(isUserWinner)
        Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "You lose!", Toast.LENGTH_SHORT).show();
    }

    /**
     * to be called when turn token is received by the player
     * this message is meant for the listeners other than the opponent
     * for the GUI to enable user operation
     * for the remote comm object for transmission for transmitting to remote device
     *
     * @param isTurn true is the caller has turn
     */
    @Override
    public void onTurnTokenUpdated(boolean isTurn) {

    }

    /**
     * This function should only be called on the UI
     *
     * @param isAccepted
     */
    @Override
    public void rematchRequestResponseByOpponent(boolean isAccepted) {

    }
}
