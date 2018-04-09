package com.example.memoredial3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.zip.DataFormatException;

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

    public void clickSubmit(View v) {
         try {
            ContactsHelper.addContact(nameText.getText().toString(), numText.getText().toString());
        } catch (DataFormatException dfe) {
            popUpMessage(dfe.getMessage());
            return;
        }

        popUpMessage("Contact added successfully.");

        nameText.setText("");
        numText.setText("");

    }
}
