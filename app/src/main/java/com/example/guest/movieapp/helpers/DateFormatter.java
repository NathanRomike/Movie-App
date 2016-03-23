package com.example.guest.movieapp.helpers;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Guest on 3/23/16.
 */
public class DateFormatter {
    public static String yearOnly(String date) {
        return date.substring(0,4).toString();
    }
}
