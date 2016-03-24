package com.example.guest.movieapp.helpers;

import java.text.NumberFormat;

/**
 * Created by Guest on 3/24/16.
 */
public class CurrencyFormatter {
    public static String toDollars(String amount) {
        Double newAmount = Double.parseDouble(amount);
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        return defaultFormat.format(newAmount);
    }
}
