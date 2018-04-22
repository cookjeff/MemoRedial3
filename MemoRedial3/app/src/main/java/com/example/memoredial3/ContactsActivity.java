package com.example.memoredial3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactsActivity extends AppCompatActivity {

    Button button1, button2, button3, button4;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("MODE");
        button1 = (Button)findViewById(R.id.contactButton1);
        button2 = (Button)findViewById(R.id.contactButton2);
        button3 = (Button)findViewById(R.id.contactButton3);
        button4 = (Button)findViewById(R.id.contactButton4);



    }

    public void goToDialer(String name, String number) {
        Intent intent = new Intent(this, MainDialActivity.class);
        intent.putExtra("MODE",mode);
        intent.putExtra("NAME",name);
        intent.putExtra("NUMBER",number);
        startActivity(intent);
    }

    public void contactButtonClick(View v) {
        String name = "", number;
        switch (v.getId()) {
            case R.id.contactButton1:
                name = button1.getText().toString();
                break;
            case R.id.contactButton2:
                name = button2.getText().toString();
                break;
            case R.id.contactButton3:
                name = button3.getText().toString();
                break;
            case R.id.contactButton4:
                name = button4.getText().toString();
                break;
        }
        number = ContactsHelper.getNumber(name);
        goToDialer(name, number);
    }
}
