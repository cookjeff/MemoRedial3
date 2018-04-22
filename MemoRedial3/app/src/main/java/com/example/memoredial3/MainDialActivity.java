package com.example.memoredial3;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainDialActivity extends AppCompatActivity {

    final String DCVN = "DialContactViewingNumber";
    final String DCFM = "DialContactFromMemory";
    PhoneNumberHelper phoneNum;
    TextView numberText;
    String targetNumber;
    String mode;
    String name;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dial);
        phoneNum = new PhoneNumberHelper();
        numberText = findViewById(R.id.numberText);
        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("MODE");
        targetNumber = bundle.getString("NUMBER");
        name = bundle.getString("NAME");

        if (mode.equals(DCVN)) {
            setTitle(name + " - " + PhoneNumberHelper.toString(targetNumber));
        } else if (mode.equals(DCFM)) {
            setTitle(name);
        }
    }

    protected void popUpMessage(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK",null);
        dialog.show();
    }

    private void updateNumber() {
        numberText.setText(phoneNum.toString());
        if (mode==DCVN) {
            // Consider adding automatic checking
        }
    }

    public void clickCall(View v) {
        if (phoneNum.Length()==10) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.fromParts("tel", phoneNum.toSimpleString(), null));
            startActivity(callIntent);
        } else {
            popUpMessage("Phone number too short!");
        }

    }

    public void clickAction(View v) {
        //if (mode.equals(DCVN)) {
        if (phoneNum.Length()==10) {
            if (phoneNum.matches(targetNumber)) {
                popUpMessage("Correct!");
            } else {
                popUpMessage("Incorrect!");
            }
        } else {
            popUpMessage("Incomplete number--try again!");
        }
        //}
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
