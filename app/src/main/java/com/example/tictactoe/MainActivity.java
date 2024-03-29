package com.example.tictactoe;

import static java.lang.Integer.*;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     private EditText player1, player2;
     private TextView playerOneScore, playerTwoScore, state;
     private Button[] buttons= new Button[9];
     private Button reset, playAgain;
     private int player1Score, player2Score;
     boolean player1Active;
     int [] gameState={2,2,2,2,2,2,2,2,2}; // tahtadaki yerler
     int[][]winPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
     int rounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state=findViewById(R.id.state);
        player1=findViewById(R.id.player1);
        player2=findViewById(R.id.player2);
        reset=findViewById(R.id.reset);
        playAgain=findViewById(R.id.playAgain);
        playerTwoScore=findViewById(R.id.playerTwoScore);
        playerOneScore=findViewById(R.id.playerOneScore);


        buttons[0]= findViewById(R.id.btn0);
        buttons[1]= findViewById(R.id.btn1);
        buttons[2]= findViewById(R.id.btn2);
        buttons[3]= findViewById(R.id.btn3);
        buttons[4]= findViewById(R.id.btn4);
        buttons[5]= findViewById(R.id.btn5);
        buttons[6]= findViewById(R.id.btn6);
        buttons[7]= findViewById(R.id.btn7);
        buttons[8]= findViewById(R.id.btn8);
        for(int i=0;i < buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }
        player1Score=0;
        player2Score=0;
        player1Active=true;
        rounds=0;

    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        } else if (checkWinner()) {
            return;
            
        }
        String buttonID= view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));
        if(player1Active){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#143CAF"));
            gameState[gameStatePointer]=0;
        }
        else {
            ((Button)view).setText("o");
            ((Button)view).setTextColor(Color.parseColor("#8F0F0F"));
            gameState[gameStatePointer]=1;

        }
        rounds++;
        if (checkWinner()){
            if(player1Active){
                player1Score++;
                updatePlayerScore();
                state.setText(player1.getText()+ " has won");

            }
            else{
                player2Score++;
                updatePlayerScore();
                state.setText( player2.getText()+" has won");
            }
        }
        else if (rounds==9){
            state.setText("No Winner");

        }
        else {
            player1Active= !player1Active;
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();
                player1Score=0;
                player2Score=0;
                updatePlayerScore();

            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();
            }
        });
    }

    private boolean checkWinner() {
        boolean winnerResults= false;
        for (int[]winPosition: winPosition){
            if (gameState[winPosition[0]]== gameState[winPosition[1]]&& gameState[winPosition[1]]== gameState[winPosition[2]]
                && gameState[winPosition[0]]!=2){
                winnerResults=true;
            }
        }
        return winnerResults;

    }

    private void playagain() {
        rounds=0;
        player1Active=true;
        for(int i=0; i< buttons.length; i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
        state.setText("status");

    }

    private void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(player1Score));
        playerTwoScore.setText(Integer.toString(player2Score));
    }
}