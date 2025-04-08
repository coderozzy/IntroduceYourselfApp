package com.example.guessinggameapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText rangeInput, userGuess;
    Button drawButton, checkButton;
    TextView gameResult;
    int randomNumber = -1;
    int attempts = 0;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rangeInput = findViewById(R.id.rangeInput);
        userGuess = findViewById(R.id.userGuess);
        drawButton = findViewById(R.id.drawButton);
        checkButton = findViewById(R.id.checkButton);
        gameResult = findViewById(R.id.gameResult);

        drawButton.setOnClickListener(v -> {
            String rangeStr = rangeInput.getText().toString().trim();
            if (!rangeStr.isEmpty()) {
                try {
                    int range = Integer.parseInt(rangeStr);
                    if (range <= 0) {
                        gameResult.setText("Enter a number greater than 0.");
                        return;
                    }
                    Random rn = new Random();
                    randomNumber = rn.nextInt(range + 1); // 0 to range (inclusive)
                    attempts = 0;
                    gameOver = false;
                    gameResult.setText("Number drawn! Start guessing.");
                } catch (NumberFormatException e) {
                    gameResult.setText("Invalid number.");
                }
            } else {
                gameResult.setText("Please enter a number range first.");
            }
        });

        checkButton.setOnClickListener(v -> {
            if (randomNumber == -1) {
                gameResult.setText("Please draw a number first.");
                return;
            }

            if (gameOver) {
                gameResult.setText("Game over. Draw again to restart.");
                return;
            }

            String guessStr = userGuess.getText().toString().trim();
            if (!guessStr.isEmpty()) {
                try {
                    int guess = Integer.parseInt(guessStr);
                    attempts++;

                    if (guess < randomNumber) {
                        gameResult.setText("Too small! Try again.");
                    } else if (guess > randomNumber) {
                        gameResult.setText("Too large! Try again.");
                    } else {
                        gameOver = true;
                        gameResult.setText("Correct! You guessed it in " + attempts + " attempts.");
                    }
                } catch (NumberFormatException e) {
                    gameResult.setText("Enter a valid number.");
                }
            } else {
                gameResult.setText("Please enter your guess.");
            }
        });
    }
}
