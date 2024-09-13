package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        findViewById(R.id.button0).setOnClickListener(onNumberClick);
        findViewById(R.id.button1).setOnClickListener(onNumberClick);
        findViewById(R.id.button2).setOnClickListener(onNumberClick);
        findViewById(R.id.button3).setOnClickListener(onNumberClick);
        findViewById(R.id.button4).setOnClickListener(onNumberClick);
        findViewById(R.id.button5).setOnClickListener(onNumberClick);
        findViewById(R.id.button6).setOnClickListener(onNumberClick);
        findViewById(R.id.button7).setOnClickListener(onNumberClick);
        findViewById(R.id.button8).setOnClickListener(onNumberClick);
        findViewById(R.id.button9).setOnClickListener(onNumberClick);

        // Operator buttons
        findViewById(R.id.buttonPlus).setOnClickListener(onOperatorClick);
        findViewById(R.id.buttonMinus).setOnClickListener(onOperatorClick);
        findViewById(R.id.buttonMultiply).setOnClickListener(onOperatorClick);
        findViewById(R.id.buttonDivide).setOnClickListener(onOperatorClick);

        findViewById(R.id.buttonClear).setOnClickListener(v -> clear());
        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculate());
    }

    private final View.OnClickListener onNumberClick = v -> {
        Button button = (Button) v;
        currentInput += button.getText().toString();
        display.setText(currentInput);
    };

    private final View.OnClickListener onOperatorClick = v -> {
        Button button = (Button) v;
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = button.getText().toString();
            currentInput = "";
        }
    };

    @SuppressLint("SetTextI18n")
    private void calculate() {
        if (!currentInput.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = "";
            operator = "";
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstNumber = 0.0;
        display.setText("0");
    }
}