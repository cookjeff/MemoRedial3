package com.example.memoredial3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MultiQuizDialerActivity extends AppCompatActivity {

    final String POAC = "PracticeOnAllContacts";
    final String QOAC = "QuizOnAllContacts";

    PhoneNumberHelper phoneNum;
    TextView numberText;
    String number;
    String name;
    String mode;
    String previousName;
    boolean autosubmit = false;
    Button autosubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_dial);
        phoneNum = new PhoneNumberHelper();

        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("MODE");

        numberText = findViewById(R.id.numberText);
        autosubmitButton = findViewById(R.id.autosubmitButton);
        //Bundle bundle = getIntent().getExtras();
        //targetNumber = bundle.getString("NUMBER");
        autosubmitButton.setText("Autosubmit\nOff");
        setupQuizRound();




    }

    protected void popUpMessage(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK",null);
        dialog.show();
    }

    public void clickToggleAutosubmit(View v) {
        if (autosubmit) {
            autosubmit = false;
            autosubmitButton.setText("Autosubmit\nOff");
        } else {
            autosubmit = true;
            autosubmitButton.setText("Autosubmit\nOn");
        }
    }

    public void setupQuizRound() {
        name = ContactsHelper.getRandom();

        number = ContactsHelper.getNumber(name);
        phoneNum.Reset();
        numberText.setText("");
        if (mode.equals(POAC)) {
            setTitle(name + " - " + number);
        } else {
            setTitle(name);
        }
    }

    private void updateNumber() {
        numberText.setText(phoneNum.toString());
        // Autosubmit functionality
        if (phoneNum.Length()==10 && autosubmit)
            clickAction(null);
    }

    public void clickAction(View v) {
        if (phoneNum.Length()==10) {
            if (phoneNum.matches(number)) {
                popUpMessage("Correct!");
                setupQuizRound();
            } else {
                popUpMessage("Incorrect!");
            }


        } else {
            popUpMessage("Incomplete number--try again!");
        }
    }

    public void clickBack(View v) {
        phoneNum.back();
        updateNumber();
    }

    public void click1(View v) {
        phoneNum.addNumber(1);
        updateNumber();
    }

    public void click2(View v) {
        phoneNum.addNumber(2);
        updateNumber();
    }

    public void click3(View v) {
        phoneNum.addNumber(3);
        updateNumber();
    }

    public void click4(View v) {
        phoneNum.addNumber(4);
        updateNumber();
    }

    public void click5(View v) {
        phoneNum.addNumber(5);
        updateNumber();
    }

    public void click6(View v) {
        phoneNum.addNumber(6);
        updateNumber();
    }

    public void click7(View v) {
        phoneNum.addNumber(7);
        updateNumber();
    }
    public void click8(View v) {
        phoneNum.addNumber(8);
        updateNumber();
    }

    public void click9(View v) {
        phoneNum.addNumber(9);
        updateNumber();
    }

    public void click0(View v) {
        phoneNum.addNumber(0);
        updateNumber();
    }


}
