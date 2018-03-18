package com.example.memoredial3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static ContactsHelper contactsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsHelper = new ContactsHelper();
        contactsHelper.start();
    }

    public void clickDialContactViewingNumber(View v) {
        Intent intent = new Intent(this, ContactsActivity.class);
        intent.putExtra("MODE","DialContactViewingNumber");
        startActivity(intent);
    }

    public void clickDialContactFromMemory(View v) {
        Intent intent = new Intent(this, ContactsActivity.class);
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

}
