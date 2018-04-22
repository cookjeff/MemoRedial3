package com.example.memoredial3;

// Adapted from https://www.androidhive.info/2016/01/android-working-with-recycler-view/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ScrollingContactsActivity extends AppCompatActivity {
    //private List<String> contactList = new ArrayList<String>();
    private RecyclerView recyclerView;
    private ContactsAdapter cAdapter;
    String mode, name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_contacts);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        setTitle("Select Contact");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("MODE");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        cAdapter = new ContactsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(cAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            public void onClick(View view, int position) {
                name = ContactsHelper.getNameAt(position);
                number = ContactsHelper.getNumber(name);

                if (MainActivity.assertions) {
                    assert(!name.equals(""));
                    assert(!number.equals(""));
                }

                goToDialer();

            }

        }));

        updateState();
    }

    public void goToDialer() {
        Intent intent;// = new Intent(this, MainDialActivity.class);
        intent = new Intent(this,MainDialActivity.class);
        intent.putExtra("MODE",mode);
        intent.putExtra("NAME",name);
        intent.putExtra("NUMBER",number);
        startActivity(intent);
    }

    public void updateState() {
        cAdapter.notifyDataSetChanged();
    }


}
