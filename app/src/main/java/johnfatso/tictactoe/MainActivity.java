package johnfatso.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    PlayerType vsPlayer;
    Button playerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vsPlayer = PlayerType.ComputerPlayer;
        playerButton = (Button) findViewById(R.id.button_play_against);
        playerButton.setText(R.string.versus_comp_player);

    }

    void onPlayButtonSelected(View view){
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("OppositePlayer", PlayerType.HumanPlayer);
        startActivity(intent);
    }

    void onVsButtonSelected(View view){
        Button button = (Button) view;
        if(vsPlayer== PlayerType.ComputerPlayer){
            vsPlayer = PlayerType.HumanPlayer;
            ((Button) view).setText(R.string.versus_human_player);
        }
        else if(vsPlayer== PlayerType.HumanPlayer){
            vsPlayer = PlayerType.ComputerPlayer;
            ((Button) view).setText(R.string.versus_comp_player);
        }
    }
}
