package com.example.memoredial3;

/**
 * Created by jrcb7 on 3/18/2018.
 */

import java.util.Random;

import android.widget.Button;

import java.util.HashMap;
import java.util.Random;

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
}
