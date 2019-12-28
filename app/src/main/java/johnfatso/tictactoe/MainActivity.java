package johnfatso.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private final int PLAYER_TYPE_SINGLE = 0x01;
    private final int PLAYER_TYPE_MULTI = 0x02;

    int playerType;

    Button main_button_play;
    Button main_button_playerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerType = PLAYER_TYPE_SINGLE;

        main_button_play = findViewById(R.id.main_play);
        main_button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(playerType == PLAYER_TYPE_SINGLE){
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("Players", "single");
                    startActivity(intent);
                }

                //TODO : implement play trigger
            }
        });

        main_button_playerType = findViewById(R.id.main_player_type);
        main_button_playerType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                switch (playerType){
                    case PLAYER_TYPE_SINGLE:
                        playerType = PLAYER_TYPE_MULTI;
                        button.setText(R.string.main_button_player_type_multi);
                        break;

                    case PLAYER_TYPE_MULTI:
                        playerType = PLAYER_TYPE_SINGLE;
                        button.setText(R.string.main_button_player_type_single);
                        break;
                }
            }
        });

    }
}
