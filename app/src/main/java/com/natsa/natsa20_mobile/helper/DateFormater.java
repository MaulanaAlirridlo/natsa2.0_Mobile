package com.natsa.natsa20_mobile.helper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {
    public String parseDateToddMMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX";
        String outputPattern = "dd MMMM yyyy";
        SimpleDateFormat inputFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            inputFormat = new SimpleDateFormat(inputPattern);
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
