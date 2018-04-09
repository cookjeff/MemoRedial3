package com.example.memoredial3;

/**
 * Created by jrcb7 on 3/18/2018.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.widget.Button;

import java.util.HashMap;
import java.util.Random;
import java.util.zip.DataFormatException;

/**
 * Created by jrcb7 on 2/6/2018.
 */

public class ContactsHelper {

    static HashMap<String, String> contacts;

    public static void start() {
        contacts = new HashMap<String,String>();
        contacts.put("Linda J", "8167341298");
        contacts.put("Waldon C", "8164535766");
        contacts.put("John F", "8168329151");
        contacts.put("Shell M","8168812704");
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

        contacts.put(name,number);

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

}
