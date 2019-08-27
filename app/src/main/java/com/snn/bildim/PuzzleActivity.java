package com.snn.bildim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class PuzzleActivity extends AppCompatActivity {

    private Card card;
    private Toast toast;
    private Intent output;
    private Button buttonCheck;
    private Button buttonShowAnswer;
    private EditText editTextAnswer;
    private TextView textViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            init(extras.getSerializable("Card"), extras.getInt("Position"));
        }
    }

    @SuppressLint("SetTextI18n")
    private void init(Serializable card, int position) {
        this.card = (Card) card;
        output = new Intent();
        output.putExtra("Position", position);

        buttonCheck = findViewById(R.id.buttonCheck);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        textViewAnswer = findViewById(R.id.textViewAnswer);

        buttonShowAnswer = findViewById(R.id.buttonShowAnswer);
        buttonShowAnswer.setVisibility(View.INVISIBLE);

        int value;
        if (this.card.getValue() == 2) {
            mSetVisible();
            textViewAnswer.setText("Cevap : "
                    + this.card.getAnswer().substring(0, 1).toUpperCase()
                    + this.card.getAnswer().substring(1));
            value = 2;
        } else if (this.card.getValue() == 3) {
            mSetVisible();
            textViewAnswer.setText("Cevap : "
                    + this.card.getAnswer().substring(0, 1).toUpperCase()
                    + this.card.getAnswer().substring(1));
            value = 3;
        } else {
            textViewAnswer.setVisibility(View.INVISIBLE);
            buttonCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(editTextAnswer.getText().toString());
                }
            });
            value = 1;
        }

        TextView textViewTitle = findViewById(R.id.textViewPuzzleTitle);
        TextView textViewQuestion = findViewById(R.id.textViewPuzzleQuestion);

        textViewTitle.setText(this.card.getTitle());
        textViewQuestion.setText(this.card.getQuestion());

        updateValue(value);
    }

    private void checkAnswer(String text) {
        if (text.equals(this.card.getAnswer().toLowerCase().trim())) {
            showToast("Evet, bildin!");
            updateValue(2);
            finish();
        } else {
            showToast("Hayır, bilemedin!");
            updateValue(1);

            if (this.buttonShowAnswer.getVisibility() == View.INVISIBLE) {
                this.buttonShowAnswer.setVisibility(View.VISIBLE);
                this.buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        mSetVisible();
                        updateValue(3);
                        textViewAnswer.setText("Cevap : "
                                + card.getAnswer().substring(0, 1).toUpperCase()
                                + card.getAnswer().substring(1));
                    }
                });
            }
        }
    }

    private void mSetVisible() {
        this.buttonCheck.setVisibility(View.INVISIBLE);
        this.textViewAnswer.setVisibility(View.VISIBLE);
        this.editTextAnswer.setVisibility(View.INVISIBLE);
        this.buttonShowAnswer.setVisibility(View.INVISIBLE);
    }

    private void updateValue(int value) {
        output.putExtra("Value", value);
        setResult(RESULT_OK, output);
    }

    private void showToast(String text) {
        if (this.toast != null) {
            this.toast.cancel();
        }
        this.toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        this.toast.show();
    }
}