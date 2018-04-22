package com.example.memoredial3;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity {

    static ContactsHelper contactsHelper;

    public static final boolean assertions = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestContacts();
        contactsHelper = new ContactsHelper();
        contactsHelper.start(getContentResolver());

        //Log.d("curr",String.valueOf(getNumContactsOnSystem()));
        //getRawContacts();
        //readContacts();


    }

    public void clickDialContactViewingNumber(View v) {
        Intent intent = new Intent(this, ScrollingContactsActivity.class);
        intent.putExtra("MODE","DialContactViewingNumber");
        startActivity(intent);
    }

    public void clickDialContactFromMemory(View v) {
        Intent intent = new Intent(this, ScrollingContactsActivity.class);
        intent.putExtra("MODE","DialContactFromMemory");
        startActivity(intent);
    }

    public void clickQuizOnAllContacts(View v) {
        Intent intent = new Intent(this, MultiQuizDialerActivity.class);
        intent.putExtra("MODE","QuizOnAllContacts");
        startActivity(intent);
    }

    public void clickPracticeOnAllContacts(View v) {
        Intent intent = new Intent(this, MultiQuizDialerActivity.class);
        intent.putExtra("MODE","PracticeOnAllContacts");
        startActivity(intent);
    }

    public void clickQuizByNumber(View v) {
        Intent intent = new Intent(this, QuizByNumberActivity.class);
        startActivity(intent);
    }

    public void clickAddContact(View v) {
        Intent intent = new Intent(this, ManageContactsActivity.class);
        startActivity(intent);
    }

    /*contacts.put("Linda J", "8167341298");
        contacts.put("Waldon C", "8164535766");
        contacts.put("John F", "8168329151");
        contacts.put("Shell M","8168812704");
        */



    private void requestContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS},1);
    }

    public void clickFillSampleContacts(View v) {
        try {
            ContactsHelper.fillSampleContacts();
        } catch (DataFormatException dfe) {
            popUpMessage(dfe.toString());
        }

    }



    protected void popUpMessage(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK",null);
        dialog.show();
    }

}
