package com.fadaly.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Boolean gameActive = true;

    Button playAgainButton;
    TextView winnerDisplayTextView;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        String winner = "";

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);


            for (int[] winngPostion : winningPositions) {
                if (gameState[winngPostion[0]] == gameState[winngPostion[1]] && gameState[winngPostion[1]] == gameState[winngPostion[2]] && gameState[winngPostion[0]] != 2) {

                    //someone has won

                    if (activePlayer == 0) winner = "red";
                    else winner = "yellow";
                    gameActive = false;

                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                    playAgainButton = findViewById(R.id.playAgainButton);
                    winnerDisplayTextView = findViewById(R.id.winnerTextView);

                    winnerDisplayTextView.setText(winner + " has won!");
                    winnerDisplayTextView.setVisibility(View.VISIBLE);

                    playAgainButton.setVisibility(View.VISIBLE);

                    break;

                }
            }
        }
    }

    public void resetGame(View view) {
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) gameState[i] = 2;
        gameActive = true;

        playAgainButton = findViewById(R.id.playAgainButton);
        winnerDisplayTextView = findViewById(R.id.winnerTextView);

        winnerDisplayTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}