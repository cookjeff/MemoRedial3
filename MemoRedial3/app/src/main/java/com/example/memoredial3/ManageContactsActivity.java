package com.example.memoredial3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ManageContactsActivity extends AppCompatActivity {

    SharedPreferences sharedprefs;
    EditText nameText;
    EditText numText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contacts);
        setTitle("Add Contact");
        sharedprefs = getSharedPreferences("ContactPrefs", Context.MODE_PRIVATE);
        nameText = findViewById(R.id.edittextName);
        numText = findViewById(R.id.edittextNumber);
    }

    protected void popUpMessage(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK",null);
        dialog.show();
    }

    public void clickAdd(View v) {
        if (sharedprefs.contains("Contact1") && sharedprefs.contains("Contact2")) {
            popUpMessage("Contacts full!");
            return;
        }
        if (nameText.getText().toString().equals("")) {
            popUpMessage("Invalid name!");
            return;
        }
        if (numText.getText().toString().equals("") || numText.getText().toString().length()!=10) {

            popUpMessage("Invalid number; must be 10 digits (include area code).");
        }

        if (sharedprefs.contains("Contact1")) {

        } else {

        }

    }
}
