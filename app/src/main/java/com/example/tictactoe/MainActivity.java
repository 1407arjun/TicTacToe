package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //yellow = 0 = O, red = 1 = X, not played = 2
    int[] state = {2,2,2,2,2,2,2,2,2};
    String[] players = {"O", "X"};
    String winner = players[1];
    String loser = players[0];
    boolean gameActive = true;
    int[][] winCase = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void coins(View view){
        ImageView coin = (ImageView) view;
        TextView message = findViewById(R.id.display);

        int tapCoin = Integer.parseInt(coin.getTag().toString());

        if (state[tapCoin] == 2 && gameActive) {
            state[tapCoin] = activePlayer;
            if (activePlayer == 0) {
                if (players[0].equals("O")) {
                    coin.setImageResource(R.drawable.yellow);
                }else{
                    coin.setImageResource(R.drawable.red);
                }
                message.setText(players[1]+"'s turn");
                activePlayer = 1;

            } else {
                if (players[1].equals("O")) {
                    coin.setImageResource(R.drawable.yellow);
                }else{
                    coin.setImageResource(R.drawable.red);
                }
                message.setText(players[0]+"'s turn");
                activePlayer = 0;
            }
            coin.animate().alpha(1f).setDuration(500);

            for (int[] win : winCase) {
                if ((state[win[0]] == state[win[1]]) && (state[win[1]] == state[win[2]])
                        && (state[win[0]] != 2)) {
                    gameActive = false;
                    if (state[win[0]] == 0) {
                        message.setText(players[0]+" won");
                        winner = players[0];
                        loser = players[1];
                    } else {
                        message.setText(players[1]+" won");
                        winner = players[1];
                        loser = players[0];
                    }
                }
                else{
                    boolean gameOver = true;
                    for (int coinState: state){
                        if (coinState == 2) {
                            gameOver = false;
                            break;
                        }
                    }
                    if (gameOver) {
                        message.setText("It's a draw");
                        winner = players[1];
                        loser = players[0];
                    }
                }
            }
        }
    }

    public void reset(View view){
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(state, 2);
        players[0] = loser;
        players[1] = winner;
        GridLayout gridLayout = findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        TextView message = findViewById(R.id.display);
        message.setText(loser + " starts");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView message = findViewById(R.id.display);
        message.setText("O starts");
    }
}