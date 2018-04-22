package com.example.memoredial3;

import android.provider.ContactsContract;

/**
 * Created by jrcb7 on 2/6/2018.
 */

public class PhoneNumberHelper {
    private static String num;
    private String number = "";

    public String addNumber(int num) {
        return addNumber(Integer.toString(num));
    }
    public String addNumber(String num) {
        if (number.length() < 10) {
            number += num;
        }
        return number.toString();
    }
    public String back() {
        if (number.length() > 0)
            number = number.substring(0,number.length()-1);
        return number;
    }
    public String clear() {
        number = "";
        return number;
    }

    public void setNumber(String num) {
        number = num;
    }

    public void Reset() {
        this.number = "";
    }

    public String toSimpleString() {
        return number;
    }

    public boolean matches(String number) {
        return number.equals(this.number);
    }

    public int Length() {
        return number.length();
    }

    public static String siftNumber(String num) {
        PhoneNumberHelper.num = num;
        return num.replace("(","").replace(" ","").replace(")","").replace("-","");
    }

    public String toString() {
        return PhoneNumberHelper.toString(number);
    }

    public static String toString(String number) {
        int len = number.length();
        String result = "";
        switch (len) {
            case 0:
            case 1:
            case 2:
            case 3:
                result = number;
                break;
            case 4:
                result = number.substring(0,3) + "-" + number.charAt(3);
                break;
            case 5:
                result = number.substring(0,3) + "-" + number.substring(3);
                break;
            case 6:
                result = number.substring(0,3) + "-" + number.substring(3);
                break;
            case 7:
                result = number.substring(0,3) + "-" + number.substring(3);
                break;
            case 8:
                result = "("+number.substring(0,3) + ") " + number.substring(3,6) + "-" +number.substring(6);
                break;
            case 9:
                result = "(" + number.substring(0,3)+") " + number.substring(3,6) + "-" + number.substring(6);
                break;
            case 10:
            default:
                result = "(" + number.substring(0,3) + ") " + number.substring(3,6) + "-" + number.substring(6);
                break;
        }
        return result;
    }

}

