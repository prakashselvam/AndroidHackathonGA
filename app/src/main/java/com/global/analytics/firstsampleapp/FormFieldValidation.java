package com.global.analytics.firstsampleapp;

/**
 * Created by senthilraj on 17-10-2015.
 */
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormFieldValidation {
    public boolean isNumber(String value) {
        try
        {
            int d = Integer.parseInt(value);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public boolean isAmount(String value) {
        try
        {
            double d = Double.parseDouble(value);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public boolean isMoney(String value) {
        try {
            DecimalFormat decim = new DecimalFormat("#.##");
            Double price2 = Double.parseDouble(decim.format(value));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean isAlphaNumeric(String value){
        try {
            for (int i=0; i<value.length(); i++) {
                char c = value.charAt(i);
                if (!Character.isDigit(c) && !Character.isLetter(c))
                {
                    return false;
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;

    }
    public boolean isMobileNumber(String value){
        try {
            String ePattern = "(^(0)[7-9]{1})([0-9]{9})";
            Pattern pattern = Pattern.compile(value);
            Matcher m = pattern.matcher(value);
            return m.matches();
        }
        catch (Exception e) {
            return false;
        }
    }
    public boolean isValidEmail(String value) {
        try {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            Pattern p = java.util.regex.Pattern.compile(ePattern);
            Matcher m = p.matcher(value);
            return m.matches();
        }
        catch (Exception e) {
            return false;
        }
    }

}
