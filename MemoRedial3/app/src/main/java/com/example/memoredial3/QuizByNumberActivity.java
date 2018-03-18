package com.example.memoredial3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuizByNumberActivity extends AppCompatActivity {

    String name;
    String number;
    Button buttonChoice1, buttonChoice2, buttonChoice3;

    Button buttons[];
    int usedIndices[] = new int[2];
    int currIndex;

    TextView textNumber;

    String falseName1 = "", falseName2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_by_number);
        buttonChoice1 = findViewById(R.id.buttonChoice1);
        buttonChoice2 = findViewById(R.id.buttonChoice2);
        buttonChoice3 = findViewById(R.id.buttonChoice3);
        textNumber = findViewById(R.id.textNumber);

        setTitle("Quiz By Number");

        buttons = new Button[]{buttonChoice1,buttonChoice2,buttonChoice3};

        setupQuiz();
    }

    private void setupQuiz() {
        buttonChoice1.setText(" ");
        buttonChoice2.setText("  ");
        buttonChoice3.setText("   ");


        name = ContactsHelper.getRandom();
        number = ContactsHelper.getNumber(name);

        textNumber.setText(PhoneNumberHelper.toString(number));

        Random rand = new Random();

        if (ContactsHelper.getNumContacts() > 1) {
            falseName1 = name;
            while (falseName1.equals(name)) {
                falseName1 = ContactsHelper.getRandom();
            }
        }

        if (ContactsHelper.getNumContacts() > 2) {
            falseName2 = name;

            while (falseName2.equals(name) || falseName1.equals(falseName2)) {
                falseName2 = ContactsHelper.getRandom();
            }
        }

        currIndex = rand.nextInt(3);
        buttons[currIndex].setText(name);
        usedIndices[0] = currIndex;


        while (currIndex == usedIndices[0]) {
            currIndex = rand.nextInt(3);
        }
        buttons[currIndex].setText(falseName1);
        usedIndices[1] = currIndex;


        while (currIndex == usedIndices[0] || currIndex == usedIndices[1]) {
            currIndex = rand.nextInt(3);
        }
        buttons[currIndex].setText(falseName2);

    }

    protected void popUpMessage(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK",null);
        dialog.show();
    }

    public void clickOption1(View v) {
        checkAnswer(v);
    }

    public void clickOption2(View v) {
        checkAnswer(v);
    }

    public void clickOption3(View v) {
        checkAnswer(v);
    }

    private void checkAnswer(View v) {
        if (((Button)v).getText().equals(name)) {
            popUpMessage("Correct!");
            setupQuiz();
        } else {
            popUpMessage("Incorrect!");
        }
    }

}
