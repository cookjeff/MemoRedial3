package com.example.memoredial3;

/**
 * Created by jrcb7 on 3/26/2018.
 * Adapted from https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    //private List<String> namesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.number);
        }
    }


    //public ContactsAdapter(List<String> namesList) this.namesList = namesList;}

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_recview_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = ContactsHelper.getNameAt(position);
        holder.name.setText(name);
        holder.number.setText(PhoneNumberHelper.toString(ContactsHelper.getNumber(name)));
    }

    public int getItemCount() {
        return ContactsHelper.getCount();
    }
}