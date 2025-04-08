package com.example.introduceyourselfapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    Button submitButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        submitButton = findViewById(R.id.submitButton);
        resultText = findViewById(R.id.resultText);

        submitButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (name.isEmpty()) {
                resultText.setText("Please introduce yourself");
            } else {
                resultText.setText("Hello " + name);
            }
        });
    }
}
