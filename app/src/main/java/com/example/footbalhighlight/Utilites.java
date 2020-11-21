package com.example.footbalhighlight;


import android.util.Log;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilites {



    public String dateFormater(String date) {

        String[] split = date.split("T");
        String firstSubString = split[0].trim();
//        // This is the format date we want
//        DateFormat mSDF = new SimpleDateFormat("dd MMMM yyyy");
//
//        // This format date is actually present
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//        try {
//            String mydate= mSDF.format(formatter.parse(firstSubString));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

       return firstSubString;
    }
}
