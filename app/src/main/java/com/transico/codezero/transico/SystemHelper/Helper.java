package com.transico.codezero.transico;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

abstract class Helper {

     static String DateFormatter(String format, java.util.Date myDate){
        DateFormat df = new SimpleDateFormat(format,Locale.US);
        return df.format(myDate);
    }

    static String createStartTime(){
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }




}
