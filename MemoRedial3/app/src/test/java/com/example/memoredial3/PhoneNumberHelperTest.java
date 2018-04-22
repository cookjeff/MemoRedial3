package com.example.memoredial3;

import android.provider.ContactsContract;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jrcb7 on 4/17/2018.
 */
public class PhoneNumberHelperTest {
    @Test
    public void toSimpleString() throws Exception {

        PhoneNumberHelper helper = new PhoneNumberHelper();

        String[] targetFormats = {"","1","12","123","123-4","123-45","123-456","123-4567","(123) 456-78","(123) 456-789","(123) 456-7890"};

        assertEquals("empty string phone number formatting failed!",(targetFormats[0]),PhoneNumberHelper.toString(""));
        assertEquals("len 1 string phone number formatting failed!",(targetFormats[1]),PhoneNumberHelper.toString("1"));
        assertEquals("len 2 string phone number formatting failed!",(targetFormats[2]),PhoneNumberHelper.toString("12"));
        assertEquals("len 3 string phone number formatting failed!",(targetFormats[3]),PhoneNumberHelper.toString("123"));
        assertEquals("len 4 string phone number formatting failed!",(targetFormats[4]),PhoneNumberHelper.toString("1234"));
        assertEquals("len 5 string phone number formatting failed!",(targetFormats[5]),PhoneNumberHelper.toString("12345"));
        assertEquals("len 6 string phone number formatting failed!",(targetFormats[6]),PhoneNumberHelper.toString("123456"));
        assertEquals("len 7 string phone number formatting failed!",(targetFormats[7]),PhoneNumberHelper.toString("1234567"));
        assertEquals("len 8 string phone number formatting failed!",(targetFormats[8]),PhoneNumberHelper.toString("12345678"));
        assertEquals("len 9 string phone number formatting failed!",(targetFormats[9]),PhoneNumberHelper.toString("123456789"));
        assertEquals("len 10 string phone number formatting failed!",(targetFormats[10]),PhoneNumberHelper.toString("1234567890"));



    }

    @Test
    public void siftNumber() throws Exception {

        assert(PhoneNumberHelper.siftNumber("(913) 555-1234").equals("9135551234"));

    }


}