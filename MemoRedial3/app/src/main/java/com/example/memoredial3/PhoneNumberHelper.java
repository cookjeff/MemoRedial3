package com.example.memoredial3;

/**
 * Created by jrcb7 on 2/6/2018.
 */

public class PhoneNumberHelper {
    private String number = "";

    public String addNumber(int num) {
        return addNumber(Integer.toString(num));
    }
    public String addNumber(String num) {
        if (number.length() < 11) {
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

    public String toString() {
        return toString(number);
    }

    public static String toString(String number) {
        int len = number.length();
        String result = "";
        switch (len) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                result = number;
                break;
            case 5:
                result = number.charAt(0) + "-" + number.substring(1);
                break;
            case 6:
                result = number.substring(0,2) + "-" + number.substring(2);
                break;
            case 7:
                result = number.substring(0,3) + "-" + number.substring(3);
                break;
            case 8:
                result = "(  " + number.charAt(0) + ") " + number.substring(1,3) + "-" +number.substring(3);
                break;
            case 9:
                result = "( " + number.substring(0,2)+") " + number.substring(2,5) + "-" + number.substring(5);
                break;
            case 10:
            default:
                result = "(" + number.substring(0,3) + ") " + number.substring(3,6) + "-" + number.substring(6);
                break;
        }
        return result;
    }

}

