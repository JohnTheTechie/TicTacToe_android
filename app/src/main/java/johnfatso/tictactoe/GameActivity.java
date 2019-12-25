package johnfatso.tictactoe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    Button[][] tappers;
    ConstraintLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar bar = getSupportActionBar();
        bar.hide();

        configureBoard();

    }

    void configureBoard(){

        int tapperSize;

        tappers = new Button[3][3];
        board = findViewById(R.id.game_board_holder);


        tapperSize = board.getWidth()-10;

        Toast.makeText(this, "tappersize : "+tapperSize, Toast.LENGTH_SHORT).show();

        tappers[0][0] = findViewById(R.id.game_tapper11);
        tappers[0][1] = findViewById(R.id.game_tapper12);
        tappers[0][2] = findViewById(R.id.game_tapper13);
        tappers[1][0] = findViewById(R.id.game_tapper21);
        tappers[1][1] = findViewById(R.id.game_tapper22);
        tappers[1][2] = findViewById(R.id.game_tapper23);
        tappers[2][0] = findViewById(R.id.game_tapper31);
        tappers[2][1] = findViewById(R.id.game_tapper32);
        tappers[2][2] = findViewById(R.id.game_tapper33);

        tappers[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tappers[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
