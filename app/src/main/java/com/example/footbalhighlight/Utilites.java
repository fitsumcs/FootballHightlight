package com.example.footbalhighlight;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Utilites {


    String mydate;

    public String dateFormater(String date) {

        String[] split = date.split("T");
        String firstSubString = split[0].toString();
        // This is the format date we want
        DateFormat mSDF = new SimpleDateFormat("dd MMMM yyyy");

        // This format date is actually present
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            mydate= mSDF.format(formatter.parse(firstSubString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

       return mydate;
    }

}
