package com.example.memoredial3;

/**
 * Created by jrcb7 on 3/18/2018.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;
import java.util.Random;
import java.util.zip.DataFormatException;

/**
 * Created by jrcb7 on 2/6/2018.
 */

public class ContactsHelper {

    static HashMap<String, String> contacts;

    static ContentResolver contentResolver;

    //private ContactsHelper() {};

    public static void start(ContentResolver outsideCR) {
        contentResolver = outsideCR;
        readContacts();
    }

    static String getNumber(String name) {
        return contacts.get(name);
    }

    static String getRandom() {
        Random random = new Random();
        int size = contacts.keySet().size();
        return (String)contacts.keySet().toArray()[random.nextInt(size)];
    }

    static int getNumContacts() {
        return contacts.size();
    }

    static void addContact(String name, String number) throws DataFormatException {
        if (number.length() != 10)
            throw new DataFormatException("Number is not 10 digits.");

        try {
            double dbl = Double.parseDouble(number);
        } catch (NumberFormatException nfe){
            throw new DataFormatException("Number is not numeric.");
        }

        if (name==null || name.equals(""))
            throw new DataFormatException("Name is not valid.");

        contacts.put(name,PhoneNumberHelper.siftNumber(number));
        addContactToSystem(name,number);

    }

    static String getNameAt(int i) {
        ArrayList<String> names = new ArrayList<String>();
        names.addAll(contacts.keySet());
        Collections.sort(names);

        return names.get(i);


    }

    static int getCount() {
        return contacts.keySet().size();
    }

    private static ArrayList<Integer> getRawContacts() {
        Cursor cursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[] {ContactsContract.RawContacts._ID}, null, null, null);
        cursor.moveToFirst();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        do {
            ids.add(cursor.getInt(0));
        } while (cursor.moveToNext());
        return ids;
    }

    public static int getNumContactsOnSystem() {
        Cursor cursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[] {ContactsContract.RawContacts._ID}, null, null, null);
        return cursor.getCount();
    }

    static void readContacts() {

        List<Integer> rawContactIDs = getRawContacts();
        contacts = new HashMap<String,String>();
        String currName, currNum;

        for (int rcid : rawContactIDs) {
            Cursor pc = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID + "=?",new String[]{String.valueOf(rcid)},null);
            pc.moveToFirst();
            currNum = pc.getString(pc.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.d("ctest",currNum);
            int regContactId = pc.getInt(pc.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            pc.close();


            Cursor nc = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,
                    ContactsContract.Contacts._ID + "=?", new String[]{String.valueOf(regContactId)}, null);
            nc.moveToFirst();
            Log.d("ctest",nc.getString(nc.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            currName = nc.getString(nc.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            nc.close();

            contacts.put(currName,PhoneNumberHelper.siftNumber(currNum));

        }
    }

    public static void fillSampleContacts() throws DataFormatException {
        if (getNumContactsOnSystem() < 4) {
            addContact("Shell M", "8165558812");
            addContact("Waldon C", "8165554535");
            addContact("John F", "8165558329");
            addContact("Linda J", "8165557341");
        }
    }

    static void addContactToSystem(String name, String number) {
        // Code adapted from https://stackoverflow.com/questions/12576185/cannot-insert-android-contacts-programmatically-into-android-device

        ArrayList<ContentProviderOperation> op_list = new ArrayList<ContentProviderOperation>();
        op_list.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                //.withValue(RawContacts.AGGREGATION_MODE, RawContacts.AGGREGATION_MODE_DEFAULT)
                .build());

        // first and last names
        op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,name)
                .build());


        op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                .build());

        try{
            ContentProviderResult[] results = contentResolver.applyBatch(ContactsContract.AUTHORITY, op_list);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
