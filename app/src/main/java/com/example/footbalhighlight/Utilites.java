package com.example.footbalhighlight;


import android.util.Log;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public  String extractUrls(String text)
    {

        String theUrl = "";
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        for (String url : containedUrls)
        {
            theUrl = url;
        }

        return theUrl;
    }
}
